package utils.condition;

import guitreeparser.object.GuiContainerNode;
import guitreeparser.object.GuiWidgetNode;
import guitreeparser.object.GuiWindowNode;
import guitreeparser.object.INode;
import utils.ISearchCondition;

/**
 *
 * @author ducanhnguyen
 */
public class GuiNodeCondition implements ISearchCondition {

    @Override
    public boolean isSatisfiable(INode n) {
        return n instanceof GuiContainerNode || n instanceof GuiWidgetNode
                || n instanceof GuiWindowNode;
    }

}
