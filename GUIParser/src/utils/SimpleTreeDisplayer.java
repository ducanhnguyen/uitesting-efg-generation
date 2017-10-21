package utils;

import guitreeparser.object.AbstractEventNode;
import guitreeparser.object.AbstractGuiNode;
import guitreeparser.object.AbstractNode;
import guitreeparser.object.EfgEvent;
import guitreeparser.object.EfgEvents;
import guitreeparser.object.GuiContainerNode;
import guitreeparser.object.GuiNode;
import guitreeparser.object.GuiStructureNode;
import guitreeparser.object.INode;
import guitreeparser.object.IAbstractNode;

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
                treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] " + "[size = " + node.getChidren().size()
                        + "]" + cast.getProperties() + "\n";
            } else {
                if (cast.getEvents().size() >= 1) {
                    treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] binded; " + cast.getProperties() + "\n";
                } else {
                    treeInString += genTab(level) + "[" + node.getClass().getSimpleName() + "] " + cast.getProperties() + "\n";
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
