package guitreeparser.object;

/**
 *
 * @author ducanhnguyen
 */
public interface IGuiNodePropertyName {

    static final String ID = "ID";
    
    // Eg., javax.swing.JDialog
    static final String CLASS = "Class";

    // Eg., SYSTEM INTERACTION, TERMINAL
    static final String TYPE = "Type";

    // Eg., Error
    static final String TITLE = "Title";

    // Eg., javax.swing.plaf.ColorUIResource[r=238,g=238,b=238]
    static final String BACKGROUND = "background";

    // true/false
    static final String OPAQUE = "opaque";

    // location
    static final String X = "x";

    // location
    static final String Y = "y";

    // true/false
    static final String FOCUSABLE = "focusable";

    // e.g., java.awt.Font[family=Dialog,name=Dialog,style=plain,size=12]
    static final String FONT = "font";

    // e.g., java.awt.SystemColor[i=9]
    static final String FOREGROUND = "foreground";

    // true/false
    static final String VISIBLE = "visible";

    // true/false
    static final String ENABLED = "enabled";

    // size
    static final String WIDTH = "width";

    //size
    static final String HEIGHT = "height";

    // ?
    static final String REPLAYABLEACTION = "ReplayableAction";

    static final String MODAL = "Modal";

    static final String ROOTWINDOW = "Rootwindow";

    static final String ICON = "Icon";

    static final String INVOKELIST = "Invokelist";
}
