/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Command;

import java.awt.image.BufferedImage;


public interface Command {
    
    public void execute();
    public BufferedImage[] getImg();
    
}