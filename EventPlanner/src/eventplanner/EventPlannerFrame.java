package eventplanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author rachelpoturich
 */
public class EventPlannerFrame extends JFrame {

    private ArrayList<InfoHandler> events;
    private InfoHandler b;
    private String role;

    public EventPlannerFrame(ArrayList<InfoHandler> e, InfoHandler _b) {

        b = _b;
        showLoginForm();

        events = e;
        role = b.getUser().getRole();

        System.out.println("Current role: " + role);

        if (role.equals("admin")) {
            System.out.println("You are an admin");
        }

        //Basic set up
        setTitle("Welcome " + b.getUser().getUsername());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 500);
        setBackground(new Color(211, 250, 214));

        //North panel
        JPanel northPanel = new JPanel();
        northPanel.setSize(800, 300);
        northPanel.setBackground(new Color(211, 250, 214));

        //South panel
        JPanel southPanel = new JPanel();
        southPanel.setSize(800, 300);
        southPanel.setBackground(new Color(211, 250, 214));

        //East panel
        JPanel eastPanel = new JPanel();
        eastPanel.setSize(200, 500);
        eastPanel.setBackground(new Color(211, 250, 214));

        //West panel
        JPanel westPanel = new JPanel();
        westPanel.setSize(200, 500);
        westPanel.setBackground(new Color(211, 250, 214));

        //Sort panel - first row in grid layout
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setBackground(new Color(211, 250, 214));
        JButton sortButton = new JButton("Sort");
        sortPanel.add(sortButton);

        JRadioButton nameRadio = new JRadioButton("Name");
        JRadioButton dateRadio = new JRadioButton("Date");
        JRadioButton presenterRadio = new JRadioButton("Presenter");

        dateRadio.setSelected(true);

        //Button group for radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(nameRadio);
        buttonGroup.add(dateRadio);
        buttonGroup.add(presenterRadio);

        //Add elements to sort panel
        sortPanel.add(nameRadio);
        sortPanel.add(dateRadio);
        sortPanel.add(presenterRadio);

        ArrayList<JRadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(nameRadio);
        radioButtons.add(dateRadio);
        radioButtons.add(presenterRadio);

        sortPanel.setBackground(new Color(89, 100, 117));

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton selected = null;
                //if(radioButtons.)

