
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    JPanel centerPanel = new JPanel(new GridLayout(4, 4));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ThumbnailTest thumbnailTest = new ThumbnailTest();

    }

    public ThumbnailTest() {

        //    	for (int i = 0 ; i < files.size(); i ++){
        //    		System.out.println(files.get(i).getAbsolutePath());
        //    	}
        for (int i = 0; i < files.size(); i++) {
            imagePath.add(files.get(i).getAbsolutePath());
        }

        JFrame myFrame = new JFrame("Thumbnails");

        try {

            for (int i = 0; i < files.size(); i++) {
                edu.rit.swen383_800_g2.Composite.Image ic;
                ic = new edu.rit.swen383_800_g2.Composite.Image(imagePath.get(i));
                centerPanel.add(ic.getIcon());
                ic.getIcon().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            JLabel picLabel = new JLabel(new ImageIcon(ic.getImage()));
                            //ic.getImage();
                            JOptionPane.showMessageDialog(null, picLabel, "Image", JOptionPane.PLAIN_MESSAGE, null);
                        }
                    }
                });
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        myFrame.add(centerPanel);
        //centerPanel.repaint();
        //System.out.println(image);

        myFrame.setLocationRelativeTo(null);
        myFrame.setSize(600, 600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }

    public void paint(Graphics g) {
        g.drawImage(image, 10, 10, null);
    }

}
