package utils;

import java.util.ArrayList;
import java.util.List;

import guitree.object.INode;

/**
 *
 * @author ducanhnguyen
 */
public class Search {

	/**
	 * @param root
	 *            Root sub tree
	 * @param condition
	 *            The condition
	 * @return List of node matching the given condition
	 */
	public static List<INode> searchNodes(INode root, ISearchCondition condition) {
		List<INode> output = new ArrayList<>();

		for (INode child : root.getChidren()) {
			if (condition.isSatisfiable(child)) {
				output.add(child);
			}
			output.addAll(Search.searchNodes(child, condition));
		}
		return output;
	}
}
