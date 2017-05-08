/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.DataSaver;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.io.File;
import java.io.PrintWriter;
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

        PrintWriter pw = null;
        
        for(ImgComponent c : componentList) {
            
            try{
                pw = new PrintWriter(new File("imageData.csv"));
            } catch (Exception e) {
                
            }
                
            if(c instanceof edu.rit.swen383_800_g2.Composite.Image){
            
                String info = "";
                
                info += c.getName();
                info += ",";
                info += c.getFile().getPath();
                info += ",";
                info += c.getTime();
                info += ",";
                
                ArrayList<String> labels = c.getLabels();
                
                for(String l: labels){
                    info += l + ",";
                }
                
                info += "\n";
                
                pw.write(info);
            }
        }
        
        pw.close();
        
    }
    
}
