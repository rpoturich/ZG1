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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
