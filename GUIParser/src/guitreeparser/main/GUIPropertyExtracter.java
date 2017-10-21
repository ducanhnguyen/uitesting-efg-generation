package guitreeparser.main;

import guitreeparser.object.GuiRootNode;
import java.util.ArrayList;
import java.util.List;
import guitreeparser.object.IGuiNodePropertyName;
import guitreeparser.object.INode;
import guitreeparser.object.IProperty;
import guitreeparser.object.IAbstractNode;

/**
 * Get all possible values of a property's name
 *
 * @author ducanhnguyen
 */
public class GUIPropertyExtracter {

    private GuiRootNode root;
    private List<String> output = new ArrayList<>();
    private String nameProperty;

    public GUIPropertyExtracter() {
    }

    public static void main(String[] args) {
        GUITreeParser parser = new GUITreeParser("C:\\Users\\ducanhnguyen\\Desktop\\Project.GUI.xml");
        parser.parseGUITree();
        GuiRootNode root = parser.getRoot();

        GUIPropertyExtracter extracter = new GUIPropertyExtracter();
        extracter.setNameProperty(IGuiNodePropertyName.INVOKELIST);
        extracter.setRoot(root);
        extracter.getProperty();

        for (String value : extracter.getOutput()) {
            System.out.println(value);
        }
    }

    public void getProperty() {
        getAllProperties(root, nameProperty);
    }

    private void getAllProperties(IAbstractNode node, String name) {
        for (IProperty property : node.getProperties()) {
            if (property.getName().toLowerCase().equals(name.toLowerCase())) {
                if (!output.contains(property.getValue())) {
                    output.add(property.getValue());
                }
            }
        }

        for (INode child : node.getChidren()) {
            getAllProperties((IAbstractNode) child, name);
        }
    }

    public void setRoot(GuiRootNode root) {
        this.root = root;
    }

    public GuiRootNode getRoot() {
        return root;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }
}
