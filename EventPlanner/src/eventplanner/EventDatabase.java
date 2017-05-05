package eventplanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Time;
import javax.swing.JOptionPane;

/**
 * Provides connection to the Event Planner database
 *
 *
 * @author rachelpoturich
 */
public class EventDatabase {

    private Connection connect;
    private String url = ""; //URL
    private String user = ""; //user
    private String pass = ""; //password
    private boolean connection = false;
    private String query = ""; //query statement

    private Statement statement;
    private ResultSet result;
    
    private ArrayList<Event> events;

    /**
     * Constructor
     *
     * @param _url The url
     * @param _user The user
     * @param _pass The password
     */
    public EventDatabase(String _url, String _user, String _pass) {

        setUrl(_url);
        setUser(_user);
        setPassword(_pass);

    } //end constructor

    /**
     * Sets the URL
     *
     * @param _url
     */
    public void setUrl(String _url) {
        url = _url;
    }

    /**
     * Sets the user
     *
     * @param _user
     */
    public void setUser(String _user) {
        user = _user;
    }

    /**
     * Sets the password
     *
     * @param _pass
     */
    public void setPassword(String _pass) {
        pass = _pass;
    }

    /**
     * Attempts to establish a connection with the MySQL database
     */
    public boolean connect() {

        try {
            connect = DriverManager.getConnection(url, user, pass);
            System.out.println("Successfully connected to the database!");
            statement = connect.createStatement();
            connection = true;

        } catch (SQLException sqle) {
            System.out.println("Failed to connect");
            sqle.printStackTrace();
            connection = false;
        } catch (Exception e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
            connection = false;
        } finally {

            return connection;
        }
    } //end connect

    /**
     * Attempts to close the connection with the MySQL database
     */
    public boolean close() {

        try {
            connect.close();
            connection = connect.isClosed();
            System.out.println("Database closed!");
        } catch (SQLException sqle) {

        } catch (Exception e) {

        } finally {
            return connection;
        }

    } //end close

    /**
     * Gets the data from a table with a specified SQL statement. This method
     * uses a PreparedStatement for execution.
     *
     * @param sqlStatement The SQL SELECT statement to query the database
     * @param values The values to be passed to the PreparedStatement
     * @return The 2D array of table info
     */
    public ArrayList<ArrayList<String>> getData(String sqlStatement, ArrayList<String> values) {
        //System.out.println("Query that getData receives: " + sqlStatement);
        PreparedStatement ps = prepare(sqlStatement, values); //prepare statement

        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

        try {
            result = ps.executeQuery();
            int cols = result.getMetaData().getColumnCount(); //number of columns to iterate over

            ArrayList<String> columnRow = new ArrayList<String>();

            for (int i = 1; i <= cols; i++) {
                String columnName = result.getMetaData().getColumnName(i);
                columnRow.add(columnName);
            }

            table.add(columnRow);

            while (result.next()) {
                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= cols; i++) {
                    String data = result.getString(i);
                    row.add(data);
                }
                table.add(row);
            }

        } catch (SQLException sqle) {

        } catch (Exception e) {

        }

        return table;
    } //end getData

    /**
     * Performs UPDATE, INSERT, and DELETE statements on a table with a
     * specified SQL statement. This method uses a PreparedStatement for
     * execution.
     *
     * @param sqlStatement The SQL statement to query the database
     * @param values The values to be passed to the PreparedStatement
     * @return A boolean to determine whether or not the query was successful
     */
    public boolean setData(String sqlStatement, ArrayList<String> values) {
        PreparedStatement ps = prepare(sqlStatement, values);//prepare statement
        boolean isExecuted = false;

        System.out.println(sqlStatement); //PRINT CHECK
        try {
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                JOptionPane.showMessageDialog(null, "No rows affected");
                isExecuted = true;
            } else {
                JOptionPane.showMessageDialog(null, (rowsAffected + " row(s) affected."));
                isExecuted = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            isExecuted = false;
        } catch (Exception e) {
            e.printStackTrace();
            isExecuted = false;
        }

        return isExecuted;
    } //end setData

    /**
     * Prepares a String representing a query and binds values to the
     * PreparedStatement.
     *
     * @param query The SQL statement to query the database
     * @param values The values to be passed to the PreparedStatement
     * @return The PreparedStatement with bound values
     */
    public PreparedStatement prepare(String query, ArrayList<String> values) {
        PreparedStatement ps = null;
        System.out.println(query); //PRINT CHECK
        System.out.println(values); //PRINT CHECK
        try {
            ps = connect.prepareStatement(query);

            for (int i = 0; i < values.size(); i++) {
                ps.setString(i + 1, values.get(i));
                System.out.println(values.get(i)); //PRINT CHECK
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ps;
    } //end prepare

    
    
    public ArrayList<String> getDataForEventsByName() {
        ArrayList<String> eventInfo = new ArrayList<>();
        query = "SELECT event.event_id, event.event_name, time.time, room.room_name, CONCAT(presenter.presenter_lastname, \", \" ,presenter.presenter_firstname) AS \"Name\" " +
                "FROM (time INNER JOIN event ON time.time_id= event.time_id_start) " +
                "INNER JOIN room ON(time.time_id= room.room_id) " +   
                "INNER JOIN presenter ON (presenter.presenter_id = event.event_id) ORDER BY event.event_name ASC;";    
        
        try {

            result = statement.executeQuery(query);
            boolean row = result.next();

            if (row == false) {
                System.out.println("EMPTY SET: There is no data...");
                
            } else {
                while (row) {
                    int id = result.getInt(1);
                    String nameEvent = result.getString(2);
                    Time time = result.getTime(3);
                    String room = result.getString(4);
                    String presenter = result.getString(5);
                    

                    Event newEvent = new Event(id);
                    newEvent.setDb(this);
                    
                    //ArrayList<String> values = new ArrayList<>();
                    //values.add("" + id);
                    //newEvent.fetch(values);
                    
                    String newEventInfo = nameEvent + "," + time + "," + room + "," + presenter;
                    
                    eventInfo.add(newEventInfo);
                    row = result.next();
                }
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventInfo;
    }
    
    public ArrayList<Event> getEvents(){
        return events;
    }

} //end class
