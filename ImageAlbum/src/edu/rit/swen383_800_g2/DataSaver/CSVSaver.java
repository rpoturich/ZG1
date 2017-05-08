/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.DataSaver;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.util.ArrayList;

/**
 *
 * @author rachelpoturich
 */
public class CSVSaver implements DataSaver {
    
    private ArrayList<ImgComponent> componentList;
    
    public CSVSaver(ArrayList<ImgComponent> componentList){
    
       this.componentList = componentList;
        
    }

    @Override
    public void save() {

        
        
    }
    
}
