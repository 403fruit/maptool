/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.rptools.maptool.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.rptools.maptool.client.AppUtil;
import net.rptools.maptool.model.HeroLabData;
import net.rptools.maptool.model.Token;
import net.rptools.maptool.model.Token.Type;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jcabi.xml.XMLDocument;

/**
 * Extracts character information from a Hero Lab portfolio and can create rptok token files in .maptool/tmp or updates the token live
 * 
 * @author Jamz
 *
 */
public final class ExtractHeroLab {
	private static final File tmpDir = AppUtil.getTmpDir();
	private File finalTempDir;
	private File extractComplete;
	private File portfolioFile;

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;

	public ExtractHeroLab(File heroLabPortfolio, boolean forceRescan) {
		this.portfolioFile = heroLabPortfolio;

		factory = DocumentBuilderFactory.newInstance();

		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		finalTempDir = new File(tmpDir + "/hero_lab_" + heroLabPortfolio.hashCode());
		extractComplete = new File(tmpDir + "/hero_lab_" + heroLabPortfolio.hashCode() + "/completed_" + heroLabPortfolio.hashCode() + ".txt");

		if (forceRescan) {
			FileUtils.deleteQuietly(finalTempDir);
		}

		finalTempDir.mkdirs();
	}

	public File getTempDir() {
		return finalTempDir;
	}

	public boolean isExtracted() {
		if (extractComplete.exists())
			return true;
		else
			return false;
	}

