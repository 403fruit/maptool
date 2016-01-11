package net.rptools.maptool.client.ui.drawpanel;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;

import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.model.GUID;
import net.rptools.maptool.model.Zone;
import net.rptools.maptool.model.drawing.Drawable;
import net.rptools.maptool.model.drawing.DrawablesGroup;
import net.rptools.maptool.model.drawing.DrawnElement;
import net.rptools.maptool.model.drawing.Pen;

public class DrawablesPanel extends JComponent {
	private static final long serialVersionUID = 441600187734634440L;
	private static final int MAX_PANEL_SIZE = 250;
	private final List<GUID> selectedIDList = new ArrayList<GUID>();

	public List<Object> getSelectedIds() {
		List<Object> list = new ArrayList<Object>();
		list.addAll(selectedIDList);
		return list;
	}

	public void setSelectedIds(List<GUID> ids) {
		this.selectedIDList.clear();
		this.selectedIDList.addAll(ids);
		repaint();
	}

	public void addSelectedId(GUID id) {
		this.selectedIDList.add(id);
		repaint();
	}

	public void clearSelectedIds() {
		this.selectedIDList.clear();
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		if (selectedIDList.size()>0) {
			Graphics2D g2d = (Graphics2D)g;
			if (MapTool.getFrame().getCurrentZoneRenderer()!=null) {
				Zone zone = MapTool.getFrame().getCurrentZoneRenderer().getZone();
				if (zone != null) {
					List<DrawnElement> drawableList = new ArrayList<DrawnElement>();
					for (GUID id : selectedIDList) {
						if (zone.getDrawnElement(id)!=null)
								drawableList.add(zone.getDrawnElement(id));
					}
					if (drawableList.size()>0) {
						Collections.reverse(drawableList);
						Rectangle bounds = getBounds(drawableList);
						double scale = (double)getSize().width / (double)bounds.width;
						//double scale = (double)200 / (double)bounds.width;
						g.drawImage(drawDrawables(g2d, drawableList, bounds, scale), 0, 0, null);
					}
				}
			}
		}
	}


	private BufferedImage drawDrawables(Graphics2D gx, List<DrawnElement> drawableList, Rectangle viewport, double scale) {
		BufferedImage backBuffer = new BufferedImage(viewport.width, viewport.height, Transparency.TRANSLUCENT);
		Graphics2D g = backBuffer.createGraphics();
		g.setClip(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
		Composite oldComposite = g.getComposite();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		AffineTransform tf = new AffineTransform();
		tf.translate(-(viewport.x*scale), -(viewport.y*scale));
		tf.scale(scale, scale);
		g.transform(tf);
		for (DrawnElement element : drawableList) {
			Drawable drawable = element.getDrawable();
			Pen pen = element.getPen();
			if (pen.getOpacity() != 1 && pen.getOpacity() != 0 /* handle legacy pens, besides, it doesn't make sense to have a non visible pen*/) {
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, pen.getOpacity()));
			}
			if (drawable instanceof DrawablesGroup) {
				g.drawImage(drawDrawables(g, ((DrawablesGroup)drawable).getDrawableList(), new Rectangle(0,0,(int)(viewport.width/scale),(int)(viewport.height/scale)), 1), 0, 0, null);
			} else 
				drawable.draw(g, pen);
			g.setComposite(oldComposite);
		}
		g.dispose();
		return backBuffer;
	}

	private Rectangle getBounds(List<DrawnElement> drawableList) {
		Rectangle bounds = null;
		for (DrawnElement element : drawableList) {
			Rectangle drawnBounds = new Rectangle(element.getDrawable().getBounds());
			// Handle pen size
			Pen pen = element.getPen();
			int penSize = (int) (pen.getThickness() / 2 + 1);
			drawnBounds.setRect(drawnBounds.getX() - penSize, drawnBounds.getY() - penSize, drawnBounds.getWidth() + pen.getThickness(), drawnBounds.getHeight() + pen.getThickness());
			if (bounds==null)
				bounds = drawnBounds;
			else
				bounds.add(drawnBounds);
		}
		if (bounds!=null)
			return bounds;
		return new Rectangle(0, 0, -1, -1);
	}


}
