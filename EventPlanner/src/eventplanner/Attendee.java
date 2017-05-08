package eventplanner;

import java.util.ArrayList;

public class Attendee {

    private String test = "test";
    private int attendee_ID;
    private String attendee_firstname;
    private String attendee_lastname;
    private int attendee_type;
    private int attendee_student_year;
    private EventDatabase db;
    
    public Attendee() {
        attendee_ID = 0;
        attendee_firstname = "";
        attendee_lastname = "";
        attendee_type = 0;
        attendee_student_year = 0;
    }

    public Attendee(int attendee_ID) {
        this.attendee_ID = attendee_ID;
    }

    public Attendee(int attendee_ID, String attendee_firstname, String attendee_lastname, int attendee_type,
            int attendee_student_year) {
        this.attendee_ID = attendee_ID;
        this.attendee_firstname = attendee_firstname;
        this.attendee_lastname = attendee_lastname;
        this.attendee_type = attendee_type;
        this.attendee_student_year = attendee_student_year;
    }

    /* GETTERS */
    public int getAttendee_ID() {
        return attendee_ID;
    }

    public String getAttendee_Firstname() {
        return attendee_firstname;
    }

    public String getAttendee_LastName() {
        return attendee_lastname;
    }

    public int getAttendee_Type() {
        return attendee_type;
    }

    public int getAttendee_Student_Year() {
        return attendee_student_year;
    }

    
    /* SETTERS */
    
    public void setAttendee_ID(int attendee_ID) {
        this.attendee_ID = attendee_ID;
    }

    public void setAttendee_FirstName(String attendee_firstname) {
        this.attendee_firstname = attendee_firstname;
    }

    public void setAttendee_LastName(String attendee_lastname) {
        this.attendee_lastname = attendee_lastname;
    }

    public void setAttendee_Type(int attendee_type) {
        this.attendee_type = attendee_type;
    }

    public void setAttendee_Student_Year(int attendee_student_year) {
        this.attendee_student_year = attendee_student_year;
    }

    public EventDatabase getDb() {
        return db;
    }

    public void setDb(EventDatabase db) {
        this.db = db;
    }

    
    public void fetch(ArrayList<String> values) {
        String query = "SELECT * FROM attendee WHERE attendee_id = ? ;";
        
        ArrayList<ArrayList<String>> results;
        results = db.getData(query, values); //call getData using PreparedStatement
        ArrayList<String> row = results.get(1);
        
        setAttendee_FirstName(row.get(1));
        setAttendee_LastName(row.get(2));
        setAttendee_Type(Integer.parseInt(row.get(3)));
        setAttendee_Student_Year(Integer.parseInt(row.get(4)));
    }

    
    
    public void put() {
        String attID = "" + getAttendee_ID();
        String fname = getAttendee_Firstname();
        String lname = getAttendee_LastName();
        String attType = "" + getAttendee_Type();
        String studentType = "" + getAttendee_Student_Year();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(attID);
        values.add(fname);
        values.add(lname);
        values.add(attType);
        values.add(studentType);
        values.add(attID);
        
        
        String updateStatement = "UPDATE attendee SET attendee_id= ? , attendee_firstname= ? , attendee_lastname= ? , attendee_type= ? , "
                + "attendee_student_year= ? WHERE attendee_id= ? ;";

        db.setData(updateStatement, values); //call setData using PreparedStatement
    }

    
    
    public void post() {
        String attID = "" + getAttendee_ID();
        String fname = getAttendee_Firstname();
        String lname = getAttendee_LastName();
        String attType = "" + getAttendee_Type();
        String studentType = "" + getAttendee_Student_Year();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(attID);
        values.add(fname);
        values.add(lname);
        values.add(attType);
        values.add(studentType);
        
        String insertStatement = "INSERT INTO attendee VALUES ( ? , ? , ? , ? , ?);";

        db.setData(insertStatement, values); //call setData using PreparedStatement
    }

    public void delete() {
        String id = "" + getAttendee_ID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        
        String deleteStatement = "DELETE FROM attendee WHERE attendee_id= ? ;";

        db.setData(deleteStatement, values); //call setData using PreparedStatement
    }
}
