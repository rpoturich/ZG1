/*
 * Command to add a label to an ImgComponent
 * Pattern: Command
 */

package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.image.BufferedImage;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author rachelpoturich
 */
public class AddLabelCommand implements Command {

    private ImgComponent ic;
    private JTextField labelBox;
    private JComboBox labelCombo;
    private String label;
    
    public AddLabelCommand(ImgComponent i, JTextField field){
        ic = i;
        labelBox = field;
        label = labelBox.getText();
    }
    
    public AddLabelCommand(ImgComponent i, JComboBox box, JTextField field){
        ic = i;
        labelCombo = box;
        labelBox = field;
        label = (String) labelCombo.getSelectedItem();
    }
    
    public void execute() {
        ic.addLabel(label);
        labelBox.setText("");
    }

   
}
