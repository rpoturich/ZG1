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
            
            connection = false;
        } catch (Exception e) {
            
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
            System.out.println("DAtabase close!");
        } catch (SQLException sqle) {
            
        } catch (Exception e) {
            
        } finally {
            return connection;
        }

    } //end close
    
    
    /**
     * Gets the data from a table with a specified SQL statement.
     * This method uses a PreparedStatement for execution.
     * 
     * @param sqlStatement The SQL SELECT statement to query the database
     * @param values    The values to be passed to the PreparedStatement
     * @return  The 2D array of table info
     */
    public ArrayList<ArrayList<String>> getData(String sqlStatement, ArrayList<String> values){
        PreparedStatement ps = prepare(sqlStatement, values); //prepare statement
        
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
        
        try{    
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
     * specified SQL statement.
     * This method uses a PreparedStatement for execution.
     * 
     * @param sqlStatement  The SQL statement to query the database
     * @param values    The values to be passed to the PreparedStatement
     * @return  A boolean to determine whether or not the query was successful
     */
    public boolean setData(String sqlStatement, ArrayList<String> values){
        PreparedStatement ps = prepare(sqlStatement, values);//prepare statement
        boolean isExecuted = false;

        try {
            int rowsAffected = ps.executeUpdate(query);
            if (rowsAffected == 0) {
                JOptionPane.showMessageDialog(null, "No rows affected");
                isExecuted = true;
            } else {
                JOptionPane.showMessageDialog(null, (rowsAffected + " row(s) affected."));
                isExecuted = true;
            }
        } catch (SQLException sqle) {
            
            isExecuted = false;
        } catch (Exception e) {
            
            isExecuted = false;
        }

        return isExecuted;
    } //end setData
    
 
    /**
     * Prepares a String representing a query and binds values
     * to the PreparedStatement.
     * 
     * @param query The SQL statement to query the database 
     * @param values    The values to be passed to the PreparedStatement
     * @return  The PreparedStatement with bound values 
     */
    public PreparedStatement prepare(String query, ArrayList<String> values){
        PreparedStatement ps = null;
        try{
            ps = connect.prepareStatement(query);
            
            for(int i = 0; i < values.size(); i++){
                ps.setString(i+1, values.get(i));
            }
            
        } catch (SQLException sqle) {
            
        } catch (Exception e) {
            
        }
        
        return ps;
    } //end prepare
   
    public ArrayList<Bussiness> getDataWithColumns(String query){
      ArrayList<Bussiness> events = new ArrayList<>();
  
      try{
       
         result = statement.executeQuery(query);
         
         boolean row = result.next();
       
         if (row == false){
            System.out.println("EMPTY SET: There is no data...");
         }
         else{
       

           while (row){
            
               String nameEvent = result.getString(1);       
               Time date = result.getTime(2); 
               String room = result.getString(3);
               String presenter = result.getString(4);
               
               Bussiness bussiness = new Bussiness(nameEvent,date,room,presenter);
               events.add(bussiness);
   
               row = result.next();     
           }
        }
      } 
      catch (SQLException se){
         System.out.println("Could not query the database4...");
      }
      catch (Exception e){
         System.out.println("Could not query the database5...");
      }
      return events;
   }

} //end class
