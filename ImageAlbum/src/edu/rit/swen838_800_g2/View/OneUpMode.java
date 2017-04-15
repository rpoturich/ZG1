/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen838_800_g2.View;

import edu.rit.swen838_800_g2.View.Frames.oneFrame;

/**
 *
 * @author rachelpoturich
 */
public class OneUpMode extends ViewMode{
    
    public OneUpMode(){
        
        oneFrame on = new oneFrame();
         on.setVisible(true);
    }
    
    @Override
    public void loadImages(){}
    
}
