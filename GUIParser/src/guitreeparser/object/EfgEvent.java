package guitreeparser.object;

/**
 *
 * @author ducanhnguyen
 */
public class EfgEvent extends AbstractEventNode implements IEfgPropertyName {

    private int index;
    private AbstractGuiNode guiNode;

    @Override
    public String toString() {
        if (guiNode != null) {
            return "index = " + index + "; is binded to gui node; property = " + getProperties().toString();
        } else {
            return "index = " + index + "; property = " + getProperties().toString();
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setGuiNode(AbstractGuiNode guiNode) {
        this.guiNode = guiNode;
    }

    public AbstractGuiNode getGuiNode() {
        return guiNode;
    }

    public boolean hasPropertyInGuiNode(String propertyName) {
        for (IProperty property : this.guiNode.getProperties()) {
            if (property.getValue().toLowerCase().length() > 0 && property.getName().toLowerCase().equals(propertyName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private String generateEventAttributeforNeo4jNode(String propertyName) {
        return propertyName + ":'" + getValueOfProperty(propertyName) + "',";
    }

    private String generateGuiAttributeforNeo4jNode(String propertyName) {
        if (hasPropertyInGuiNode(propertyName)) {
            return propertyName + ":'" + guiNode.getValueOfProperty(propertyName) + "',";
        } else {
            return "";
        }
    }

    public String convertPropertiestoNeo4j() {
        String properties = generateGuiAttributeforNeo4jNode(IGuiNodePropertyName.TITLE)
                + generateGuiAttributeforNeo4jNode(IGuiNodePropertyName.CLASS)
                        .replace("javax.swing.plaf.metal.", "").replace("javax.swing.", "")
                + generateEventAttributeforNeo4jNode(IEfgPropertyName.TYPE)
                + generateEventAttributeforNeo4jNode(IEfgPropertyName.EVENT_ID)
                + generateEventAttributeforNeo4jNode(IEfgPropertyName.INITIAL)
                + generateGuiAttributeforNeo4jNode(IGuiNodePropertyName.VISIBLE)
                + generateEventAttributeforNeo4jNode(IEfgPropertyName.ACTION);
        properties += "Xpath:'" + getGuiNode().getXPath() + "'";
        properties = "{" + properties + "}";
        return properties;
    }
}
