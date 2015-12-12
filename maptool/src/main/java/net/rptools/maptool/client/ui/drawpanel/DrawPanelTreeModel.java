package net.rptools.maptool.client.ui.drawpanel;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import net.rptools.maptool.client.ui.tokenpanel.TokenPanelTreeModel.View;
import net.rptools.maptool.language.I18N;
import net.rptools.maptool.model.ModelChangeEvent;
import net.rptools.maptool.model.ModelChangeListener;
import net.rptools.maptool.model.Zone;
import net.rptools.maptool.model.Zone.Layer;
import net.rptools.maptool.model.drawing.DrawnElement;

public class DrawPanelTreeModel implements TreeModel, ModelChangeListener {

	private final String root = "Views";
	private Zone zone;
	private final JTree tree;
	private final Map<View, List<DrawnElement>> viewMap = new HashMap<View, List<DrawnElement>>();
	private final List<View> currentViewList = new ArrayList<View>();
	private volatile boolean updatePending = false;
	private final List<TreeModelListener> listenerList = new ArrayList<TreeModelListener>();
	
	public enum View {
		TOKEN_DRAWINGS("panel.DrawExplorer.View.TOKEN", Zone.Layer.TOKEN),
		GM_DRAWINGS("panel.DrawExplorer.View.GM", Zone.Layer.GM),
		OBJECT_DRAWINGS("panel.DrawExplorer.View.OBJECT", Zone.Layer.OBJECT),
		BACKGROUND_DRAWINGS("panel.DrawExplorer.View.BACKGROUND", Zone.Layer.BACKGROUND);
		
		private View(String key, Zone.Layer layer) {
			this.displayName = I18N.getText(key);
			this.layer = layer;
		}
		String displayName;
		Zone.Layer layer;

		public String getDisplayName() {
			return displayName;
		}

		public Zone.Layer getLayer() {
			return layer;
		}
	}
	
	public DrawPanelTreeModel(JTree tree) {
		this.tree = tree;
	}
	
	@Override
	public void modelChanged(ModelChangeEvent event) {
		update();
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent == root) {
			return currentViewList.get(index);
		}
		if (parent instanceof View) {
			return getViewList((View) parent).get(index);
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent == root) {
			return currentViewList.size();
		}
		if (parent instanceof View) {
			return getViewList((View) parent).size();
		}
		return 0;
	}

	private List<DrawnElement> getViewList(View view) {
		List<DrawnElement> list = viewMap.get(view);
		if (list == null) {
			return Collections.emptyList();
		}
		return list;
	}

	@Override
	public boolean isLeaf(Object node) {
		return node instanceof DrawnElement;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// Nothing to do
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent == root) {
			return currentViewList.indexOf(child);
		}
		if (parent instanceof View) {
			getViewList((View) parent).indexOf(child);
		}
		return -1;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(l);
	}
	
	// Called by zone change event
	public void setZone(Zone zone) {
		if (zone != null) {
			zone.removeModelChangeListener(this);
		}
		this.zone = zone;
		update();

		if (zone != null) {
			zone.addModelChangeListener(this);
		}
	}

	public void update() {
		// better solution would be to use a timeout to invoke the internal update to give more
		// token events the chance to arrive, but in this case EventQueue overload will
		// manage to delay it quite nicely
		if (!updatePending) {
			updatePending = true;
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					updatePending = false;
					updateInternal();
				}
			});
		}
	}

	private void updateInternal() {
		currentViewList.clear();
		viewMap.clear();
		List<DrawnElement> drawableList  = new ArrayList<DrawnElement>();
		if (zone != null) {
			drawableList  = zone.getBackgroundDrawnElements();
			if (drawableList.size()>0) {
				viewMap.put(View.BACKGROUND_DRAWINGS, drawableList);
				currentViewList.add(View.BACKGROUND_DRAWINGS);
			}
			drawableList  = zone.getGMDrawnElements();
			if (drawableList.size()>0) {
				viewMap.put(View.GM_DRAWINGS, drawableList);
				currentViewList.add(View.GM_DRAWINGS);
			}
			drawableList  = zone.getObjectDrawnElements();
			if (drawableList.size()>0) {
				viewMap.put(View.OBJECT_DRAWINGS, drawableList);
				currentViewList.add(View.OBJECT_DRAWINGS);
			}
			drawableList  = zone.getDrawnElements(Layer.TOKEN);
			if (drawableList.size()>0) {
				viewMap.put(View.TOKEN_DRAWINGS, drawableList);
				currentViewList.add(View.TOKEN_DRAWINGS);
			}
		}

		Enumeration<TreePath> expandedPaths = tree.getExpandedDescendants(new TreePath(root));

		fireStructureChangedEvent(
				new TreeModelEvent(this,
						new Object[] { getRoot() },
						new int[] { currentViewList.size() - 1 },
						new Object[] { View.BACKGROUND_DRAWINGS }
				)
		);
		while (expandedPaths != null && expandedPaths.hasMoreElements()) {
			tree.expandPath(expandedPaths.nextElement());
		}
	}

	private void fireStructureChangedEvent(TreeModelEvent e) {
		TreeModelListener[] listeners = listenerList.toArray(new TreeModelListener[listenerList.size()]);
		for (TreeModelListener listener : listeners) {
			listener.treeStructureChanged(e);
		}
	}

}
