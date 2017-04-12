/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Composite;

import java.io.File;
import java.util.ArrayList;


public class Album implements ImgComponent {

    private ArrayList<String> labels;
    private File f;
    private ArrayList<Image> images = new ArrayList<>();
    
    
    public Album(String label){
        
        f = new File(label);
        
    }
    
    @Override
    public void addLabel(String label){
        
        labels.add(label);
        
    }
    
    @Override
    public String getLabel(int index){
        
        return labels.get(index);
    }
    
    @Override
    public ArrayList<String> getLabels(){
        return labels;
    }
    
    public void list(){
        
        for(Image i: images){
            
            System.out.println("IMG: " + i);
            
        }
        
    }
}
