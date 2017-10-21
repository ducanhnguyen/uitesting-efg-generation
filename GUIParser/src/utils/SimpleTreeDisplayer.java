package utils;

import guitree.object.AbstractEventNode;
import guitree.object.AbstractGuiNode;
import guitree.object.EfgEvent;
import guitree.object.GuiNode;
import guitree.object.GuiStructureNode;
import guitree.object.IAbstractNode;
import guitree.object.INode;

public class SimpleTreeDisplayer extends ToString {

	public SimpleTreeDisplayer(INode root) {
		super(root);
	}

	public static void main(String[] args) {
	}

	@Override
	public String toString(INode node) {
		displayTree(node, 0);
		return treeInString;
	}

	private void displayTree(INode node, int level) {
		if (node == null) {
			return;
		} else if (node instanceof AbstractGuiNode) {
			AbstractGuiNode cast = (AbstractGuiNode) node;

			if (node instanceof GuiNode || node instanceof GuiStructureNode) {
				treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] " + "[size = "
						+ node.getChidren().size() + "]" + cast.getProperties() + "\n";
			} else {
				if (cast.getEvents().size() >= 1) {
					treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] binded; "
							+ cast.getProperties() + "\n";
				} else {
					treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] " + cast.getProperties()
							+ "\n";
				}
			}
		} else if (node instanceof AbstractEventNode) {
			AbstractEventNode cast = (AbstractEventNode) node;

			if (node instanceof EfgEvent) {
				treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] " + cast + "\n";
			} else {
				treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] " + "\n";
			}
		}
		for (INode child : node.getChidren()) {
			displayTree((IAbstractNode) child, ++level);
			level--;
		}
	}
}
