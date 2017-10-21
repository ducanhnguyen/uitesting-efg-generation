package guitreeparser.object;

/**
 *
 * @author ducanhnguyen
 */
public class GuiWidgetNode extends AbstractGuiNode {

    /**
     * Ex: [GuiWidgetNode] [(ID,w139), (Class,javax.swing.JPanel), (Type,SYSTEM
     * INTERACTION), (Title,null.glassPane), (x,0), (y,0), (width,323),
     * (height,119), (opaque,false),
     * (background,javax.swing.plaf.ColorUIResource[r=238,g=238,b=238]),
     * (focusable,true),
     * (font,javax.swing.plaf.FontUIResource[family=Dialog,name=Dialog,style=plain,size=12]),
     * (foreground,sun.swing.PrintColorUIResource[r=51,g=51,b=51]),
     * (visible,false), (enabled,true)]
     *
     * @return
     */
    @Override
    public String getNameInXPath() {
        String name = "";
        /**
         * STEP 1.
         */
        // Ex: javax.swing.JPanel
        String className = getValueOfProperty(IGuiNodePropertyName.CLASS);

        // Some widgets is not detected its variable name by GUITAR
        if (className.length() > 0) {
            name = className.replace("javax.swing.plaf.metal.", "").replace("javax.swing.", "");
        } else {
            name = "?";
        }
        /**
         * STEP 2. Add more information to the X-path
         */
        String[] supportedTitleWidgets = new String[]{
            "javax.swing.JMenuItem", "javax.swing.JButton", "javax.swing.JLabel", "javax.swing.JDialog"
        };
        for (String supportedTitleWidget : supportedTitleWidgets) {
            if (supportedTitleWidget.equals(className)) {

                String title = getValueOfProperty(IGuiNodePropertyName.TITLE);
                if (title.length() > 0 && !title.startsWith("Pos(") && !title.startsWith("null.")) {
                    name += "[title=" + title + "]";
                }
            }
        }
        return name;
    }
}
