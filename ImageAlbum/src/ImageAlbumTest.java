/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package eclecl;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Student
 */
public class ImageAlbumTest {

    File f = new File("images");
    ArrayList<String> imagePath = new ArrayList<String>();

    ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));

    BufferedImage image;

    int currentImg = 0;

    JPanel centerPanel;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ImageAlbumTest();
    }

    public ImageAlbumTest() {

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

        JFrame myFrame = new JFrame("Image album");

        myFrame.setLayout(new BorderLayout());

        centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, (int) (image.getWidth() * 0.3), (int) (image.getHeight() * 0.3), null);
            }
        };

        JPanel southPanel = new JPanel(new FlowLayout());

        myFrame.add(centerPanel, BorderLayout.CENTER);
        myFrame.add(southPanel, BorderLayout.SOUTH);

        JButton jbNext = new JButton("Next >");
        JButton jbPrev = new JButton("< Prev");
        JButton jbBW = new JButton("black and white");
        JButton jbRotateLeft = new JButton("rotate left");
        JButton jbRotateRight = new JButton("rotate right");
        JButton jbMirror = new JButton("mirror");
        JButton jbFlip = new JButton("flip");
        JButton jbResize = new JButton("resize");

        southPanel.add(jbPrev);
        southPanel.add(jbNext);
        southPanel.add(jbBW);
        southPanel.add(jbRotateLeft);
        southPanel.add(jbRotateRight);
        southPanel.add(jbMirror);
        southPanel.add(jbFlip);
        southPanel.add(jbResize);

        jbPrev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                currentImg--;
                if (currentImg < 0) {
                    currentImg = imagePath.size() - 1;
                }

                try {
                    image = ImageIO.read(new File(imagePath.get(currentImg)));

                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
                System.out.println(currentImg);

                centerPanel.repaint();

            }
        });

        jbNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                currentImg++;

                if (currentImg > imagePath.size() - 1) {
                    currentImg = 0;
                }

                try {
                    image = ImageIO.read(new File(imagePath.get(currentImg)));

                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
                System.out.println(currentImg);

                centerPanel.repaint();

            }
        });

        jbBW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    BufferedImage BWimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

                    System.out.println(currentImg);

                    Graphics2D g2d = BWimage.createGraphics();

                    g2d.drawImage(image, 0, 0, null);

                    ImageIO.write(BWimage, "png", new File(imagePath.get(currentImg)));

                    image = ImageIO.read(new File(imagePath.get(currentImg)));

                    centerPanel.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(ImageAlbumTest.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jbRotateLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        jbMirror.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int width = image.getWidth();
                int height = image.getHeight();

                Graphics2D g2d = image.createGraphics();

                g2d.drawImage(image, width, 0, -width, height, null);

                centerPanel.repaint();

            }
        });

        jbFlip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int height = image.getHeight();

                AffineTransform at = AffineTransform.getScaleInstance(1, -1);

                at.translate(0, -height);

                AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                image = ato.filter(image, null);

                centerPanel.repaint();

            }
        });

        jbResize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myFrame.setEnabled(false);

                JFrame newFrame = new JFrame("resize");
                newFrame.setLayout(new BorderLayout());

                JLabel label = new JLabel("Width: ");
                JLabel label1 = new JLabel("Heigth: ");

                JTextField width = new JTextField(5);
                JTextField heigth = new JTextField(5);

                JPanel panelNorth = new JPanel();
                panelNorth.add(label);
                panelNorth.add(width);

                newFrame.add(panelNorth, BorderLayout.NORTH);

                JPanel panelCenter = new JPanel();
                panelCenter.add(label1);
                panelCenter.add(heigth);

                newFrame.add(panelCenter, BorderLayout.CENTER);

                JPanel panelSouth = new JPanel();
                JButton button = new JButton("Resize");
                panelSouth.add(button);
                newFrame.add(panelSouth, BorderLayout.SOUTH);

                newFrame.pack();

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        myFrame.setEnabled(true);
                        newFrame.setVisible(false);

                        Graphics2D g2d = image.createGraphics();

                        g2d.drawImage(image, 0, 0, Integer.parseInt(width.getText()), Integer.parseInt(heigth.getText()), null);

                        centerPanel.repaint();

                        
                        

                    }

                });

                newFrame.setLocationRelativeTo(null);
                newFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                newFrame.setVisible(true);
            }
        });

        try {
            image = ImageIO.read(new File(imagePath.get(0)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        centerPanel.repaint();
        System.out.println(image);

        myFrame.setLocationRelativeTo(null);
        myFrame.setSize(500, 500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }

    public void paint(Graphics g) {
        g.drawImage(image, 10, 10, null);
    }

}
