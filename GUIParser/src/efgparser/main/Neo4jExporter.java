package efgparser.main;

import guitreeparser.main.GUITreeParser;
import guitreeparser.object.AbstractGuiNode;
import guitreeparser.object.EfgEvent;
import guitreeparser.object.IGuiNodePropertyName;
import guitreeparser.object.INode;
import java.io.File;
import java.util.List;
import utils.Search;
import utils.SimpleTreeDisplayer;
import utils.condition.EventNodeCondition;
import utils.condition.GuiNodeCondition;
import guitreeparser.object.IEfgPropertyName;
import utils.Utils;

/**
 *
 * @author ducanhnguyen
 */
public class Neo4jExporter {

    private File guiFile;
    private File efgFile;
    private String neo4jCommand;

    public Neo4jExporter(File guiFile, File efgFile) {
        this.guiFile = guiFile;
        this.efgFile = efgFile;
    }

    public static void main(String[] args) {
        Neo4jExporter exporter = new Neo4jExporter(new File("C:\\Users\\ducanhnguyen\\Desktop\\Project.GUI.xml"),
                new File("C:\\Users\\ducanhnguyen\\Desktop\\Project.EFG.xml"));
        exporter.export();
        Utils.writeContentToFile(exporter.getNeo4jCommand(), "C:\\Users\\ducanhnguyen\\Desktop\\sql.text");
    }

    public void export() {
        // Parse event xml
        EFGParser efgParser = new EFGParser(efgFile.getAbsolutePath());
        efgParser.parseEfgTree();
        INode efgRoot = efgParser.getRoot();

        // Parse gui xml
        GUITreeParser guiParser = new GUITreeParser(guiFile.getAbsolutePath());
        guiParser.parseGUITree();
        INode guiRoot = guiParser.getRoot();

        // Merge gui node with its event
        System.out.println("MERGE");
        List<INode> guiNodes = Search.searchNodes(guiRoot, new GuiNodeCondition());

        for (INode event : Search.searchNodes(efgRoot, new EventNodeCondition())) {
            String idEvent = ((EfgEvent) event).getValueOfProperty(IEfgPropertyName.WIDGET_ID);

            for (INode guiNode : guiNodes) {
                AbstractGuiNode castGuiNode = (AbstractGuiNode) guiNode;
                if (castGuiNode.getValueOfProperty(IGuiNodePropertyName.ID).equals(idEvent)) {
                    castGuiNode.getEvents().add((EfgEvent) event);
                    ((EfgEvent) event).setGuiNode(castGuiNode);
                }
            }
        }

        // Print
        System.out.println("+ EFG\n" + new SimpleTreeDisplayer(efgRoot).getTreeInString());
        System.out.println("+ GUI\n" + new SimpleTreeDisplayer(guiRoot).getTreeInString());

        // create nodes
        neo4jCommand = "";
        List<INode> events = Search.searchNodes(efgRoot, new EventNodeCondition());
        for (INode event : events) {
            EfgEvent cast = (EfgEvent) event;
            // Neo4j does not accept "$" or "." in the label
            String label = cast.getGuiNode().getValueOfProperty(IGuiNodePropertyName.CLASS)
                    .replace("javax.swing.", "").replace("plaf.metal.", "").replace("$", "_").replace(".", "_");
            neo4jCommand += "CREATE " + "(" + cast.getValueOfProperty(IEfgPropertyName.EVENT_ID) + ":" + label
                    + " " + cast.convertPropertiestoNeo4j() + ")\n";
        }

        // create dependencies
        for (int i = 0; i < efgParser.getEventMatrix().getEventMatrixSize(); i++) {
            for (int j = 0; j < efgParser.getEventMatrix().getEventMatrixSize(); j++) {
                // dont export dependency between a node with itself
                if (i != j) {
                    int dependency = efgParser.getEventMatrix().getEventMatrix()[i][j];
                    EfgEvent rowItem = efgParser.getEventMatrix().getEfgEvents().get(i);
                    EfgEvent columnItem = efgParser.getEventMatrix().getEfgEvents().get(j);

                    switch (dependency) {
                        case 0:
                            break;
                        case 1:
                            neo4jCommand += "CREATE (" + columnItem.getValueOfProperty(IEfgPropertyName.EVENT_ID)
                                    + ")-[:next]->("
                                    + rowItem.getValueOfProperty(IEfgPropertyName.EVENT_ID) + ")\n";
                            break;
                        case 2:
                            neo4jCommand += "CREATE (" + rowItem.getValueOfProperty(IEfgPropertyName.EVENT_ID)
                                    + ")-[:next]->("
                                    + columnItem.getValueOfProperty(IEfgPropertyName.EVENT_ID) + ")\n";
                            break;
                    }
                }
            }
        }

        // create "return"
        neo4jCommand += "return ";
        for (EfgEvent event : efgParser.getEventMatrix().getEfgEvents()) {
            neo4jCommand
                    += event.getValueOfProperty(IEfgPropertyName.EVENT_ID) + ",";
        }
        neo4jCommand = neo4jCommand.substring(0, neo4jCommand.length() - 1);
    }

    public File getGuiFile() {
        return guiFile;
    }

    public void setEfgFile(File efgFile) {
        this.efgFile = efgFile;
    }

    public void setGuiFile(File guiFile) {
        this.guiFile = guiFile;
    }

    public File getEfgFile() {
        return efgFile;
    }

    public void setNeo4jCommand(String neo4jCommand) {
        this.neo4jCommand = neo4jCommand;
    }

    public String getNeo4jCommand() {
        return neo4jCommand;
    }

}
