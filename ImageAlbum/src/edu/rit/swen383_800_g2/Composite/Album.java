/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Composite;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Album implements ImgComponent {

    private ArrayList<String> labels;
    private File f;
    private ArrayList<ImgComponent> components = new ArrayList<>();
    private String name;
    
    
    public Album(String fileName){
        
        f = new File(fileName);
        
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
        
        for(ImgComponent i: components){
            
            System.out.println("IMG or ALBUM: " + i);
            
        }
        
    }
    
    public void addComponent(ImgComponent comp){
        components.add(comp);
    }
    
    
    public ArrayList<ImgComponent> readAlbum(){
        return components;
    }
    
    @Override
    public JPanel getSmallIcon(){
        return new JPanel();
    }
    
    @Override
    public JPanel getLargeIcon(){
        return new JPanel();
    }
    
    public void setName(String _name){
        name = _name;
    }
    
    public String getName(){
        return name;
    }
}
