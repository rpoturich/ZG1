/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import edu.rit.swen838_800_g2.View.ViewMode;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author rachelpoturich
 */
public class AddAlbumCommand implements Command {

    private ArrayList<ImgComponent> components;
    private ArrayList<String> imagePath;
    private ViewMode viewMode;
    private JPanel centerPanel;

    public AddAlbumCommand(ArrayList<ImgComponent> comps, ViewMode _viewMode, ArrayList<String> paths) {
        components = comps;
        viewMode = _viewMode;
        centerPanel = viewMode.getCenterPanel();
        imagePath = paths;
    }

    @Override
    public void execute() {

        JFrame newAlbumFrame = new JFrame();
        newAlbumFrame.setTitle("New Album");
        JPanel panel = new JPanel(new GridLayout(2, 0));

        ArrayList<ImgComponent> albumComponents = new ArrayList();

        JButton pickComps = new JButton("Add to this album");
        panel.add(pickComps);
        pickComps.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                albumComponents.clear();
                JFrame pickCompsFrame = new JFrame();
                JPanel pickPanel = new JPanel(new GridLayout(2, 0));

                for (ImgComponent i : components) {
                    i.getSmallIcon().addMouseListener(new MouseListener() {
                        @Override
                        public void mousePressed(MouseEvent me) {
                            if (me.getButton() == MouseEvent.BUTTON1) {
                                albumComponents.add(i);
                                components.remove(i);
                                centerPanel.remove(i.getLargeIcon());
                                centerPanel.remove(i.getSmallIcon());
                            }
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                        }
                    });
                }

                pickCompsFrame.add(pickPanel);
                pickCompsFrame.setVisible(true);

            }
        });

        edu.rit.swen383_800_g2.Composite.Album newAlbum = new edu.rit.swen383_800_g2.Composite.Album("New Album", imagePath.get(0));
        newAlbum.addComponents(albumComponents);

        newAlbum.getSmallIcon().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Command c = new InspectCommand(viewMode, e, newAlbum);
                c.execute();
            }
        });

        components.add(newAlbum);
        centerPanel.add(newAlbum.getLargeIcon());
        viewMode.repaint();
        newAlbumFrame.add(panel);
        newAlbumFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        newAlbumFrame.setSize(500, 500);
        newAlbumFrame.setLocationRelativeTo(null);
        newAlbumFrame.setVisible(true);

    }

    @Override
    public BufferedImage[] getImg() {
        return null;
    }

}
