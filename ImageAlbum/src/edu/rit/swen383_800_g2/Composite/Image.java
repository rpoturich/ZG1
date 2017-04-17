/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Composite;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Image implements ImgComponent {

    private ArrayList<String> labels;
    private File f;
    private JLabel iconLabel;
    private ImageIcon icon;
    private BufferedImage image;
    private JPanel iconPanel;

    public Image(String fileName) {

        f = new File(fileName);
        try {
            image = ImageIO.read(f);
            //System.out.println(f.getName());
            icon = new ImageIcon(image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

            iconPanel = new JPanel(new GridLayout(2, 1));
            iconLabel = new JLabel();
            iconLabel.setIcon(icon);
            JLabel imgLabel = new JLabel("Image " + image.getHeight());
            iconPanel.add(iconLabel);
            iconPanel.add(imgLabel);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public JPanel getIcon(){
        return iconPanel;
    }

    @Override
    public void list() {

    }

    @Override
    public void addLabel(String label) {

        labels.add(label);

    }

    @Override
    public String getLabel(int index) {

        return labels.get(index);

    }

    @Override
    public ArrayList<String> getLabels() {
        return labels;
    }

}
