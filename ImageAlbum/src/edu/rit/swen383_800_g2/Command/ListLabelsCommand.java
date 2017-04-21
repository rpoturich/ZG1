/*
 * Command to list the labels associated with an ImgComponent
 *
 * Pattern: Command
 */

package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 *
 * @author rachelpoturich
 */
public class ListLabelsCommand implements Command{

    private ImgComponent ic;
    
    public ListLabelsCommand(ImgComponent i){
        ic = i;
    }
    
    @Override
    public void execute() {
        String msg = "";
        
        for(String s : ic.getLabels()){
            msg += s + "\n";
        }
        JOptionPane.showMessageDialog(null, "Labels:\n" + msg);
    }

    @Override
    public BufferedImage[] getImg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
