package utils.condition;

import guitreeparser.object.EfgEvent;
import guitreeparser.object.INode;
import utils.ISearchCondition;

/**
 *
 * @author ducanhnguyen
 */
public class EventNodeCondition implements ISearchCondition {

    @Override
    public boolean isSatisfiable(INode n) {
        return n instanceof EfgEvent;
    }

}
