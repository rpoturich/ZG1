/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Composite;

import java.io.File;
import java.util.ArrayList;


public class Album implements ImgComponent {

    private String label;
    private File f;
    private ArrayList<Image> images = new ArrayList<>();
    
    
    public Album(String label){
        
        f = new File(label);
        
    }
    
    public void setLabel(String label){
        
        this.label = label;
        
    }
    
    public String getLabel(){
        
        return label;
    }
    
    public void list(){
        
        for(Image i: images){
            
            System.out.println("IMG: " + i);
            
        }
        
    }
}
