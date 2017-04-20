package edu.rit.swen383_800_g2;

import edu.rit.swen838_800_g2.View.FourUpMode;
import edu.rit.swen838_800_g2.View.OneUpMode;
import edu.rit.swen838_800_g2.View.ViewMode;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rachelpoturich
 */
public class ImageAlbum2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame mainFrame = new JFrame();
        String view;
        view = JOptionPane.showInputDialog(null, "Enter 1 or 4");
        ViewMode viewPanel = null;
        
        if(view.equals("1")){
            viewPanel = new OneUpMode();
        } else if(view.equals("4")){
            viewPanel = new FourUpMode();
        }
        
        JScrollPane scroller = new JScrollPane(viewPanel);
        mainFrame.add(scroller, BorderLayout.CENTER);
        
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
