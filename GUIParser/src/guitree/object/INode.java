package guitree.object;

import java.util.List;

/**
 *
 * @author ducanhnguyen
 */
public interface INode {

	List<INode> getChidren();

	void setChildren(List<INode> children);

	void setParent(INode parent);

	INode getParent();

	void addChild(INode efgNode);
}
