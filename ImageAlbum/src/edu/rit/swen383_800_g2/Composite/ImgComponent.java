/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Composite;

import java.util.ArrayList;



public interface ImgComponent{
    
    public void list();
    public void addLabel(String label);
    public String getLabel(int index);
    public ArrayList<String> getLabels();
}
