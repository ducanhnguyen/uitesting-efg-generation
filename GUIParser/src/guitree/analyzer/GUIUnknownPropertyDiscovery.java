package guitree.analyzer;

import java.util.ArrayList;
import java.util.List;

import guitree.object.GuiRootNode;
import guitree.object.IAbstractNode;
import guitree.object.IGuiNodePropertyName;
import guitree.object.INode;
import guitree.object.IProperty;

import java.lang.reflect.Field;

/**
 * Discovery unknown properties
 *
 * @author ducanhnguyen
 */
public class GUIUnknownPropertyDiscovery {

	private GuiRootNode root;
	// all of properties are stored in lowercase
	private List<String> unknownProperties = new ArrayList<>();
	// all of properties are stored in lowercase
	private List<String> knownProperties = new ArrayList<>();

	public GUIUnknownPropertyDiscovery() {
	}

	public static void main(String[] args) {
		GUITreeParser parser = new GUITreeParser("C:\\Users\\ducanhnguyen\\Desktop\\Project.GUI.xml");
		parser.parseGUITree();
		GuiRootNode root = parser.getRoot();

		GUIUnknownPropertyDiscovery extracter = new GUIUnknownPropertyDiscovery();
		extracter.setRoot(root);
		extracter.findUnknownProperties();

		System.out.println("Known properties: " + extracter.getKnownProperties());
		System.out.println("Unknown properties: " + extracter.getUnknownProperties());
	}

	public void findUnknownProperties() {
		knownProperties = getListExistingProperties();
		getAllProperties(root, knownProperties);
	}

	private void getAllProperties(IAbstractNode node, List<String> knownProperties) {
		for (IProperty property : node.getProperties()) {

			if (!unknownProperties.contains(property.getName().toLowerCase())
					&& !knownProperties.contains(property.getName().toLowerCase())) {
				unknownProperties.add(property.getName().toLowerCase());
			}
		}

		for (INode child : node.getChidren()) {
			getAllProperties((IAbstractNode) child, knownProperties);
		}
	}

	private List<String> getListExistingProperties() {
		List<String> existingProperties = new ArrayList<>();
		for (Field field : IGuiNodePropertyName.class.getFields()) {
			existingProperties.add(field.getName().toLowerCase());
		}
		return existingProperties;
	}

	public void setRoot(GuiRootNode root) {
		this.root = root;
	}

	public GuiRootNode getRoot() {
		return root;
	}

	public List<String> getUnknownProperties() {
		return unknownProperties;
	}

	public void setUnknownProperties(List<String> unknownProperties) {
		this.unknownProperties = unknownProperties;
	}

	public void setKnownProperties(List<String> knownProperties) {
		this.knownProperties = knownProperties;
	}

	public List<String> getKnownProperties() {
		return knownProperties;
	}

}
