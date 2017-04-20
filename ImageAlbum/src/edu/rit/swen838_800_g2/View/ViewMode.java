package edu.rit.swen838_800_g2.View;

import edu.rit.swen383_800_g2.Command.Command;
import edu.rit.swen383_800_g2.Composite.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author rachelpoturich
 */
public abstract class ViewMode extends JPanel {

    //protected JPanel centerPanel;
    protected ArrayList<ImgComponent> components;
    protected JButton view;
    protected JTextField searchField;
    protected JButton enterSearch;
    
    public ViewMode() {
        components = new ArrayList();
        
        //centerPanel = new JPanel();
        view = new JButton("1");
        searchField = new JTextField(15);
        enterSearch = new JButton("Search");
        
        //add(view, BorderLayout.WEST);
        //add(centerPanel, BorderLayout.CENTER);
        //add(searchField, BorderLayout.SOUTH);
        //add(enterSearch, BorderLayout.EAST);
        
        
        
    }

    public abstract void loadImages();
    
    
    public Map<String, Command> buildMap(){
        Map<String, Command> commandMap = new HashMap();
        return commandMap;
    }

}