	public void markComplete() {
		try {
			extractComplete.createNewFile();
			FileUtils.forceDeleteOnExit(finalTempDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void markComplete(String contents) {
		try {
			PrintWriter out = new PrintWriter(extractComplete);
			out.print(contents);
			out.close();
			FileUtils.forceDeleteOnExit(finalTempDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<File> extractAllCharacters() {
		return extractAllCharacters(false);
	}

	public List<File> extractAllCharacters(boolean forceRescan) {
		List<File> heroes = new ArrayList<File>();

		if (isExtracted() && !forceRescan) {
			heroes.addAll(Arrays.asList(finalTempDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".rptok");
				}
			})));

			return heroes;
		}

		try {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			Document portfolioIndex = getPortolioIndex();

			XPathExpression xPath_characters = xpath.compile("//character"); // Jamz: using this vs //document/characters/character which also captures //document/characters/character/minions/character
			XPathExpression xPath_gameSystem = xpath.compile("/document/game/@name");
			XPathExpression xPath_images = xpath.compile("images/image");

			String gameSystem = (String) xPath_gameSystem.evaluate(portfolioIndex, XPathConstants.STRING);
			IteratableNodeList heroNodes = new IteratableNodeList((NodeList) xPath_characters.evaluate(portfolioIndex, XPathConstants.NODESET));

			for (Node hero : heroNodes) {
				Token heroLabToken = new Token();
				String heroName = ((Element) hero).getAttribute("name");
				String heroLabIndex = ((Element) hero).getAttribute("herolableadindex");
				HeroLabData heroLabData = new HeroLabData(heroName);
				File heroFile = FileUtil.getCleanFileName(finalTempDir.getCanonicalPath(), heroName, "." + Token.FILE_EXTENSION);

				// Store all the images for macro use, eg store disguises or alternate portraits
				IteratableNodeList imageNodes = new IteratableNodeList((NodeList) xPath_images.evaluate(hero, XPathConstants.NODESET));
				heroLabData.clearImages();
				for (Node image : imageNodes) {
					Element imageElement = (Element) image;
					if (imageElement != null) {
						String imageFileName = imageElement.getAttribute("filename");
						String imageFolder = imageElement.getAttribute("folder");
						heroLabData.addImage(imageFileName, extractImage(imageFolder + "/" + imageFileName));
					}
				}

				// If there's at least a portrait, lets show that
				if (heroLabData.getImageCount() == 1) {
					heroLabData.setTokenImage(heroLabData.getPortraitImage());
				} else if (heroLabData.getImageCount() == 0) {
					heroLabData.setDefaultImages();
				}

				// Lets add everything to the token
				heroLabToken = new Token(heroName, heroLabData.getTokenImage().getId());
				heroLabToken.setGMName(heroName);

				// set the image shape
				heroLabToken.setShape(TokenUtil.guessTokenType(ImageManager.getImageAndWait(heroLabData.getTokenImage().getId())));

				// set the portrait image asset
				heroLabToken.setPortraitImage(heroLabData.getPortraitImage().getId());

				// set the hand out image asset (if exists)
				if (heroLabData.getHandoutImage() != null) {
					heroLabToken.setCharsheetImage(heroLabData.getHandoutImage().getId());
				}

				heroLabData.setPortfolioFile(portfolioFile);
				heroLabData.setHeroLabIndex(heroLabIndex);
				heroLabData.setGameSystem(gameSystem);
				heroLabData.setSummary(((Element) hero).getAttribute("summary"));
				heroLabData.setPlayerName(((Element) hero).getAttribute("playername"));
				heroLabData.setAlly(((Element) hero).getAttribute("isally").equalsIgnoreCase("yes") ? true : false);

				// Is it a minion?
				if (hero.getParentNode().getNodeName().toString().equalsIgnoreCase("minions")) {
					Node master = hero.getParentNode().getParentNode();
					String minionMasterIndex = ((Element) master).getAttribute("herolableadindex");
					String minionMasterName = ((Element) master).getAttribute("name");

					heroLabData.setMinion(true);
					heroLabData.setMinionMasterIndex(minionMasterIndex);
					heroLabData.setMinionMasterName(minionMasterName);
					heroLabData.setStatBlocks(getStatBlocks(xpath, hero, master, heroName));
				} else {
					heroLabData.setStatBlocks(getStatBlocks(xpath, hero));
				}

				if (heroLabData.isAlly()) {
					heroLabToken.setType(Type.PC);
					heroLabToken.setOwnedByAll(true);
				} else {
					heroLabToken.setType(Type.NPC);
				}

				heroLabToken.setHeroLabData(heroLabData);

				PersistenceUtil.saveToken(heroLabToken, heroFile, true);
				heroes.add(heroFile);
				markComplete(new XMLDocument(portfolioIndex).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return heroes;
	}

	public HeroLabData refreshCharacter(HeroLabData heroLabData) {
		try {
			String heroLabIndex = heroLabData.getHeroLabIndex();

			// Ugg, @herolableadindex is always 0 for all minions :(
			String minionCriteria = "";
			if (heroLabData.isMinion())
				minionCriteria = "' and @name='" + heroLabData.getName();

			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			Document portfolioIndex = getPortolioIndex();

			XPathExpression xPath_characters = xpath.compile("//character[@herolableadindex='" + heroLabIndex + minionCriteria + "']");
			XPathExpression xPath_gameSystem = xpath.compile("/document/game/@name");
			XPathExpression xPath_images = xpath.compile("images/image");

			String gameSystem = (String) xPath_gameSystem.evaluate(portfolioIndex, XPathConstants.STRING);
			Node hero = (Node) xPath_characters.evaluate(portfolioIndex, XPathConstants.NODE);

			String heroName = ((Element) hero).getAttribute("name");
			heroLabData = new HeroLabData(heroName);

			IteratableNodeList imageNodes = new IteratableNodeList((NodeList) xPath_images.evaluate(hero, XPathConstants.NODESET));
			heroLabData.clearImages();
			for (Node image : imageNodes) {
				Element imageElement = (Element) image;
				if (imageElement != null) {
					String imageFileName = imageElement.getAttribute("filename");
					String imageFolder = imageElement.getAttribute("folder");
					heroLabData.addImage(imageFileName, extractImage(imageFolder + "/" + imageFileName));
				}
			}

			// If there's at least a portrait, lets show that
			if (heroLabData.getImageCount() == 1) {
				heroLabData.setTokenImage(heroLabData.getPortraitImage());
			} else if (heroLabData.getImageCount() == 0) {
				heroLabData.setDefaultImages();
			}

			heroLabData.setPortfolioFile(portfolioFile);
			heroLabData.setHeroLabIndex(heroLabIndex);
			heroLabData.setGameSystem(gameSystem);
			heroLabData.setSummary(((Element) hero).getAttribute("summary"));
			heroLabData.setPlayerName(((Element) hero).getAttribute("playername"));
			heroLabData.setAlly(((Element) hero).getAttribute("isally").equalsIgnoreCase("yes") ? true : false);

			// Is it a minion?
			if (hero.getParentNode().getNodeName().toString().equalsIgnoreCase("minions")) {
				Node master = hero.getParentNode().getParentNode();
				String minionMasterIndex = ((Element) master).getAttribute("herolableadindex");

				heroLabData.setMinion(true);
				heroLabData.setMinionMasterIndex(minionMasterIndex);

				heroLabData.setStatBlocks(getStatBlocks(xpath, hero, master, heroName));
			} else {
				heroLabData.setStatBlocks(getStatBlocks(xpath, hero));
			}

			markComplete(new XMLDocument(portfolioIndex).toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return heroLabData;
	}

	private Document getPortolioIndex() throws IOException, SAXException {
		return getXmlFromZip("index.xml");
	}

	private Document getXmlFromZip(String zipPath) throws IOException, SAXException {
		ZipFile por = new ZipFile(portfolioFile);
		ZipEntry indexEntry = por.getEntry(zipPath);
		Document indexXml = null;

		if (indexEntry != null) {
			InputStream is = por.getInputStream(indexEntry);
			indexXml = builder.parse(is);
		}

		por.close();

		return indexXml;
	}

	public HeroLabData refreshHeroLabData(HeroLabData heroData) {
		HashMap<String, HashMap<String, String>> statBlocks = new HashMap<String, HashMap<String, String>>(3);

		statBlocks.put(HeroLabData.StatBlockType.TEXT, getStatBlock(heroData.getStatBlock_location(HeroLabData.StatBlockType.TEXT), HeroLabData.StatBlockType.TEXT));
		statBlocks.put(HeroLabData.StatBlockType.HTML, getStatBlock(heroData.getStatBlock_location(HeroLabData.StatBlockType.HTML), HeroLabData.StatBlockType.HTML));
		statBlocks.put(HeroLabData.StatBlockType.XML, getStatBlock(heroData.getStatBlock_location(HeroLabData.StatBlockType.XML), HeroLabData.StatBlockType.XML));

		heroData.setStatBlocks(statBlocks);

		return heroData;
	}

	private HashMap<String, HashMap<String, String>> getStatBlocks(XPath xpath, Node hero) {
		return getStatBlocks(xpath, hero, null, null);
	}

	private HashMap<String, HashMap<String, String>> getStatBlocks(XPath xpath, Node hero, Node master, String minionName) {
		HashMap<String, HashMap<String, String>> statBlocks = new HashMap<String, HashMap<String, String>>(3);

		statBlocks.put(HeroLabData.StatBlockType.TEXT, getStatBlock(getStatBlockPath(xpath, hero, HeroLabData.StatBlockType.TEXT), HeroLabData.StatBlockType.TEXT));
		statBlocks.put(HeroLabData.StatBlockType.HTML, getStatBlock(getStatBlockPath(xpath, hero, HeroLabData.StatBlockType.HTML), HeroLabData.StatBlockType.HTML));

		HashMap<String, String> xmlStatBlockMap = new HashMap<String, String>(2);
		String zipPath;
		// Minion XML statblocks are actually stored in the main characters <minions node, ugg...
		if (master == null) {
			zipPath = getStatBlockPath(xpath, hero, HeroLabData.StatBlockType.XML);
		} else {
			zipPath = getStatBlockPath(xpath, master, HeroLabData.StatBlockType.XML);
		}
		xmlStatBlockMap.put("location", zipPath);
		Document xmlStatBlock;

		try {
			xmlStatBlock = getXmlFromZip(zipPath);

			if (master == null) {
				// We don't need the minion data in the main character so we will remove that node to save space
				NodeList nodes = xmlStatBlock.getElementsByTagName("minions");
				Element minions = (Element) nodes.item(0);
				minions.getParentNode().removeChild(minions);

				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				StreamResult result = new StreamResult(new StringWriter());
				DOMSource source = new DOMSource(xmlStatBlock);
				transformer.transform(source, result);

				xmlStatBlockMap.put("data", result.getWriter().toString());

				statBlocks.put(HeroLabData.StatBlockType.XML, xmlStatBlockMap);
			} else {
				// We only need the <character> node for this minion, so lets find it and clone it...
				NodeList nodes = xmlStatBlock.getElementsByTagName("character");
				Node minionNode = null;

				IteratableNodeList allNodes = new IteratableNodeList(nodes);
				for (Node character : allNodes) {
					Element characterElement = (Element) character;
					if (characterElement != null) {
						String characterName = characterElement.getAttribute("name");
						if (characterName.equals(minionName)) {
							minionNode = character.cloneNode(true);

							break;
						}
					}
				}

				// If we find it, remove all <character> nodes and insert the saved clone copy under the proper parent node, <public>
				if (minionNode != null) {
					nodes = xmlStatBlock.getElementsByTagName("character");
					Element character = (Element) nodes.item(0);
					character.getParentNode().removeChild(character);

					nodes = xmlStatBlock.getElementsByTagName("public");
					nodes.item(0).insertBefore(minionNode, null);
				}

				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				StreamResult result = new StreamResult(new StringWriter());
				DOMSource source = new DOMSource(xmlStatBlock);
				transformer.transform(source, result);

				xmlStatBlockMap.put("data", result.getWriter().toString());

				statBlocks.put(HeroLabData.StatBlockType.XML, xmlStatBlockMap);
			}

		} catch (IOException | SAXException | TransformerFactoryConfigurationError |

				TransformerException e1) {
			e1.printStackTrace();
		}

		return statBlocks;
	}

	private String getStatBlockPath(XPath xpath, Node hero, String type) {
		String path = "/";

		try {
			XPathExpression xPath_statBlock = xpath.compile("statblocks/statblock[@format='" + type + "']");
			Node statBlockNode = (Node) xPath_statBlock.evaluate(hero, XPathConstants.NODE);
			if (statBlockNode != null)
				path = ((Element) statBlockNode).getAttribute("folder") + "/" + ((Element) statBlockNode).getAttribute("filename");
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return path;
	}

	private HashMap<String, String> getStatBlock(String zipPath, String type) {
		HashMap<String, String> statBlock = new HashMap<String, String>(2);
		ZipFile por;

		try {
			por = new ZipFile(portfolioFile);
			ZipEntry indexEntry = por.getEntry(zipPath);

			if (indexEntry != null) {
				statBlock.put("location", zipPath);
				statBlock.put("data", IOUtils.toString(por.getInputStream(indexEntry), "UTF-8"));
			} else {
				statBlock.put("location", null);
				statBlock.put("data", "<HTML>Unable to retrieve " + type + " statblock</HTML>");
			}

			por.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statBlock;
	}

	private BufferedImage extractImage(String fileInputPath) throws IOException {
		ZipFile por = new ZipFile(portfolioFile);
		ZipEntry entry = por.getEntry(fileInputPath);
		InputStream zipIn = por.getInputStream(entry);
		BufferedImage zipImage = ImageIO.read(zipIn);

		por.close();

		return zipImage;
	}
}