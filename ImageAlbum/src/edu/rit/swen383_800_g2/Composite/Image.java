/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Composite;

import java.io.File;


public class Image implements ImgComponent{
    
    private String label;
    private File f;
    
    public Image(String label){
        
        f = new File(label);
        
    }

    @Override
    public void list() {

    }

    public void setLabel(String label){
        
        this.label = label;
        
    }
    
    public String getLabel(){
        
        return label;
        
    }
    
    
}
