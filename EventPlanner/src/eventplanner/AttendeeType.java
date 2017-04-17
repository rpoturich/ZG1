
import eventplanner.EventDatabase;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendeeType {

    int attendeetype_ID;
    String attendeetype_Name;
    private EventDatabase db;

    public AttendeeType() {
        attendeetype_ID = 0;
        attendeetype_Name = "";
    }

    public AttendeeType(int attendeetype_ID) {
        this.attendeetype_ID = attendeetype_ID;
    }

    public AttendeeType(int attendeetype_ID, String attendeetype_Name) {
        this.attendeetype_ID = attendeetype_ID;
        this.attendeetype_Name = attendeetype_Name;
    }

    
    /* GETTERS */
    public int getAttendeeTypeID() {
        return attendeetype_ID;
    }

    public String getAttendeeType_Name() {
        return attendeetype_Name;
    }

    
    /* SETTERS */
    public void setAttendeeTypeID(int attendeetype_ID) {
        this.attendeetype_ID = attendeetype_ID;
    }

    public void setAttendeeTypeName(String attendeetype_Name) {
        this.attendeetype_Name = attendeetype_Name;
    }

    
    
    public void fetch(ArrayList<String> values) {
        String query = "SELECT * FROM attendeetype WHERE attendeetype_id= ? ;";
        
        ArrayList<ArrayList<String>> results;
        results = db.getData(query, values); //call getData using PreparedStatement
        ArrayList<String> row = results.get(1);

        setAttendeeTypeID(Integer.parseInt(row.get(1)));
        setAttendeeTypeName(row.get(2));
    }

    public void put() {
        String attTypeID = "" + getAttendeeTypeID();
        String attTypeName = getAttendeeType_Name();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(attTypeID);
        values.add(attTypeName);
        values.add(attTypeID);
        
        String updateStatement = "UPDATE attendeetype SET attendeetype_id= ? , attendeetype_name= ? WHERE attendeetype_id= ? ;";

        db.setData(updateStatement, values); //call setData using PreparedStatement
    }

    public void post() {
        String attTypeID = "" + getAttendeeTypeID();
        String attTypeName = getAttendeeType_Name();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(attTypeID);
        values.add(attTypeName);
        
        String insertStatement = "INSERT INTO attendeetype VALUES ( ? , ? );";

        db.setData(insertStatement, values); //call setData using PreparedStatement
    }

    public void delete() {
        String id = "" + getAttendeeTypeID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        
        String deleteStatement = "DELETE FROM attendeetype WHERE attendeetype_id= ? ;";

        db.setData(deleteStatement, values); //call setData using PreparedStatement
    }
}
