package utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author ducanhnguyen
 */
public class Utils {

    /**
     * Put in the center of screen
     */
    public static void setCenter(JFrame window) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
    }

    public static void setWindowLayout() {
        // Set cross-platform Java L&F (also called "Metal")
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        };

    }
}
