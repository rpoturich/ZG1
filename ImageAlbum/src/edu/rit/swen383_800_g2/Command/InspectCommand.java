/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import edu.rit.swen838_800_g2.View.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author rachelpoturich
 */
public class InspectCommand implements Command {

    private ViewMode viewMode;
    private MouseEvent e;
    private ImgComponent ic;

    public InspectCommand(ViewMode _viewMode, MouseEvent _e, ImgComponent _ic) {
        viewMode = _viewMode;
        e = _e;
        ic = _ic;
    }

    @Override
    public void execute() {

        if (e.getButton() == MouseEvent.BUTTON3) {
            JLabel picLabel = new JLabel(new ImageIcon(ic.getImage()));
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
                    Command c = new AddLabelCommand(ic, labelBox);
                    c.execute();
                }

            });

            JButton listLabel = new JButton("List labels");
            JPanel litButt = new JPanel();
            litButt.add(listLabel);

            listLabel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Command c = new ListLabelsCommand(ic);
                    c.execute();
                }

            });

            addName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Command c = new ChangeNameCommand(ic, nameBox);
                    c.execute();
                }
            });

            if (ic instanceof edu.rit.swen383_800_g2.Composite.Image) {
                buttonPanel.setLayout(new GridLayout(4, 1));
                buttonPanel.add(nameBut);
                buttonPanel.add(labelBut);
                buttonPanel.add(litButt);
                
                JButton editButton = new JButton("Edit Picture");
                JPanel editBut = new JPanel();
                editBut.add(editButton);
                
                buttonPanel.add(editBut);
                
                editButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        JFrame editFrame = new EditMode(ic);
                        editFrame.setVisible(true);
                        editFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    }
                });
                
                
                
            } else if(ic instanceof edu.rit.swen383_800_g2.Composite.Album){
                buttonPanel.setLayout(new GridLayout(3, 1));
                buttonPanel.add(nameBut);
                buttonPanel.add(labelBut);
                buttonPanel.add(litButt);
            }

            imageFrame.add(buttonPanel, BorderLayout.WEST);

            imageFrame.setVisible(true);

        }
    }

    @Override
    public BufferedImage[] getImg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
