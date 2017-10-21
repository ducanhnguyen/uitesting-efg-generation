package utils.condition;

import guitree.object.EfgEvent;
import guitree.object.INode;
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
