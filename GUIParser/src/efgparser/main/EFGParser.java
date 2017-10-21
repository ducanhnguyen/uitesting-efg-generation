package efgparser.main;

import guitreeparser.object.EfgNode;
import guitreeparser.object.EfgEvent;
import guitreeparser.object.EfgEventGraph;
import guitreeparser.object.EfgEvents;
import guitreeparser.object.EfgUnknownNode;
import guitreeparser.object.Property;
import guitreeparser.object.GuiRootNode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import guitreeparser.object.INode;
import utils.Search;
import utils.condition.EventNodeCondition;
import guitreeparser.object.IEfgPropertyName;
import guitreeparser.object.IAbstractNode;

/**
 *
 * @author ducanhnguyen
 */
public class EFGParser {

    private GuiRootNode root = new GuiRootNode();

    private File efgFile;

    private MatrixEvent eventMatrix = new MatrixEvent();

    public EFGParser(String path) {
        efgFile = new File(path);
    }

    public static void main(String[] args) {
        EFGParser parser = new EFGParser("C:\\Users\\ducanhnguyen\\Desktop\\Project.EFG.xml");
        parser.parseEfgTree();
        //System.out.println(new SimpleTreeDisplayer((IAbstractNode) parser.parseEfgTree()).getTreeInString());
        System.out.println(parser.getEventMatrix().exportEventMatrixToString());
    }

    public void parseEfgTree() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(efgFile);

            doc.getDocumentElement().normalize();

            NodeList efgNode = doc.getElementsByTagName("EFG");
            visitXml(root, efgNode.item(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int eventId = 0;

    private void visitXml(IAbstractNode parent, Node xmlNode) {
        if (isEventTag(xmlNode)) {
            IAbstractNode newNode = new EfgEvent();
            newNode.getProperties().addAll(getAllPropertiesInEventTag(xmlNode));
            parent.addChild(newNode);

            ((EfgEvent) newNode).setIndex(eventId++);
        } else {
            IAbstractNode newNode = null;
            switch (xmlNode.getNodeName()) {
                case "EFG":
                    newNode = new EfgNode();
                    break;

                case "Events":
                    newNode = new EfgEvents();
                    break;

                case "EventGraph":
                    newNode = new EfgEventGraph();
                    break;

                case "#text":
                    break;

                default:
                    newNode = new EfgUnknownNode();
                    break;
            }

            if (newNode != null) {
                if (newNode instanceof EfgEventGraph) {
                    initializeEventMatrixInEventGraphTag((NodeList) xmlNode);
                } else {
                    parent.addChild(newNode);
                    for (int i = 0; i < ((NodeList) xmlNode).getLength(); i++) {
                        visitXml(newNode, ((NodeList) xmlNode).item(i));
                    }
                }
            }
        }
    }

    private void initializeEventMatrixInEventGraphTag(NodeList node) {
        // STEP 1. Get matrix
        int hang = 0;

        for (int i = 0; i < node.getLength(); i++) {
            NodeList rows = (NodeList) node.item(i);
            int cot = 0;

            boolean hasData = false;
            for (int j = 0; j < rows.getLength(); j++) {
                Node cell = rows.item(j);

                if (cell != null && !cell.getNodeName().equals("#text")) {
                    eventMatrix.setValue(hang, cot, Integer.parseInt(cell.getTextContent()));
                    cot++;
                    hasData = true;
                }
            }

            if (hasData) {
                hang++;
            }
        }
        eventMatrix.setEventMatrixSize(hang);
        // STEP 2. Get header
        List<INode> efgEvents = Search.searchNodes(root, new EventNodeCondition());

        List<EfgEvent> efgEventsCast = new ArrayList<>();
        for (INode efgEvent : efgEvents) {
            efgEventsCast.add((EfgEvent) efgEvent);
        }
        eventMatrix.setEfgEvents(efgEventsCast);
    }

    /**
     * <pre>
     * <Event>
     * <EventId>e0</EventId>
     * <WidgetId>w11</WidgetId>
     * <Type>EXPAND</Type>
     * <Initial>true</Initial>
     * <Action>edu.umd.cs.guitar.event.JFCSelectionHandler</Action>
     * </Event>
     * </pre>
     *
     * @param node is an event
     * @return
     */
    private List<Property> getAllPropertiesInEventTag(Node node) {
        List<Property> properties = new ArrayList<>();

        // for each property
        for (int i = 0; i < ((NodeList) node).getLength(); i++) {
            Node item = ((NodeList) node).item(i);

            if (item.getNodeName().toLowerCase().equals(IEfgPropertyName.EVENT_ID.toLowerCase())) {
                properties.add(new Property(IEfgPropertyName.EVENT_ID, item.getTextContent()));
            } else if (item.getNodeName().toLowerCase().equals(IEfgPropertyName.ACTION.toLowerCase())) {
                properties.add(new Property(IEfgPropertyName.ACTION, item.getTextContent()));
            } else if (item.getNodeName().toLowerCase().equals(IEfgPropertyName.INITIAL.toLowerCase())) {
                properties.add(new Property(IEfgPropertyName.INITIAL, item.getTextContent()));
            } else if (item.getNodeName().toLowerCase().equals(IEfgPropertyName.TYPE.toLowerCase())) {
                properties.add(new Property(IEfgPropertyName.TYPE, item.getTextContent()));
            } else if (item.getNodeName().toLowerCase().equals(IEfgPropertyName.WIDGET_ID.toLowerCase())) {
                properties.add(new Property(IEfgPropertyName.WIDGET_ID, item.getTextContent()));
            }
        }
        return properties;
    }

    private boolean isEventTag(Node xmlNode) {
        return xmlNode.getNodeName().equals("Event");
    }

    public File getEfgFile() {
        return efgFile;
    }

    public void setEfgFile(File efgFile) {
        this.efgFile = efgFile;
    }

    public MatrixEvent getEventMatrix() {
        return eventMatrix;
    }

    public void setEventMatrix(MatrixEvent eventMatrix) {
        this.eventMatrix = eventMatrix;
    }

    public void setRoot(GuiRootNode root) {
        this.root = root;
    }

    public GuiRootNode getRoot() {
        return root;
    }

}
