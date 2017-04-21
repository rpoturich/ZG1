/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen838_800_g2.View;

import edu.rit.swen383_800_g2.Command.Command;
import edu.rit.swen383_800_g2.Composite.Image;
import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rachelpoturich
 */
public class EditMode extends JFrame{
    
    ImgComponent img;
    ImgComponent editImg;
    
    public EditMode(ImgComponent _img){
        img = _img;
        editImg = _img;
        
        JPanel mainPanel = new JPanel(); 
        JPanel imgLeftPanel = new JPanel();
        JPanel imgRightPanel = new JPanel();
        
        imgLeftPanel.add(img.getLargeIcon());
        mainPanel.add(imgLeftPanel, BorderLayout.WEST);
        
        imgRightPanel.add(editImg.getLargeIcon());
        mainPanel.add(imgRightPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
        
    }
    
    

}
