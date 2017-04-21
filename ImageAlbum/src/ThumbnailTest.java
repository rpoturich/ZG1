/*
 * Test class
 */


import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
    
    //Do I use this?
    String panelType = "4";

    JPanel centerPanel = new JPanel();
    JButton view = new JButton("1");
    JTextField searchField = new JTextField(15);
    JButton enterSearch = new JButton("Search");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ThumbnailTest thumbnailTest = new ThumbnailTest();

    }

    public ThumbnailTest() {

        for (int i = 0; i < files.size(); i++) {
            imagePath.add(files.get(i).getAbsolutePath());
        }

        JFrame myFrame = new JFrame("Thumbnails");

        ArrayList<ImgComponent> components = new ArrayList();

        try {

            for (int i = 0; i < files.size(); i++) {
                edu.rit.swen383_800_g2.Composite.Image ic;
                ic = new edu.rit.swen383_800_g2.Composite.Image(imagePath.get(i));

                components.add(ic);

                ic.getSmallIcon().addMouseListener(new MouseAdapter() {
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

                            addLabelButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {

                                    String text = labelBox.getText();
                                    //System.out.println("China");
                                    ic.addLabel(text);
                                    labelBox.setText("");
                                }

                            });

                            JButton listLabel = new JButton("List labels");
                            JPanel litButt = new JPanel();
                            litButt.add(listLabel);

                            listLabel.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    System.out.println(ic.getLabels());
                                }

                            });

                            addName.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    String newName = nameBox.getText();
                                    ic.setName(newName);
                                    nameBox.setText("");
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

            }//for loop

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        centerPanel.setLayout(new GridLayout(0, 1));

        for (ImgComponent i : components) {
            centerPanel.add(i.getLargeIcon());
        }

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (view.getText().equals("4")) {
                    view.setText("1");
                    centerPanel.setLayout(new GridLayout(0, 1));

                    for (ImgComponent ic : components) {
                        ic.getLargeIcon();
                    }
                } else if (view.getText().equals("1")) {
                    view.setText("4");
                    centerPanel.setLayout(new GridLayout(0, 2));

                    for (ImgComponent ic : components) {
                        ic.getSmallIcon();
                    }
                }
            }

        });

        enterSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String labelToFind = searchField.getText();

                System.out.println(labelToFind); //PRINT CHECK

                ArrayList<ImgComponent> foundComponents = new ArrayList();
                ArrayList<String> foundLabels = new ArrayList();

                for (ImgComponent ic : components) {
                    foundLabels = ic.getLabels();
                    System.out.println(foundLabels.size()); //PRINT CHECK

                    for (String s : foundLabels) {
                        if (s.equalsIgnoreCase(labelToFind)) {
                            System.out.println(s); //PRINT CHECK
                            foundComponents.add(ic);
                        } //if

                    } //inner for

                } //outer for

                JFrame foundFrame = new JFrame();
                foundFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                JPanel foundPanel = new JPanel(new GridLayout(2, 0));

                for (ImgComponent ic : foundComponents) {
                    foundPanel.add(ic.getSmallIcon());
                }

                foundFrame.add(foundPanel);
                foundFrame.setSize(350, 350);
                foundFrame.setVisible(true);
            }

        });

        JScrollPane scroll = new JScrollPane(centerPanel);
        myFrame.add(scroll, BorderLayout.CENTER);
        myFrame.add(view, BorderLayout.WEST);
        myFrame.add(searchField, BorderLayout.SOUTH);
        myFrame.add(enterSearch, BorderLayout.EAST);

        myFrame.setMinimumSize(new Dimension(350, 350));
        myFrame.setLocationRelativeTo(null);
        myFrame.setSize(350, 350);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }

    public void paint(Graphics g) {
        g.drawImage(image, 10, 10, null);
    }

}
