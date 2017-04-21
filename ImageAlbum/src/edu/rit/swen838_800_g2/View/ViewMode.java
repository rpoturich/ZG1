package edu.rit.swen838_800_g2.View;

import edu.rit.swen383_800_g2.Command.Command;
import edu.rit.swen383_800_g2.Command.*;
import edu.rit.swen383_800_g2.Composite.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
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
    protected Map<String, Command> commands;

    public ViewMode() {
        components = new ArrayList();

        centerPanel = new JPanel();
        view = new JButton("1");
        JPanel vPanel = new JPanel();
        vPanel.add(view);

        searchField = new JTextField(15);
        enterSearch = new JButton("Search");

        add(view, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(searchField, BorderLayout.SOUTH);
        add(enterSearch, BorderLayout.EAST);
        
        loadImages();

    }

    public void loadImages() {
        File f = new File("images");
        ArrayList<String> imagePath = new ArrayList<String>();

        ArrayList<File> files = new ArrayList(Arrays.asList(f.listFiles()));

        for (int i = 0; i < files.size(); i++) {
            imagePath.add(files.get(i).getAbsolutePath());
        }

        try {

            for (int i = 0; i < files.size(); i++) {
                edu.rit.swen383_800_g2.Composite.Image ic;
                ic = new edu.rit.swen383_800_g2.Composite.Image(imagePath.get(i));

                components.add(ic);

            }//for loop

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        centerPanel.setLayout(new GridLayout(0, 1));

        for (ImgComponent i : components) {
            centerPanel.add(i.getLargeIcon());
        }

        ViewMode currentMode = this;

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Command c = new ToggleCommand(currentMode);
                c.execute();
            }

        });
        
        JScrollPane scroller = new JScrollPane(centerPanel);
        add(scroller, BorderLayout.CENTER);

        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
