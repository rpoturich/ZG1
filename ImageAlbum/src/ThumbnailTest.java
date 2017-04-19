
import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

        //myFrame.add(buttonPanel, BorderLayout.WEST);
        ArrayList<ImgComponent> components = new ArrayList();
        final edu.rit.swen383_800_g2.Composite.Image currentImage;

        try {

            for (int i = 0; i < files.size(); i++) {
                edu.rit.swen383_800_g2.Composite.Image ic;
                ic = new edu.rit.swen383_800_g2.Composite.Image(imagePath.get(i));
                centerPanel.add(ic.getIcon());
                components.add(ic);
                ic.getIcon().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            JLabel picLabel = new JLabel(new ImageIcon(ic.getImage()));
                            //ic.getImage();
                            JFrame imageFrame = new JFrame();
                            imageFrame.setTitle("Image Frame");
                            imageFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            imageFrame.setSize(700, 700);
                            JPanel imPan = new JPanel();
                            imPan.add(picLabel);
                            imageFrame.add(imPan, BorderLayout.CENTER);

                            JPanel buttonPanel = new JPanel();

                            JButton addName = new JButton("Add name");
                            JTextField nameBox = new JTextField(15);
                            JPanel nameBut = new JPanel();
                            nameBut.add(addName);
                            nameBut.add(nameBox);

                            JButton addLabelButton = new JButton("Add label");
                            JTextField labelBox = new JTextField(15);
                            JPanel labelBut = new JPanel();
                            labelBut.add(addLabelButton);
                            labelBut.add(labelBox);
                            
                            
                            addLabelButton.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ae){
                                 
                                    String text = labelBox.getText();
                                    //System.out.println("China");
                                    ic.addLabel(text);
                                    labelBox.setText("");
                                }
                            
                            });
                            

                            JButton listLabel = new JButton("List labels");
                            JPanel litButt = new JPanel();
                            litButt.add(listLabel);
                            
                            listLabel.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ae){
                                    System.out.println(ic.getLabels());
                                }
                            
                            });
                            

                            buttonPanel.setLayout(new GridLayout(3, 1));
                            buttonPanel.add(nameBut);
                            buttonPanel.add(labelBut);
                            buttonPanel.add(litButt);
                            
                            imageFrame.add(buttonPanel, BorderLayout.WEST);
                            
                            imageFrame.setVisible(true);

                            //JOPTION PANE ROUTE
                            //JOptionPane.showMessageDialog(null, picLabel, "Image", JOptionPane.PLAIN_MESSAGE, null);
                        }
                    }
                });

                ic.getIcon().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                        }
                    }

                });

            }//for loop

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        myFrame.add(centerPanel, BorderLayout.CENTER);
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
