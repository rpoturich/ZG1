package edu.rit.swen838_800_g2.View;

import edu.rit.swen383_800_g2.Command.Command;
import edu.rit.swen383_800_g2.Command.*;
import edu.rit.swen383_800_g2.Composite.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GrayFilter;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author rachelpoturich
 */
public abstract class ViewMode extends JFrame {

    protected JPanel centerPanel;
    protected ArrayList<ImgComponent> components;
    protected JButton view;
    protected JTextField searchField;
    protected JButton enterSearch;
    protected JButton addAlbum;
    protected Map<String, Command> commands;
    protected ArrayList<String> imagePath;

    public ViewMode() {
        components = new ArrayList();

        centerPanel = new JPanel();
        view = new JButton("1");
        JPanel vPanel = new JPanel();
        vPanel.add(view);

        addAlbum = new JButton("Add an album");
        JPanel albumPanel = new JPanel();
        albumPanel.add(addAlbum);

        searchField = new JTextField(15);
        enterSearch = new JButton("Search");

        add(albumPanel, BorderLayout.NORTH);
        add(view, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(searchField, BorderLayout.SOUTH);
        add(enterSearch, BorderLayout.EAST);

        loadImages();

    }

    public void loadImages() {
        File f = new File("images");
        imagePath = new ArrayList<String>();

        ArrayList<File> files = new ArrayList(Arrays.asList(f.listFiles()));

        ViewMode currentMode = this;

        for (int i = 0; i < files.size(); i++) {
            imagePath.add(files.get(i).getAbsolutePath());
        }

        try {

            for (int i = 1; i < files.size(); i++) {
                edu.rit.swen383_800_g2.Composite.Image ic;
                ic = new edu.rit.swen383_800_g2.Composite.Image(imagePath.get(i));

                components.add(ic);

                ic.getSmallIcon().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        Command c = new InspectCommand(currentMode, e, ic);
                        c.execute();
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
                Command c = new ToggleCommand(currentMode);
                c.execute();
            }

        });

        enterSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Command c = new SearchCommand(currentMode);
                c.execute();
            }
        });

        addAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame newAlbumFrame = new JFrame();
                JPanel panel = new JPanel(new GridLayout(2, 0));
                JLabel nLabel = new JLabel("Enter a name:");
                JTextField nField = new JTextField(15);

                panel.add(nLabel);
                panel.add(nField);

                ArrayList<ImgComponent> albumComponents = new ArrayList();

                JButton pickComps = new JButton("Add to this album");
                panel.add(pickComps);
                pickComps.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        JFrame pickCompsFrame = new JFrame();
                        JPanel pickPanel = new JPanel(new GridLayout(2, 0));
                        
                        for (ImgComponent i : components) {
                            pickPanel.add(i.getSmallIcon());
                            i.getSmallIcon().addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent me) {
                                    albumComponents.add(i);
                                    JOptionPane.showMessageDialog(null, "added component " + i.getName());
                                }
                            });
                        }

                        pickCompsFrame.add(pickPanel);
                        pickCompsFrame.setVisible(true);

                    }
                });

                edu.rit.swen383_800_g2.Composite.Album newAlbum = new edu.rit.swen383_800_g2.Composite.Album("a1", imagePath.get(0));
                
                newAlbumFrame.add(panel);
                newAlbumFrame.setVisible(true);
            }
        });

        JScrollPane scroller = new JScrollPane(centerPanel);
        add(scroller, BorderLayout.CENTER);

        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Map<String, Command> buildMap() {
        Command c;
        Map<String, Command> commandMap = new HashMap();

        c = new ToggleCommand(this);
        commandMap.put("toggle", c);

        return commandMap;
    }

    public JButton getView() {
        return view;
    }

    public void setView(JButton view) {
        this.view = view;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public JButton getEnterSearch() {
        return enterSearch;
    }

    public void setEnterSearch(JButton enterSearch) {
        this.enterSearch = enterSearch;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public ArrayList<ImgComponent> getComponentsArray() {
        return components;
    }

    public void setComponents(ArrayList<ImgComponent> components) {
        this.components = components;
    }

}
