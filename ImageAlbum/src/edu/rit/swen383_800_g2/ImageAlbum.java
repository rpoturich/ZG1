/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Student
 */
public class ImageAlbum extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ImageAlbum album = new ImageAlbum();    
        
    }
    
    public ImageAlbum(){
        
        //Initial set up
        setTitle("G2 Image Album");
        setSize(800, 800);
        
        JPanel menu = new JPanel();
        
        JPanel mainPanel = new JPanel();
        JFrame picFrame = new JFrame();
        picFrame.setPreferredSize(new Dimension(300, 300));
        
        
        //South Panel and Buttons
        JPanel southPanel = new JPanel(new FlowLayout());
        
        JButton button = new JButton("NEXT");
        JButton button1 = new JButton("PREVIOUS");
        
        southPanel.add(button);
        southPanel.add(button1);
        
        add(mainPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        
        
        //Menu bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuView = new JMenu("View");
        JMenu menuFile = new JMenu("File");
        JMenu menuSearch = new JMenu("Search");
        JMenu menuAbout = new JMenu("About");
        JMenu menuExit = new JMenu("Exit");
        
        JMenuItem itemView = new JMenuItem("1 - up");
        JMenuItem itemView2 = new JMenuItem("2 - up");
        
        JMenuItem itemSearch = new JMenuItem("Search by label");
        
        JMenuItem itemInfo = new JMenuItem("Info");
        
        JMenuItem itemOpen = new JMenuItem("Open");
        JMenuItem itemSave = new JMenuItem("Save");
        
        JMenuItem itemExit = new JMenuItem("Close");
        
        menuExit.add(itemExit);
        
        menuView.add(itemView);
        menuView.add(itemView2);
        
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        
        menuSearch.add(itemSearch);
        
        menuAbout.add(itemInfo);
        
        menuBar.add(menuView);
        menuBar.add(menuFile);
        menuBar.add(menuSearch);
        menuBar.add(menuAbout);
        menuBar.add(menuExit);
        
        menu.add(menuBar);
        add(menu, BorderLayout.NORTH);
        
        itemInfo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Image Project createad by:\n "
                        + "Rachel Poturich\n"
                        + "Marko Zivko\n"
                        + "Mirko Siljeg\n"
                        + "Divo Lise");

            }
            
        });
        
        itemExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
                
            }
        
        
        
        });
        
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    
}
