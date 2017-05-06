/*
 * Album class, part of the ImgComponent
 * Pattern: Composite
 *
 * This class represents an Album, which can contain
 * all types of ImgComponents
 */
package edu.rit.swen383_800_g2.Composite;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Album implements ImgComponent {

    private ArrayList<String> labels = new ArrayList();
    private File f;
    private ArrayList<ImgComponent> components = new ArrayList<>();
    private JLabel iconLabel;
    private ImageIcon smallIcon;
    private ImageIcon largeIcon;
    private BufferedImage image;
    private JPanel iconPanel;
    private JLabel imgLabel;
    private String name;

    
    /**
     * Constructor
     * @param _name name of the album
     * @param fileName  file name to read image 
     */
    public Album(String _name, String fileName) {

        //read and set icon for album
        f = new File(fileName);
        name = _name;

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

    
    /* OVERRIDES */
    
    @Override
    public void addLabel(String label) {
        System.out.println(label);
        System.out.println(labels.size());
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

    @Override
    public JPanel getSmallIcon() {
        iconLabel.setIcon(smallIcon);
        return iconPanel;
    }

    @Override
    public JPanel getLargeIcon() {
        iconLabel.setIcon(largeIcon);
        return iconPanel;
    }

    @Override
    public void setName(String _name) {
        name = _name;
        iconPanel.remove(imgLabel);
        imgLabel = new JLabel(name);
        iconPanel.add(imgLabel);
    }

    @Override
    public Image getImage() {
    
        return (Image) image;
    }

    @Override
    public String getName() {  
        return name;
    }

    @Override
    public void setImage(BufferedImage im) {
    }
    
    public void list() {

        for (ImgComponent i : components) {

            System.out.println("IMG or ALBUM: " + i);

        }

    }

    public void addComponent(ImgComponent comp) {
        components.add(comp);
    }
    
    public void addComponents(ArrayList<ImgComponent> list){
        components = list;
    }

    public ArrayList<ImgComponent> readAlbum() {
        return components;
    }

    @Override
    public ImgComponent clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
