/**
 * Class to represent the window available to the user for editing images
 * EditMode extends ViewMode
 *
 */
package edu.rit.swen838_800_g2.View;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rachelpoturich
 */
public class EditMode extends JFrame {

    ImgComponent img;
    ImgComponent editImg;

    /**
     * Constructor
     *
     * @param _img the image to be manipulated
     */
    public EditMode(ImgComponent _img) {
        img = _img;
        editImg = _img;

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel imgLeftPanel = new JPanel();
        JPanel imgRightPanel = new JPanel();

        imgRightPanel.add(editImg.getLargeIcon());
        mainPanel.add(imgRightPanel, BorderLayout.EAST);

        imgLeftPanel.add(img.getLargeIcon());
        mainPanel.add(imgLeftPanel, BorderLayout.WEST);

        add(mainPanel);
        setVisible(true);

    } //end constructor

} //end class
