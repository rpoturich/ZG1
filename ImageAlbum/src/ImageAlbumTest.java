/*
 * Test class
 */

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

    Stack<String> comands = new Stack<>();

    Stack<String> redoCommands = new Stack<>();

    BufferedImage image;

    int currentImg = 0;

    JPanel centerPanel;
    JFrame myFrame;

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

        myFrame = new JFrame("Image album");

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
        JButton jbCrop = new JButton("Crop");
        JButton jbUndo = new JButton("Undo");
        JButton jbRedo = new JButton("Redo");

        southPanel.add(jbPrev);
        southPanel.add(jbNext);
        southPanel.add(jbBW);
        southPanel.add(jbRotateLeft);
        southPanel.add(jbRotateRight);
        southPanel.add(jbMirror);
        southPanel.add(jbFlip);
        southPanel.add(jbCrop);
        southPanel.add(jbUndo);
        southPanel.add(jbRedo);

        jbRotateLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                comands.push(jbRotateLeft.getText());

                image = rotateImage(image, 90);
                int drawLocationX = 0;
                int drawLocationY = 0;

// Rotation information
                AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                Graphics2D g2d = image.createGraphics();

// Drawing the rotated image at the required drawing locations
                g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

                centerPanel.repaint();

            }

        });

        jbRotateRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                comands.push(jbRotateRight.getText());

                image = rotateImage(image, -90);
                int drawLocationX = 0;
                int drawLocationY = 0;

// Rotation information
                AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(-90), image.getWidth() / 2, image.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                Graphics2D g2d = image.createGraphics();

// Drawing the rotated image at the required drawing locations
                g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

                centerPanel.repaint();

            }

        });

        jbPrev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                comands.push(jbPrev.getText());

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

                comands.push(jbNext.getText());

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
                    if (!(imagePath.get(currentImg).contains(".pngBlackWhite"))) {

                        comands.push(jbBW.getText());

                        BufferedImage BWimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

                        System.out.println(currentImg);

                        Graphics2D g2d = BWimage.createGraphics();

                        g2d.drawImage(image, 0, 0, null);

                        ImageIO.write(BWimage, "png", new File(imagePath.get(currentImg) + ".pngBlackWhite"));

                        image = ImageIO.read(new File(imagePath.get(currentImg) + ".pngBlackWhite"));

                        imagePath.add(currentImg, imagePath.get(currentImg) + ".pngBlackWhite");
                        imagePath.remove(currentImg + 1);

                        centerPanel.repaint();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ImageAlbumTest.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jbMirror.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                comands.push(jbMirror.getText());

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

                comands.push(jbFlip.getText());

                int height = image.getHeight();

                AffineTransform at = AffineTransform.getScaleInstance(1, -1);

                at.translate(0, -height);

                AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                image = ato.filter(image, null);

                centerPanel.repaint();

            }
        });

        jbCrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                comands.push(jbCrop.getText());

                myFrame.setEnabled(false);

                JFrame newFrame = new JFrame("crop");
                newFrame.setLayout(new BorderLayout());

                JLabel label = new JLabel("X coords: ");
                JLabel label1 = new JLabel("Y coords: ");
                JLabel label2 = new JLabel("Width: ");
                JLabel label3 = new JLabel("Heigth: ");

                JTextField x = new JTextField(5);
                JTextField y = new JTextField(5);
                JTextField width = new JTextField(5);
                JTextField heigth = new JTextField(5);

