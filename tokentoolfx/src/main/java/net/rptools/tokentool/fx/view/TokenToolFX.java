package net.rptools.tokentool.fx.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.rptools.lib.DebugStream;
import net.rptools.lib.FileUtil;
import net.rptools.lib.image.ImageUtil;
import net.rptools.tokentool.AppConstants;
import net.rptools.tokentool.AppSetup;
import net.rptools.tokentool.fx.controller.TokenTool_Controller;
import net.rptools.tokentool.fx.util.FxImageUtil;

/**
 * 
 * @author Jamz
 * 
 *         To see splashscreen during testing, use JVM arg:
 *         -Djavafx.preloader=net.rptools.tokentool..fx.view.SplashScreenLoader Otherwise
 *         splashscreen will only show when defined as JavaFX-Preloader-Class in
 *         the JAR manifest.
 * 
 */
public class TokenToolFX extends Application {
	private final String TOKEN_TOOL_ICON = "/net/rptools/tokentool/image/token_tool_icon.png";
	private final String TOKEN_TOOL_FXML = "/net/rptools/tokentool/fxml/TokenTool.fxml";
	private final String TOKEN_TOOL_BUNDLE = "net.rptools.tokentool.i18n.TokenTool";

	private BorderPane root;
	private TokenTool_Controller controller;

	public static String VERSION = "";

	private static final int THUMB_SIZE = 100;
	private int overlayCount = 0;
	private int loadCount = 1;
	private TreeItem<String> overlayTreeItems;

	@Override
	public void start(Stage primaryStage) throws IOException {
		setUserAgentStylesheet(STYLESHEET_MODENA); // Setting the style back to the new Modena
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(TOKEN_TOOL_FXML), ResourceBundle.getBundle(TOKEN_TOOL_BUNDLE));
		root = fxmlLoader.load();
		controller = (TokenTool_Controller) fxmlLoader.getController();

		Scene scene = new Scene(root);
		primaryStage.setTitle("TokenTool");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(TOKEN_TOOL_ICON)));
		primaryStage.setScene(scene);

		controller.updateOverlayTreeview(overlayTreeItems);
		primaryStage.show();
		controller.expandOverlayOptionsPane(true);
	}

	@Override
	public void init() throws Exception {
		final Parameters params = getParameters();

		// System.out.println("3D Hardware Available? " + Platform.isSupported(ConditionalFeature.SCENE3D));
		VERSION = getVersion();

		// Lets unzip/install the overlays in newer version
		AppSetup.install(VERSION);

		// Now lets cache any overlays we find and update preLoader with progress
		overlayCount = (int) Files.walk(AppConstants.OVERLAY_DIR.toPath()).filter(Files::isRegularFile).count();
		overlayTreeItems = cacheOverlays(AppConstants.OVERLAY_DIR, null, THUMB_SIZE);

		// All Done!
		notifyPreloader(new Preloader.ProgressNotification(1.0));
	}

	/**
	 * 
	 * @author Jamz
	 * @since 1.4.0.1
	 * 
	 *	This method loads and processes all the overlays found in
	 *	user.home/overlays It can take a minute to load as it creates
	 *	thumbnail versions for the comboBox so we call this during the
	 *	init and display progress in the preLoader (splash screen).
	 *
	 */
	private TreeItem<String> cacheOverlays(File dir, TreeItem<String> parent, int THUMB_SIZE) {
		TreeItem<String> root = new TreeItem<>(dir.getName());
		root.setExpanded(false);

		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				cacheOverlays(file, root, THUMB_SIZE);
			} else {
				if (ImageUtil.SUPPORTED_IMAGE_FILE_FILTER.accept(file, file.getName())) {
					// Create a thumbnail and translate Magenta to Transparency...
					Image thumb = FxImageUtil.magentaToTransparency(
							new Image(file.toURI().toString(), THUMB_SIZE, THUMB_SIZE, true, true), 1);
					TreeItem<String> imageNode = new TreeItem<>(file.toURI().toString(), new ImageView(thumb));
					root.getChildren().add(imageNode);
				}

				notifyPreloader(new Preloader.ProgressNotification((double) loadCount++ / overlayCount));
			}
		}

		if (parent != null && root.getChildren().size() > 0) {
			// When we show the overlay image, the TreeItem value is "" so we need to
			// sort those to the bottom for a cleaner look and keep sub dir's at the top.
			// If a node has no children then it's an overlay, otherwise it's a directory...
			root.getChildren().sort(new Comparator<TreeItem<String>>() {
				@Override
				public int compare(TreeItem<String> o1, TreeItem<String> o2) {
					if (o1.getChildren().size() == 0 && o2.getChildren().size() == 0)
						return 0;
					else if (o1.getChildren().size() == 0)
						return Integer.MAX_VALUE;
					else if (o2.getChildren().size() == 0)
						return Integer.MIN_VALUE;
					else
						return o1.getValue().compareTo(o2.getValue());
				}
			});

			parent.getChildren().add(root);
		}

		return root;
	}

	private String getVersion() {
		String version = "DEVELOPMENT";
		try {
			if (getClass().getClassLoader().getResource("net/rptools/tokentool/version.txt") != null) {
				try {
					version = new String(FileUtil.loadResource("net/rptools/tokentool/version.txt"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IllegalArgumentException iae) {
			version = "Could not load version";
		}

		return version;
	}

	private static boolean getCommandLineOption(Options options, String searchValue, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			if (cmd.hasOption(searchValue)) {
				return true;
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * Legacy, it simply calls launches the FX Application which calls init()
	 * then start()
	 * 
	 * @author Jamz
	 * @since 1.4.0.1
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Options cmdOptions = new Options();
		cmdOptions.addOption("d", "debug", false, "turn on System.out enhanced debug output");

		boolean debug = getCommandLineOption(cmdOptions, "debug", args);

		// Jamz: Just a little console log formatter for system.out to hyperlink messages to source.
		if (debug)
			DebugStream.activate();
		else
			DebugStream.deactivate();

		launch(args);
	}
}