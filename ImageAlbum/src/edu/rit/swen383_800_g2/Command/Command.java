/*
 * Command interface for all commands to be used 
 * by the ViewMode
 *
 * Pattern: Command
 */


package edu.rit.swen383_800_g2.Command;

import java.awt.image.BufferedImage;


public interface Command {
    
    public void execute();
    
    public BufferedImage[] getImg();
    
} //end interface
