package eventplanner;

import java.util.ArrayList;

/**
 *
 * @author rachelpoturich
 */
public class SignUp {
    
    private int signupEventID;
    private int signupAttendeeID;
    private EventDatabase db;
    
    
    public SignUp(){
        signupEventID = 0;
        signupAttendeeID = 0;
    }
    
    public SignUp(int eID){
        signupEventID = eID;
    }
    
    public SignUp(int eID, int attID, EventDatabase _db){
        signupEventID = eID;
        signupAttendeeID = attID;
        db = _db;
    }

    
    /* GETTERS */
    public int getSignupEventID() {
        return signupEventID;
    }
    
    public int getSignupAttendeeID() {
        return signupAttendeeID;
    }

    
    /* SETTERS */
    public void setSignupEventID(int signupEventID) {
        this.signupEventID = signupEventID;
    }

    public void setSignupAttendeeID(int signupAttendeeID) {
        this.signupAttendeeID = signupAttendeeID;
    }
    
    
    /**
     * 
     * @param values 
     */
    public void fetch(ArrayList<String> values) {
        String query = "SELECT * FROM signup WHERE signup_event_id= ? ;";
        
        ArrayList<ArrayList<String>> results;
        results = db.getData(query, values); //call getData using PreparedStatement
        ArrayList<String> row = results.get(1);

        setSignupEventID(Integer.parseInt(row.get(1)));
        setSignupAttendeeID(Integer.parseInt(row.get(2)));
        
    }
    
    
    /**
     * 
     */
    public void put() {
        String eID = "" + getSignupEventID();
        String attID = "" + getSignupAttendeeID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(eID);
        values.add(attID);
        values.add(eID);
        
        String updateStatement = "UPDATE signup SET signup_event_id= ? , signup_attendee_id= ? WHERE signup_event_id= ? ;";

        db.setData(updateStatement, values); //call setData using PreparedStatement
    }
    
    
    /**
     * 
     * @param newID 
     */
    public void post(int newID) {
        String eID = "" + getSignupEventID();
        String attID = "" + getSignupAttendeeID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(eID);
        values.add(attID);
        
        String insertStatement = "INSERT INTO signup VALUES ( ? , ? );";

        db.setData(insertStatement, values); //call setData using PreparedStatement
    }
    
    
    /**
     * 
     */
    public void delete() {
        String id = "" + getSignupEventID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        
        String deleteStatement = "DELETE FROM signup WHERE signup_event_id= ? ;";

        db.setData(deleteStatement, values); //call setData using PreparedStatement
    }
    
}
