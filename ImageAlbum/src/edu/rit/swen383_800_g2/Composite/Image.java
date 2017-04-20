/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Composite;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Image implements ImgComponent {

    private ArrayList<String> labels = new ArrayList();
    private File f;
    private JLabel iconLabel;
    private ImageIcon smallIcon;
    private ImageIcon largeIcon;
    private BufferedImage image;
    private JPanel iconPanel;
    private JLabel imgLabel;
    private String name;

    public Image(String fileName) {

        f = new File(fileName);
        try {
            image = ImageIO.read(f);
            
            smallIcon = new ImageIcon(image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
            largeIcon = new ImageIcon(image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
            
            iconPanel = new JPanel(new GridLayout(2, 1));
            iconLabel = new JLabel();
            iconLabel.setIcon(smallIcon);
            imgLabel = new JLabel(name);
            iconPanel.add(iconLabel);
            iconPanel.add(imgLabel);
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public JPanel getSmallIcon(){
        iconLabel.setIcon(smallIcon);
        return iconPanel;
    }
    
    public JPanel getLargeIcon(){
        iconLabel.setIcon(largeIcon);
        return iconPanel;
    }
    
    public void setName(String _name){
        name = _name;
        iconPanel.remove(imgLabel);
        imgLabel = new JLabel(name);
        iconPanel.add(imgLabel);
    }
    
    public String getName(){
        return name;
    }
    
    public java.awt.Image getImage(){
        
        java.awt.Image img = image.getScaledInstance((int)(image.getWidth()*0.3), (int)(image.getHeight()*0.3), java.awt.Image.SCALE_SMOOTH);
        return img;
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

    public BufferedImage[] resize(int columns, ArrayList<BufferedImage> imgList) {
        
        BufferedImage [] resized = new BufferedImage[imgList.size()];
        
        int width = 0;
        int height = 0;
        
        if(columns == 1){
            
            width = 650;
            height = 680;
               
        }
        else if(columns == 2){
            
            width = 300;
            height = 330;
            
        }
        for (int i = 0; i < imgList.size(); i++){
    
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
            
            Graphics g = bi.createGraphics();
            g.drawImage(imgList.get(i), 0, 0, width, height, null);
            resized[i] = bi;
        }
        
        return resized;
    }

}
