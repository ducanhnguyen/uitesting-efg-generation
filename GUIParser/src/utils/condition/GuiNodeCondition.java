package utils.condition;

import guitree.object.GuiContainerNode;
import guitree.object.GuiWidgetNode;
import guitree.object.GuiWindowNode;
import guitree.object.INode;
import utils.ISearchCondition;

/**
 *
 * @author ducanhnguyen
 */
public class GuiNodeCondition implements ISearchCondition {

	@Override
	public boolean isSatisfiable(INode n) {
		return n instanceof GuiContainerNode || n instanceof GuiWidgetNode || n instanceof GuiWindowNode;
	}

}
