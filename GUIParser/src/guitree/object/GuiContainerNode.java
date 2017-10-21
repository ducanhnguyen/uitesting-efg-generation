package guitree.object;

/**
 *
 * @author ducanhnguyen
 */
public class GuiContainerNode extends AbstractGuiNode {

	/**
	 * Ex: [GuiContainerNode] [(ID,w135),
	 * (Class,studentmanagement.AddNewClassWindow), (Type,SYSTEM INTERACTION),
	 * (Title,Add new class),
	 * (background,javax.swing.plaf.ColorUIResource[r=238,g=238,b=238]),
	 * (opaque,true), (x,799), (y,509), (focusable,true),
	 * (font,java.awt.Font[family=Dialog,name=Dialog,style=plain,size=12]),
	 * (foreground,java.awt.SystemColor[i=9]), (visible,true), (enabled,true),
	 * (width,323), (height,119)]
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
		String[] supportedTitleWidgets = new String[] { "javax.swing.JMenu", "javax.swing.JDialog" };
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