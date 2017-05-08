package edu.rit.swen838_800_g2.View;

/**
 * Class to represent the window available to the user ViewMode extends JFrame
 *
 */
import edu.rit.swen383_800_g2.Command.Command;
import edu.rit.swen383_800_g2.Command.*;
import edu.rit.swen383_800_g2.Composite.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ViewMode extends JFrame {

    protected JPanel centerPanel;
    protected ArrayList<ImgComponent> components; //Use this; loop through all and check if 
                                                  //instance of Component.Image
    protected JButton view;
    protected JTextField searchField;
    protected JButton enterSearch;
    protected JButton addAlbum;
    protected Map<String, Command> commands;
    protected ArrayList<String> imagePath;

    /**
     * Constructor Instantiates a JFrame
     */
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
        JPanel searchPanel = new JPanel();
        searchPanel.add(enterSearch);

        add(albumPanel, BorderLayout.NORTH);
        add(vPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(searchField, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.EAST);

        loadImages();

    }//end constructor

    /**
     * Loads list of images, adds components to JFrame, and assigns listeners
     */
    public void loadImages() {

        //JFileChooser chooser = new JFileChooser();
        //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //String fileName = "";
        
        
        //int returnVal = chooser.showOpenDialog(null);
        //if (returnVal == JFileChooser.APPROVE_OPTION) {
            //System.out.println("You chose to open this file: "
                    //+ chooser.getCurrentDirectory());
            //fileName = chooser.getSelectedFile().getPath();
        //}

        //read images
        File f = new File("images");
        imagePath = new ArrayList<>();

        ArrayList<File> files = new ArrayList(Arrays.asList(f.listFiles()));

        ViewMode currentMode = this;

        for (int i = 0; i < files.size(); i++) {
            imagePath.add(files.get(i).getAbsolutePath());
        }

        try {

            //create Image objects and assign listeners to the icon
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

            e.printStackTrace();
        }

        centerPanel.setLayout(new GridLayout(0, 1));

        //add icons to the view
        for (ImgComponent i : components) {
            centerPanel.add(i.getLargeIcon());
        }

        //toggle view
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Command c = new ToggleCommand(currentMode);
                c.execute();
            }

        });

        //search
        enterSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Command c = new SearchCommand(currentMode);
                c.execute();
            }
        });

        //add an album
        addAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Command c = new AddAlbumCommand(components, currentMode, imagePath);
                c.execute();
            }
        });

        //add all elements to scrollpane and add to jframe
        JScrollPane scroller = new JScrollPane(centerPanel);
        add(scroller, BorderLayout.CENTER);

        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    } //end loadImages


    /* GETTERS */
    public JButton getView() {
        return view;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getEnterSearch() {
        return enterSearch;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public ArrayList<ImgComponent> getComponentsArray() {
        return components;
    }


    /* SETTERS */
    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public void setComponents(ArrayList<ImgComponent> components) {
        this.components = components;
    }

    public void setEnterSearch(JButton enterSearch) {
        this.enterSearch = enterSearch;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public void setView(JButton view) {
        this.view = view;
    }
}