                for (JRadioButton jrb : radioButtons) {
                    if (jrb.isSelected()) {
                        selected = jrb;

                        System.out.println(selected.getText());

                        if (selected.getText().equalsIgnoreCase("name")) {

                        } else if (selected.getText().equalsIgnoreCase("date")) {

                        } else if (selected.getText().equalsIgnoreCase("presenter")) {

                        }
                    }
                }

            }

        }); //end sort button actionlistener

        //Center panel of grid layout
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        centerPanel.setBackground(new Color(211, 250, 214));
        centerPanel.add(sortPanel);

        //Set icons for buttons
        ImageIcon searchIcon = new ImageIcon("img/search.jpg");
        ImageIcon addIcon = new ImageIcon("img/add.png");
        ImageIcon listIcon = new ImageIcon("img/list.png");

        String allEvents = InfoHandler.printEvents(events);
        String[] eventStrings = allEvents.split("\n");

        //loop and add info
        for (String s : eventStrings) {
            JPanel row = new JPanel(new GridLayout(1, 8));

            JButton searchButton = new JButton(searchIcon);

            JPanel searchButtonPanel = new JPanel();
            searchButtonPanel.setBackground(Color.WHITE);
            searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            searchButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            searchButtonPanel.add(searchButton);
            row.add(searchButtonPanel);

            if (role.equals("admin") || role.equals("editor")) {
                JButton addButton = new JButton(addIcon);
                addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                JPanel addButtonPanel = new JPanel();
                addButtonPanel.setBackground(Color.WHITE);
                addButtonPanel.add(addButton);
                row.add(addButtonPanel);
            }

            String[] eventStringPieces = s.split("\\s+");
            for (String st : eventStringPieces) {
                System.out.println(st);
                row.add(new JLabel(st));
            }

            if (role.equals("admin") || role.equals("editor")) {
                JButton listAttsButton = new JButton(listIcon);
                JPanel listAttsButtonPanel = new JPanel();
                listAttsButtonPanel.setBackground(Color.WHITE);
                listAttsButtonPanel.add(listAttsButton);
                row.add(listAttsButtonPanel);
            }

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

        if (role.equals("admin") || role.equals("editor")) {
            //Event management
            JMenu eventMan = new JMenu("Event Management");
            menu.add(eventMan);

            //Event management options
            JMenuItem addEventItem = new JMenuItem("Add Event");

            addEventItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addEventAction();
                }
            });

            eventMan.add(addEventItem);

            if (role.equals("admin")) {
                JMenuItem removeEventItem = new JMenuItem("Remove Event");

                removeEventItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeEventAction();
                    }
                });

                eventMan.add(removeEventItem);
            }

        }

        if (role.equals("admin")) {

            //Room management
            JMenu roomMan = new JMenu("Room Management");
            menu.add(roomMan);

            //Room management options
            JMenuItem addRoomItem = new JMenuItem("Add Room");

            addRoomItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addRoomAction();
                }
            });

            JMenuItem removeRoomItem = new JMenuItem("Remove Room");

            removeRoomItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeRoomAction();
                }
            });

            roomMan.add(addRoomItem);
            roomMan.add(removeRoomItem);
        }

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

    public void showLoginForm() {

        JPanel loginPanel = new JPanel(new GridLayout(6, 2));
        JLabel username = new JLabel("Username: ");
        JLabel password = new JLabel("Password: ");
        JTextField enterUser = new JTextField(15);
        JPasswordField enterPass = new JPasswordField(15);

        JLabel instructions1 = new JLabel("Enter username and password");
        instructions1.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel instructions2 = new JLabel("If you do not have a username,");
        instructions2.setFont(new Font("Arial", Font.ITALIC, 14));
        JLabel instructions3 = new JLabel("leave the fields empty and click OK");
        instructions3.setFont(new Font("Arial", Font.ITALIC, 14));

        loginPanel.add(instructions1);
        loginPanel.add(new JPanel());

        loginPanel.add(instructions2);
        loginPanel.add(new JPanel());

        loginPanel.add(instructions3);
        loginPanel.add(new JPanel());

        loginPanel.add(new JPanel());
        loginPanel.add(new JPanel());

        loginPanel.add(username);
        loginPanel.add(enterUser);
        loginPanel.add(password);
        loginPanel.add(enterPass);

        int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            String sendUsername;
            String passwordString;

            if (enterUser.getText().equals("") || enterUser.getText() == null) {
                sendUsername = "general";
                passwordString = "general";

                b.authenticate(sendUsername, passwordString);
            } else {
                sendUsername = enterUser.getText();
                passwordString = new String(enterPass.getPassword());

                b.authenticate(sendUsername, passwordString);
            }
        } else {
            int logOrClose = JOptionPane.showConfirmDialog(null, "You must either login or close the program", "Login Warning", JOptionPane.OK_CANCEL_OPTION);

            if (logOrClose == JOptionPane.OK_OPTION) {
                showLoginForm();
            } else {
                JOptionPane.showMessageDialog(null, "The program will now close");
                System.exit(0);
            }

        }

    }

    public void addRoomAction() {
        JFrame popUp = new JFrame();

        popUp.setTitle("Insert room data");

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(5, 2));

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());

        JLabel roomIdLabel = new JLabel("Room ID: ");
        JLabel campusIdLabel = new JLabel("Campus ID: ");
        JLabel roomNameLabel = new JLabel("Room name: ");
        JLabel roomCapacityLabel = new JLabel("Room capacity: ");
        JLabel videoLabel = new JLabel("Video (true/false): ");

        JTextField roomIdTextField = new JTextField();
        JTextField campusIdTextField = new JTextField();
        JTextField roomNameTextField = new JTextField();
        JTextField roomCapacityTextField = new JTextField();
        JTextField videoTextField = new JTextField();

        gridPanel.add(roomIdLabel);
        gridPanel.add(roomIdTextField);

        gridPanel.add(campusIdLabel);
        gridPanel.add(campusIdTextField);

        gridPanel.add(roomNameLabel);
        gridPanel.add(roomNameTextField);

        gridPanel.add(roomCapacityLabel);
        gridPanel.add(roomCapacityTextField);

        gridPanel.add(videoLabel);
        gridPanel.add(videoTextField);

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int roomId = Integer.parseInt(roomIdTextField.getText());
                int campusId = Integer.parseInt(campusIdTextField.getText());
                String roomName = roomNameTextField.getText();
                int roomCapacity = Integer.parseInt(roomCapacityTextField.getText());
                boolean video = Boolean.parseBoolean(videoTextField.getText());

                Room newRoom = new Room();
                newRoom.setDb(b.getDb());

                newRoom.setRoomID(roomId);
                newRoom.setCampusID(campusId);
                newRoom.setRoomName(roomName);
                newRoom.setCapacity(roomCapacity);
                newRoom.setVideolink(video);

                newRoom.post(roomId);

            }
        });

        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                popUp.dispose();

            }
        });

        flowPanel.add(submitButton);
        flowPanel.add(closeButton);

        popUp.add(gridPanel, BorderLayout.NORTH);
        popUp.add(flowPanel, BorderLayout.SOUTH);

        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        popUp.setVisible(true);
    }

    public void removeRoomAction() {
        JFrame popUp = new JFrame();

        popUp.setTitle("Insert room ID");

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1, 2));

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());

        JLabel roomIDLabel = new JLabel("Room ID: ");
        JTextField roomIDTextField = new JTextField();

        gridPanel.add(roomIDLabel);
        gridPanel.add(roomIDTextField);

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int roomID;

                roomID = Integer.parseInt(roomIDTextField.getText());

                Room newRoom = new Room();
                newRoom.setDb(b.getDb());
                newRoom.setRoomID(roomID);

                newRoom.delete();

            }
        });

        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                popUp.dispose();

            }
        });

        flowPanel.add(submitButton);
        flowPanel.add(closeButton);

        popUp.add(gridPanel, BorderLayout.NORTH);
        popUp.add(flowPanel, BorderLayout.SOUTH);

        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        popUp.setVisible(true);
    }

    public void addEventAction() {
        JFrame popUp = new JFrame();

        popUp.setTitle("Insert event data.");

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9, 2));

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());

        JLabel eventIDLabel = new JLabel("Event ID: ");
        JLabel eventNameLabel = new JLabel("Event name: ");
        JLabel startTimeIDLabel = new JLabel("Start time ID: ");
        JLabel endTimeIDLabel = new JLabel("End time ID: ");
        JLabel roomIDLabel = new JLabel("Room ID: ");
        JLabel dayIDLabel = new JLabel("Day ID: ");
        JLabel videoLabel = new JLabel("Video (true/false): ");
        JLabel eventDescriptionLabel = new JLabel("Event description: ");
        JLabel audienceTypeLabel = new JLabel("Audience type: ");

        JTextField eventIDTextField = new JTextField();
        JTextField eventNameTextField = new JTextField();
        JTextField startTimeIDTextField = new JTextField();
        JTextField endTimeIDTextField = new JTextField();
        JTextField roomIDTextField = new JTextField();
        JTextField dayIDTextField = new JTextField();
        JTextField videoTextField = new JTextField();
        JTextField eventDescriptionTextField = new JTextField();
        JTextField audienceTypeTextField = new JTextField();

        gridPanel.add(eventIDLabel);
        gridPanel.add(eventIDTextField);

        gridPanel.add(eventNameLabel);
        gridPanel.add(eventNameTextField);

        gridPanel.add(startTimeIDLabel);
        gridPanel.add(startTimeIDTextField);

        gridPanel.add(endTimeIDLabel);
        gridPanel.add(endTimeIDTextField);

        gridPanel.add(roomIDLabel);
        gridPanel.add(roomIDTextField);

        gridPanel.add(dayIDLabel);
        gridPanel.add(dayIDTextField);

        gridPanel.add(videoLabel);
        gridPanel.add(videoTextField);

        gridPanel.add(eventDescriptionLabel);
        gridPanel.add(eventDescriptionTextField);

        gridPanel.add(audienceTypeLabel);
        gridPanel.add(audienceTypeTextField);

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int eventID = Integer.parseInt(eventIDTextField.getText());
                String eventName = eventNameTextField.getText();
                int startTimeID = Integer.parseInt(startTimeIDTextField.getText());
                int endTimeID = Integer.parseInt(endTimeIDTextField.getText());
                int roomID = Integer.parseInt(roomIDTextField.getText());
                int dayID = Integer.parseInt(roomIDTextField.getText());
                boolean video = Boolean.parseBoolean(videoTextField.getText());
                String eventDescription = eventDescriptionTextField.getText();
                int audienceType = Integer.parseInt(audienceTypeTextField.getText());

                Event newEvent = new Event();
                newEvent.setDb(b.getDb());

                newEvent.setEventID(eventID);
                newEvent.setEventName(eventName);
                newEvent.setStartTimeID(startTimeID);
                newEvent.setEndTimeID(endTimeID);
                newEvent.setRoomID(roomID);
                newEvent.setDayID(dayID);
                newEvent.setVideo(video);
                newEvent.setDescription(eventDescription);
                newEvent.setAudienceType(audienceType);

                newEvent.post(eventID);

            }
        });

        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                popUp.dispose();

            }
        });

        flowPanel.add(submitButton);
        flowPanel.add(closeButton);

        popUp.add(gridPanel, BorderLayout.NORTH);
        popUp.add(flowPanel, BorderLayout.SOUTH);

        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        popUp.setVisible(true);
    }

    public void removeEventAction() {
        JFrame popUp = new JFrame();

        popUp.setTitle("Insert event ID.");

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1, 2));

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());

        JLabel eventIDLabel = new JLabel("Event ID: ");
        JTextField eventIDTextField = new JTextField();

        gridPanel.add(eventIDLabel);
        gridPanel.add(eventIDTextField);

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int eventID;

                eventID = Integer.parseInt(eventIDTextField.getText());

                Event newEvent = new Event();
                newEvent.setDb(b.getDb());
                newEvent.setEventID(eventID);

                newEvent.delete();

            }
        });

        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                popUp.dispose();

            }
        });

        flowPanel.add(submitButton);
        flowPanel.add(closeButton);

        popUp.add(gridPanel, BorderLayout.NORTH);
        popUp.add(flowPanel, BorderLayout.SOUTH);

        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        popUp.setVisible(true);
    }

} //end class
