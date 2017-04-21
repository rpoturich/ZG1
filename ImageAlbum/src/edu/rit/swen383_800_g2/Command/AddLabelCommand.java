/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author rachelpoturich
 */
public class AddLabelCommand implements Command {

    private ImgComponent ic;
    private JTextField labelBox;
    
    public AddLabelCommand(ImgComponent i, JTextField field){
        ic = i;
        labelBox = field;
    }
    
    public void execute() {
        String text = labelBox.getText();
        ic.addLabel(text);
        labelBox.setText("");
    }

    @Override
    public BufferedImage[] getImg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
