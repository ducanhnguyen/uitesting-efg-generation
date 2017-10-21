package guitree.object;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ducanhnguyen
 */
public abstract class AbstractNode implements IAbstractNode {

	private List<INode> children = new ArrayList<>();
	private List<IProperty> properties = new ArrayList<>();
	private INode parent;

	@Override
	public List<INode> getChidren() {
		return children;
	}

	public void setChildren(List<INode> children) {
		this.children = children;
	}

	public List<IProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<IProperty> attributes) {
		this.properties = attributes;
	}

	public void setParent(INode parent) {
		this.parent = parent;
	}

	public INode getParent() {
		return parent;
	}

	public void addChild(INode efgNode) {
		getChidren().add(efgNode);
		efgNode.setParent(this);
	}

	public String getValueOfProperty(String propertyName) {
		for (IProperty property : this.getProperties()) {
			if (property.getName().toLowerCase().equals(propertyName.toLowerCase())) {
				return property.getValue();
			}
		}
		return "";
	}
}
