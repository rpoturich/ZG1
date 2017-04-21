/*
 * Changes the name of an ImgComponent
 * Pattern: Command
 */

package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;


/**
 *
 * @author rachelpoturich
 */
public class ChangeNameCommand implements Command{

    private ImgComponent ic;
    private JTextField nameField;
    
    public ChangeNameCommand(ImgComponent c, JTextField _name){
        ic = c;
        nameField = _name;
    }
    
    @Override
    public void execute() {
        String newName = nameField.getText();
        ic.setName(newName);
        nameField.setText("");
    }

    @Override
    public BufferedImage[] getImg() {
        return null;
    }
    
} //end class
