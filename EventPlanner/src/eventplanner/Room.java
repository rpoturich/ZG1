package eventplanner;

import java.util.ArrayList;

public class Room {

    private int roomID;
    private int campusID;
    private String roomName;
    private int capacity;
    private boolean videolink;
    private EventDatabase db;

    public EventDatabase getDb() {
        return db;
    }

    public void setDb(EventDatabase db) {
        this.db = db;
    }

    public Room() {

    }

    public Room(int roomID) {
        this.roomID = roomID;

    }

    public Room(int roomID, int campusID, String roomName, int capacity, boolean videolink) {
        this.roomID = roomID;
        this.campusID = campusID;
        this.roomName = roomName;
        this.capacity = capacity;
        this.videolink = videolink;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCampusID() {
        return campusID;
    }

    public void setCampusID(int campusID) {
        this.campusID = campusID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isVideolink() {
        return videolink;
    }

    public void setVideolink(boolean videolink) {
        this.videolink = videolink;
    }
    
    
    /**
     * 
     * @param newID 
     */
    public void post(int newID) {
        String rID = "" + getRoomID();
        String campID = "" + getCampusID();
        String rName = getRoomName();
        String cap = "" + getCapacity();
        boolean vid = isVideolink();
        
        int vidBoolInt = 0;
        
        //Data type of video column in database is based on tinyint and boolean values
        //must be converted to a 0 or 1 before storing in the database
        if(vid){
            vidBoolInt = 1;
        } else {
            vidBoolInt = 0;
        }
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(rID);
        values.add(campID);
        values.add(rName);
        values.add(cap);
        values.add("" + vidBoolInt);
        
        
        String insertStatement = "INSERT INTO room VALUES ( ? , ? , ? , ? , ?);";

        db.setData(insertStatement, values); //call setData using PreparedStatement
    }
    
    
    
    /**
     * 
     */
    public void delete() {
        String id = "" + getRoomID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        
        String deleteStatement = "DELETE FROM room WHERE room_id= ? ;";

        db.setData(deleteStatement, values); //call setData using PreparedStatement
    }

}
