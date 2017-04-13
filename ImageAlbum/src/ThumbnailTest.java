
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rachelpoturich
 */
public class ThumbnailTest {

    File f = new File("images");
    ArrayList<String> imagePath = new ArrayList<String>();

    ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));

    BufferedImage image;
    int currentImg = 0;
    
    JPanel centerPanel = new JPanel(new GridLayout(4,4));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ThumbnailTest();

    }

    public ThumbnailTest() {

        //    	for (int i = 0 ; i < files.size(); i ++){
        //    		System.out.println(files.get(i).getAbsolutePath());
        //    	}
        for (int i = 0; i < files.size(); i++) {
            imagePath.add(files.get(i).getAbsolutePath());
        }

        try {
            image = ImageIO.read(new File(imagePath.get(currentImg)));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JFrame myFrame = new JFrame("Thumbnails");
        
        /*
        centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0, 0, null);
            }
        };*/


        try {
            
            for(int i = 0; i < files.size(); i++){
                Image im = ImageIO.read(new File(imagePath.get(i)));
                im = im.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(im);
                JLabel label = new JLabel();
                label.setIcon(icon);
                centerPanel.add(label);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        myFrame.add(centerPanel);
        //centerPanel.repaint();
        //System.out.println(image);

        myFrame.setLocationRelativeTo(null);
        myFrame.setSize(500, 500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }

    public void paint(Graphics g) {
        g.drawImage(image, 10, 10, null);
    }

}
