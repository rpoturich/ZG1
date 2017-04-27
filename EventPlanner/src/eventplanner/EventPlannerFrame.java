/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventplanner;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author rachelpoturich
 */
public class EventPlannerFrame extends JFrame{
    
    
    public EventPlannerFrame(){
        setTitle("Event Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 500);
        
        JPanel northPanel = new JPanel();
        northPanel.setSize(800, 300);
        
        JPanel southPanel = new JPanel();
        southPanel.setSize(800, 300);
        
        JPanel eastPanel = new JPanel();
        eastPanel.setSize(200, 500);
        
        JPanel westPanel = new JPanel();
        westPanel.setSize(200, 500);
        
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.add(new JButton("Sort"));
        JRadioButton nameRadio = new JRadioButton("Name");
        JRadioButton dateRadio = new JRadioButton("Date");
        JRadioButton presenterRadio = new JRadioButton("Presenter");
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(nameRadio);
        buttonGroup.add(dateRadio);
        buttonGroup.add(presenterRadio);
        
        sortPanel.add(nameRadio);
        sortPanel.add(dateRadio);
        sortPanel.add(presenterRadio);
        
        sortPanel.setBackground(Color.pink);
        
        
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(sortPanel);
        
        
        String fillerRow = "Name\t00/00/00\t00:00\tRoom\tPresenter";
        ImageIcon searchIcon = new ImageIcon("img/search.jpg");
        ImageIcon addIcon = new ImageIcon("img/add.png");
        ImageIcon listIcon = new ImageIcon("img/list.png");
        //searchIcon.set
        
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
            row.setBackground(Color.cyan);
            
            centerPanel.add(row);
        }
        
        JMenuBar menu = new JMenuBar();
        
        JMenu eventMenu = new JMenu("File");
        menu.add(eventMenu);
        
        JMenuItem eventItem = new JMenuItem("See Events");
        JMenuItem presenterItem = new JMenuItem("See Presenters");
        JMenuItem roomItem = new JMenuItem("See Rooms");
        
        eventMenu.add(eventItem);
        eventMenu.add(presenterItem);
        eventMenu.add(roomItem);
        
        
        JMenu eventMan = new JMenu("Event Management");
        menu.add(eventMan);
        
        JMenuItem addEventItem = new JMenuItem("Add Event");
        JMenuItem removeEventItem = new JMenuItem("Remove Event");
        
        eventMan.add(addEventItem);
        eventMan.add(removeEventItem);
        
        
        JMenu roomMan = new JMenu("Room Management");
        menu.add(roomMan);
        
        JMenuItem addRoomItem = new JMenuItem("Add Room");
        JMenuItem removeRoomItem = new JMenuItem("Remove Room");
        
        roomMan.add(addRoomItem);
        roomMan.add(removeRoomItem);
        
        
        setJMenuBar(menu);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        
        JScrollPane scroller = new JScrollPane(centerPanel);
        //scroller.add(centerPanel);
                
        add(scroller, BorderLayout.CENTER);
        
        
        setVisible(true);
        
    }
    
}
