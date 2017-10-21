package guitreeparser.main;

import guitreeparser.object.GuiContainerNode;
import guitreeparser.object.GuiContentsNode;
import guitreeparser.object.GuiNode;
import guitreeparser.object.GuiStructureNode;
import guitreeparser.object.Property;
import guitreeparser.object.GuiRootNode;
import guitreeparser.object.GuiUnknownNode;
import guitreeparser.object.GuiWidgetNode;
import guitreeparser.object.GuiWindowNode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.SimpleTreeDisplayer;
import guitreeparser.object.IAbstractNode;

/**
 *
 * @author ducanhnguyen
 */
public class GUITreeParser {

    private File guiTreeFile;

    GuiRootNode root = new GuiRootNode();

    public GUITreeParser(String path) {
        guiTreeFile = new File(path);
    }

    public static void main(String[] args) {
        GUITreeParser parser = new GUITreeParser("C:\\Users\\ducanhnguyen\\Desktop\\Project.GUI.xml");
        parser.parseGUITree();
        System.out.println(new SimpleTreeDisplayer(parser.getRoot()).getTreeInString());
    }

    public void parseGUITree() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(guiTreeFile);

            doc.getDocumentElement().normalize();

            NodeList guiStructureNode = doc.getElementsByTagName("GUIStructure");
            visitXml(root, guiStructureNode.item(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void visitXml(IAbstractNode parent, Node xmlNode) {
        if (isAttributeTag(xmlNode)) {
            parent.getProperties().addAll(getAllPropertiesInAttributeTag(xmlNode));
        } else {
            IAbstractNode newNode = null;
            switch (xmlNode.getNodeName()) {
                case "Window":
                    newNode = new GuiWindowNode();
                    break;

                case "GUIStructure":
                    newNode = new GuiStructureNode();
                    break;

                case "GUI":
                    newNode = new GuiNode();
                    break;

                case "Container":
                    newNode = new GuiContainerNode();
                    break;

                case "Contents":
                    newNode = new GuiContentsNode();
                    break;

                case "Widget":
                    newNode = new GuiWidgetNode();
                    break;

                case "#text":
                    break;

                default:
                    newNode = new GuiUnknownNode();
                    break;
            }

            if (newNode != null) {
                if (newNode instanceof GuiContentsNode) {
                    for (int i = 0; i < ((NodeList) xmlNode).getLength(); i++) {
                        visitXml(parent, ((NodeList) xmlNode).item(i));
                    }
                } else {
                    parent.addChild(newNode);
                    for (int i = 0; i < ((NodeList) xmlNode).getLength(); i++) {
                        visitXml(newNode, ((NodeList) xmlNode).item(i));
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * <Attributes>
     * <Property>
     * <Name>ID</Name>
     * <Value>Error</Value>
     * </Property>
     * </Attributes>
     * </pre>
     *
     * @param node
     * @return
     */
    private List<Property> getAllPropertiesInAttributeTag(Node node) {
        List<Property> attributes = new ArrayList<>();

        // for each Property
        for (int i = 0; i < ((NodeList) node).getLength(); i++) {
            Node item = ((NodeList) node).item(i);
            Property property = new Property();

            if (item.getNodeName().equals("Property")) {
                for (int j = 0; j < ((NodeList) item).getLength(); j++) {
                    Node propertyItem = ((NodeList) item).item(j);
                    switch (propertyItem.getNodeName()) {
                        case "Name":
                            property.setName(propertyItem.getTextContent());
                            break;
                        case "Value":
                            property.setValue(propertyItem.getTextContent());
                            break;
                    }
                }
                attributes.add(property);
            }
        }
        return attributes;
    }

    private boolean isAttributeTag(Node xmlNode) {
        return xmlNode.getNodeName().equals("Attributes");
    }

    public File getGuiTreeFile() {
        return guiTreeFile;
    }

    public void setGuiTreeFile(File guiTreeFile) {
        this.guiTreeFile = guiTreeFile;
    }

    public void setRoot(GuiRootNode root) {
        this.root = root;
    }

    public GuiRootNode getRoot() {
        return root;
    }

}