//                JPanel panelNorth = new JPanel();
//                panelNorth.add(label);
//                panelNorth.add(width);
//
//                newFrame.add(panelNorth, BorderLayout.NORTH);
//
//                JPanel panelCenter = new JPanel();
//                panelCenter.add(label1);
//                panelCenter.add(heigth);
//
//                newFrame.add(panelCenter, BorderLayout.CENTER);
                JPanel panelCenter = new JPanel(new GridLayout(4, 2));
                panelCenter.add(label);
                panelCenter.add(x);
                panelCenter.add(label1);
                panelCenter.add(y);
                panelCenter.add(label2);
                panelCenter.add(width);
                panelCenter.add(label3);
                panelCenter.add(heigth);

                newFrame.add(panelCenter, BorderLayout.NORTH);

                JPanel panelSouth = new JPanel();
                JButton button = new JButton("crop");
                panelSouth.add(button);
                newFrame.add(panelSouth, BorderLayout.SOUTH);

                newFrame.pack();

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        myFrame.setEnabled(true);
                        newFrame.setVisible(false);

                        try {
                            BufferedImage originalImgage = ImageIO.read(new File(imagePath.get(currentImg)));

                            System.out.println("Original Image Dimension: " + originalImgage.getWidth() + "x" + originalImgage.getHeight());

                            BufferedImage SubImgage = originalImgage.getSubimage(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()),
                                    Integer.parseInt(width.getText()), Integer.parseInt(heigth.getText()));
                            System.out.println("Cropped Image Dimension: " + SubImgage.getWidth() + "x" + SubImgage.getHeight());

                            //    File outputfile = new File(imagePath.get(currentImg) + ".pngCropied");
                            ImageIO.write(SubImgage, "png", new File(imagePath.get(currentImg) + ".pngCropied"));

                            image = ImageIO.read(new File(imagePath.get(currentImg) + ".pngCropied"));

                            imagePath.add(currentImg, imagePath.get(currentImg) + ".pngCropied");

                            imagePath.remove(currentImg + 1);

                            centerPanel.repaint();

//sdfgdsfgsdfgsdfgsdfgsdfgdsfgdsfgdsfg
                            System.out.println("Image cropped successfully: " + imagePath.get(currentImg));
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }

                        //Graphics2D g2d = image.createGraphics();
                        //g2d.drawImage(image, 0, 0, Integer.parseInt(width.getText()), Integer.parseInt(heigth.getText()), null);
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

        jbUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lastCommand = comands.pop();
                    System.out.println(lastCommand);

                    redoCommands.add(lastCommand);

                    if (lastCommand.equalsIgnoreCase(jbFlip.getText())) {

                        // THIS IS COPY OF ACTION PERFORMED OF FLIP BUTTON... LATER WE WILL MANIPULATE WITH IT AND STORE IT IN SEPARATE CLASS/METHOD
                        int height = image.getHeight();

                        AffineTransform at = AffineTransform.getScaleInstance(1, -1);

                        at.translate(0, -height);

                        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                        image = ato.filter(image, null);

                        centerPanel.repaint();

                    } else if (lastCommand.equalsIgnoreCase(jbMirror.getText())) {

                        int width = image.getWidth();
                        int height = image.getHeight();

                        Graphics2D g2d = image.createGraphics();

                        g2d.drawImage(image, width, 0, -width, height, null);

                        centerPanel.repaint();

                    } else if (lastCommand.equalsIgnoreCase(jbBW.getText())) {

                        image = ImageIO.read(new File(imagePath.get(currentImg).substring(0, imagePath.get(currentImg).indexOf(".pngBlackWhite"))));

                        imagePath.add(currentImg, imagePath.get(currentImg).substring(0, imagePath.get(currentImg).indexOf(".pngBlackWhite")));
                        imagePath.remove(currentImg + 1);

                        centerPanel.repaint();

                    } else if (lastCommand.equalsIgnoreCase(jbNext.getText())) {

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

                    } else if (lastCommand.equalsIgnoreCase(jbPrev.getText())) {

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

                    } else if (lastCommand.equalsIgnoreCase(jbRotateRight.getText())) {

                        image = rotateImage(image, 90);
                        int drawLocationX = 0;
                        int drawLocationY = 0;

// Rotation information
                        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                        Graphics2D g2d = image.createGraphics();

// Drawing the rotated image at the required drawing locations
                        g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

                        centerPanel.repaint();

                    } else if (lastCommand.equalsIgnoreCase(jbRotateLeft.getText())) {

                        image = rotateImage(image, -90);
                        int drawLocationX = 0;
                        int drawLocationY = 0;

// Rotation information
                        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(-90), image.getWidth() / 2, image.getHeight() / 2);
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                        Graphics2D g2d = image.createGraphics();

