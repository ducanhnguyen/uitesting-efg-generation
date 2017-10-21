package guitree.object;

import java.util.List;

/**
 *
 * @author ducanhnguyen
 */
public interface IAbstractNode extends INode {

	List<IProperty> getProperties();

	void setProperties(List<IProperty> properties);

	String getValueOfProperty(String propertyName);
}
