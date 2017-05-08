/*
 * Image class, part of the ImgComponent
 * Pattern: Composite
 *
 * This class represents an Image
 */
package edu.rit.swen383_800_g2.Composite;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Image implements ImgComponent {

    private ArrayList<String> labels = new ArrayList(); //use this to get labels
    private File f; //use this to get path
    private JLabel iconLabel;
    private ImageIcon smallIcon;
    private ImageIcon largeIcon;
    private BufferedImage image;
    private JPanel iconPanel;
    private JLabel imgLabel;
    private JLabel timeLabel;
    private String name;

    /**
     * Constructor
     * 
     * @param fileName  name of the file to read 
     */
    public Image(String fileName) {

        //read a file and set attributes
        f = new File(fileName);
        try {
            image = ImageIO.read(f);

            System.out.println(image.toString());
            
            smallIcon = new ImageIcon(image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
            largeIcon = new ImageIcon(image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));

            iconPanel = new JPanel(new GridLayout(3, 1));
            iconLabel = new JLabel();
            iconLabel.setIcon(smallIcon);
            imgLabel = new JLabel(name);

            //add date
            DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date date = new Date();
            timeLabel = new JLabel(format.format(date));
            
            iconPanel.add(iconLabel);
            iconPanel.add(imgLabel);
            iconPanel.add(timeLabel);

        } catch (Exception e) {
            e.printStackTrace();
        }

    } //end constructor

    
    /* GETTERS */
    
    public JPanel getSmallIcon() {
        iconLabel.setIcon(smallIcon);
        return iconPanel;
    }

    public JPanel getLargeIcon() {
        iconLabel.setIcon(largeIcon);
        return iconPanel;
    }

    public String getName() {
        return name;
    }

    @Override
    public java.awt.Image getImage() {

        java.awt.Image img = image.getScaledInstance((int) (image.getWidth() * 0.3), (int) (image.getHeight() * 0.3), java.awt.Image.SCALE_SMOOTH);
        return img;
    }
    
    @Override
    public String getLabel(int index) {

        return labels.get(index);

    }

    @Override
    public ArrayList<String> getLabels() {
        return labels;
    }

    
    public File getFile(){
        return f;
    }
    
    public String getTime(){
        return timeLabel.getText();
    }
    
    public void setName(String _name) {
        name = _name;
        iconPanel.remove(imgLabel);
        imgLabel = new JLabel(name);
        iconPanel.add(imgLabel);
    }
    
    /* SETTERS */
    
    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    @Override
    public void list() {

    }

    @Override
    public void addLabel(String label) {

        labels.add(label);

    }

    public BufferedImage[] resize(int columns, ArrayList<BufferedImage> imgList) {

        BufferedImage[] resized = new BufferedImage[imgList.size()];

        int width = 0;
        int height = 0;

        if (columns == 1) {

            width = 650;
            height = 680;

        } else if (columns == 2) {

            width = 300;
            height = 330;

        }
        for (int i = 0; i < imgList.size(); i++) {

            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics g = bi.createGraphics();
            g.drawImage(imgList.get(i), 0, 0, width, height, null);
            resized[i] = bi;
        }

        return resized;
    }

    @Override
    public ImgComponent clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

} //end class
