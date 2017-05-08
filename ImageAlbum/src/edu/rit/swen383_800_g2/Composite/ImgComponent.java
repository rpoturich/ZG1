/*
 * Image/Album Component Interface
 * Pattern: Composite
 *
 * Provides an interface for all Image components used in the program
 *
 */
package edu.rit.swen383_800_g2.Composite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JPanel;


public interface ImgComponent{
    
    //List of classes to be implemented
    public void list();
    public File getFile();
    public String getTime();
    public ImgComponent  clone();
    public void addLabel(String label);
    public String getLabel(int index);
    public void setName(String name);
    public String getName();
    public ArrayList<String> getLabels();
    public JPanel getSmallIcon();
    public JPanel getLargeIcon();
    public java.awt.Image getImage();
    public void setImage(BufferedImage im);
}