// Drawing the rotated image at the required drawing locations
                        g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

                        centerPanel.repaint();

                    }  else if (lastCommand.equalsIgnoreCase(jbCrop.getText())) {
                        
                        image = ImageIO.read(new File(imagePath.get(currentImg).substring(0, imagePath.get(currentImg).indexOf(".pngCropied"))));

                        imagePath.add(currentImg, imagePath.get(currentImg).substring(0, imagePath.get(currentImg).indexOf(".pngCropied")));
                        
                        imagePath.remove(currentImg + 1);
                        
                        centerPanel.repaint();
                        
                    }

                } catch (EmptyStackException esex) {
                    System.out.println("No more commands");
                } catch (IOException ex) {
                    Logger.getLogger(ImageAlbumTest.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jbRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cmdRedo = redoCommands.pop();

                    if (cmdRedo.equalsIgnoreCase(jbFlip.getText())) {

                        comands.push(jbFlip.getText());

                        int height = image.getHeight();

                        AffineTransform at = AffineTransform.getScaleInstance(1, -1);

                        at.translate(0, -height);

                        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                        image = ato.filter(image, null);

                        centerPanel.repaint();

                    } else if (cmdRedo.equalsIgnoreCase(jbMirror.getText())) {

                        comands.push(jbMirror.getText());

                        int width = image.getWidth();
                        int height = image.getHeight();

                        Graphics2D g2d = image.createGraphics();

                        g2d.drawImage(image, width, 0, -width, height, null);

                        centerPanel.repaint();

                    } else if (cmdRedo.equalsIgnoreCase(jbBW.getText())) {

                        try {
                            if (!(imagePath.get(currentImg).contains(".pngBlackWhite"))) {

                                comands.push(jbBW.getText());

                                BufferedImage BWimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

                                System.out.println(currentImg);

                                Graphics2D g2d = BWimage.createGraphics();

                                g2d.drawImage(image, 0, 0, null);

                                ImageIO.write(BWimage, "png", new File(imagePath.get(currentImg) + ".pngBlackWhite"));

                                image = ImageIO.read(new File(imagePath.get(currentImg) + ".pngBlackWhite"));

                                imagePath.add(currentImg, imagePath.get(currentImg) + ".pngBlackWhite");
                                imagePath.remove(currentImg + 1);

                                centerPanel.repaint();
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ImageAlbumTest.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (cmdRedo.equalsIgnoreCase(jbNext.getText())) {

                        comands.push(jbNext.getText());

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

                    } else if (cmdRedo.equalsIgnoreCase(jbPrev.getText())) {

                        comands.push(jbPrev.getText());

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

                    } else if (cmdRedo.equalsIgnoreCase(jbRotateLeft.getText())) {

                        comands.push(jbRotateLeft.getText());

                        image = rotateImage(image, 90);
                        int drawLocationX = 0;
                        int drawLocationY = 0;

                        // Rotation information
                        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                        Graphics2D g2d = image.createGraphics();

                        // Drawing the rotated image at the required drawing locations
                        g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

                        centerPanel.repaint();

                    } else if (cmdRedo.equalsIgnoreCase(jbRotateRight.getText())) {

                        comands.push(jbRotateRight.getText());

                        image = rotateImage(image, -90);
                        int drawLocationX = 0;
                        int drawLocationY = 0;

                        // Rotation information
                        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(-90), image.getWidth() / 2, image.getHeight() / 2);
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                        Graphics2D g2d = image.createGraphics();

                        // Drawing the rotated image at the required drawing locations
                        g2d.drawImage(op.filter(image, null), drawLocationX, drawLocationY, null);

                        centerPanel.repaint();

                    } else if (cmdRedo.equalsIgnoreCase(jbCrop.getText())) {

                        comands.push(jbCrop.getText());

                        myFrame.setEnabled(false);

                        JFrame newFrame = new JFrame("crop");
                        newFrame.setLayout(new BorderLayout());

                        JLabel label = new JLabel("X coords: ");
                        JLabel label1 = new JLabel("Y coords: ");
                        JLabel label2 = new JLabel("Width: ");
                        JLabel label3 = new JLabel("Heigth: ");

                        JTextField x = new JTextField(5);
                        JTextField y = new JTextField(5);
                        JTextField width = new JTextField(5);
                        JTextField heigth = new JTextField(5);

//                JPanel panelNorth = new JPanel();
//                panelNorth.add(label);
//                panelNorth.add(width);
//
//                newFrame.add(panelNorth, BorderLayout.NORTH);
//
//                JPanel panelCenter = new JPanel();
//                panelCenter.add(label1);
//                panelCenter.add(heigth);
//
//                newFrame.add(panelCenter, BorderLayout.CENTER);
                        JPanel panelCenter = new JPanel(new GridLayout(4, 2));
                        panelCenter.add(label);
                        panelCenter.add(x);
                        panelCenter.add(label1);
                        panelCenter.add(y);
                        panelCenter.add(label2);
                        panelCenter.add(width);
                        panelCenter.add(label3);
                        panelCenter.add(heigth);

                        newFrame.add(panelCenter, BorderLayout.NORTH);

                        JPanel panelSouth = new JPanel();
                        JButton button = new JButton("crop");
                        panelSouth.add(button);
                        newFrame.add(panelSouth, BorderLayout.SOUTH);

                        newFrame.pack();

                        button.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {

                                myFrame.setEnabled(true);
                                newFrame.setVisible(false);

                                try {
                                    BufferedImage originalImgage = ImageIO.read(new File(imagePath.get(currentImg)));

                                    System.out.println("Original Image Dimension: " + originalImgage.getWidth() + "x" + originalImgage.getHeight());

                                    BufferedImage SubImgage = originalImgage.getSubimage(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()),
                                            Integer.parseInt(width.getText()), Integer.parseInt(heigth.getText()));
                                    System.out.println("Cropped Image Dimension: " + SubImgage.getWidth() + "x" + SubImgage.getHeight());

                                    //    File outputfile = new File(imagePath.get(currentImg) + ".pngCropied");
                                    ImageIO.write(SubImgage, "png", new File(imagePath.get(currentImg) + ".pngCropied"));

                                    image = ImageIO.read(new File(imagePath.get(currentImg) + ".pngCropied"));

                                    imagePath.add(currentImg, imagePath.get(currentImg) + ".pngCropied");

                                    imagePath.remove(currentImg + 1);

                                    centerPanel.repaint();

//sdfgdsfgsdfgsdfgsdfgsdfgdsfgdsfgdsfg
                                    System.out.println("Image cropped successfully: " + imagePath.get(currentImg));
                                } catch (IOException ioe) {
                                    ioe.printStackTrace();
                                }

                                //Graphics2D g2d = image.createGraphics();
                                //g2d.drawImage(image, 0, 0, Integer.parseInt(width.getText()), Integer.parseInt(heigth.getText()), null);
                                centerPanel.repaint();

                            }

                        });

                        newFrame.setLocationRelativeTo(null);
                        newFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        newFrame.setVisible(true);

                    }
                } catch (EmptyStackException esex) {
                    System.out.println("No more redo");
                }
            }
        });
        centerPanel.repaint();
        System.out.println(image);

        myFrame.setLocationRelativeTo(null);
        myFrame.setSize(800, 500);
        myFrame.setDefaultCloseOperation(closing());
        myFrame.setVisible(true);

    }

    public void paint(Graphics g) {
        g.drawImage(image, 10, 10, null);
    }

    public BufferedImage rotateImage(BufferedImage image, double degreesAngle) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage result = new BufferedImage(w, h, image.getType());
        Graphics2D g2 = result.createGraphics();
        g2.setColor(myFrame.getBackground());
        g2.fillRect(0, 0, w, h);
        g2.rotate(Math.toRadians(degreesAngle), w / 2, h / 2);
        g2.drawImage(image, null, 0, 0);

        return result;
    }

    public int closing() {

        ArrayList<String> imagePathToDelete = new ArrayList<>();

        for (int i = 0; i < imagePath.size(); i++) {

            if (imagePath.get(i).contains(".pngBlackWhite") || imagePath.get(i).contains(".pngCropied")) {
                imagePathToDelete.add(imagePath.get(i));
                imagePath.remove(i);
            }

        }

        for (int i = 0; i < imagePathToDelete.size(); i++) {

            new File(imagePathToDelete.get(i)).delete();

        }

        return JFrame.EXIT_ON_CLOSE;
    }

    
}
