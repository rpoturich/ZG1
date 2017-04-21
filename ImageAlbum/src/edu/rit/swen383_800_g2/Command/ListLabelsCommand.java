/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.image.BufferedImage;

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
        System.out.println(ic.getLabels());
    }

    @Override
    public BufferedImage[] getImg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
