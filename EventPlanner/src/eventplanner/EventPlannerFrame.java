package eventplanner;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author rachelpoturich
 */
public class EventPlannerFrame extends JFrame{
    
    
    public EventPlannerFrame(){
        
        //Basic set up
        setTitle("Event Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 500);
        setBackground(new Color(211,250,214));
        
        //North panel
        JPanel northPanel = new JPanel();
        northPanel.setSize(800, 300);
        northPanel.setBackground(new Color(211,250,214));
        
        
        //South panel
        JPanel southPanel = new JPanel();
        southPanel.setSize(800, 300);
        southPanel.setBackground(new Color(211,250,214));
        
        
        //East panel
        JPanel eastPanel = new JPanel();
        eastPanel.setSize(200, 500);
        eastPanel.setBackground(new Color(211,250,214));
        
        
        //West panel
        JPanel westPanel = new JPanel();
        westPanel.setSize(200, 500);
        westPanel.setBackground(new Color(211,250,214));
        
        
        //Sort panel - first row in grid layout
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setBackground(new Color(211,250,214));
        sortPanel.add(new JButton("Sort"));
        JRadioButton nameRadio = new JRadioButton("Name");
        JRadioButton dateRadio = new JRadioButton("Date");
        JRadioButton presenterRadio = new JRadioButton("Presenter");
        
        //Button group for radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(nameRadio);
        buttonGroup.add(dateRadio);
        buttonGroup.add(presenterRadio);
        
        //Add elements to sort panel
        sortPanel.add(nameRadio);
        sortPanel.add(dateRadio);
        sortPanel.add(presenterRadio);
        
        sortPanel.setBackground(new Color(89,100,117));
        
        
        //Center panel of grid layout
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        centerPanel.setBackground(new Color(211,250,214));
        centerPanel.add(sortPanel);
        
        
        //Set icons for buttons
        ImageIcon searchIcon = new ImageIcon("img/search.jpg");
        ImageIcon addIcon = new ImageIcon("img/add.png");
        ImageIcon listIcon = new ImageIcon("img/list.png");
        
        
        //loop and add filler info
        for(int i=0; i<10; i++){
            JPanel row = new JPanel(new GridLayout(1, 8));
            row.add(new JButton(searchIcon));
            row.add(new JButton(addIcon));
            row.add(new JLabel("   Event Name"));
            row.add(new JLabel("00/00/00"));
            row.add(new JLabel("00:00"));
            row.add(new JLabel("Room"));
            row.add(new JLabel("Presenter"));
            row.add(new JButton(listIcon));
            row.setBackground(Color.WHITE);
            
            centerPanel.add(row);
        }
        
        
        //Create menu bar
        JMenuBar menu = new JMenuBar();
        
        //File 
        JMenu eventMenu = new JMenu("File");
        menu.add(eventMenu);
        
        //File options
        JMenuItem eventItem = new JMenuItem("See Events");
        JMenuItem presenterItem = new JMenuItem("See Presenters");
        JMenuItem roomItem = new JMenuItem("See Rooms");
        
        eventMenu.add(eventItem);
        eventMenu.add(presenterItem);
        eventMenu.add(roomItem);
        
        
        //Event management
        JMenu eventMan = new JMenu("Event Management");
        menu.add(eventMan);
        
        //Event management options
        JMenuItem addEventItem = new JMenuItem("Add Event");
        JMenuItem removeEventItem = new JMenuItem("Remove Event");
        
        eventMan.add(addEventItem);
        eventMan.add(removeEventItem);
        
        
        //Room management
        JMenu roomMan = new JMenu("Room Management");
        menu.add(roomMan);
        
        
        //Room management options
        JMenuItem addRoomItem = new JMenuItem("Add Room");
        JMenuItem removeRoomItem = new JMenuItem("Remove Room");
        
        roomMan.add(addRoomItem);
        roomMan.add(removeRoomItem);
        
        
        //Final set up
        setJMenuBar(menu);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        
        
        //Add scroll pane
        JScrollPane scroller = new JScrollPane(centerPanel);
                
        add(scroller, BorderLayout.CENTER);
        
        //Set visibility
        setVisible(true);
        
    } //end constructor
    
} //end class
