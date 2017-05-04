package eventplanner;

import java.util.ArrayList;

/**
 *
 * @author rachelpoturich
 */
public class Event {
    
    private int eventID;
    private String eventName;
    private int startTimeID;
    private int endTimeID;
    private int roomID;
    private int dayID;
    private boolean video;
    private String eventDescription;
    private int audienceType;
    private EventDatabase db;
    
    /**
     * Default constructor
     */
    public Event() {
        eventID = 0;
        eventName = "";
        startTimeID = 0;
        endTimeID = 0;
        roomID = 0;
        dayID = 0;
        video = false;
        eventDescription = "";
        audienceType = 0;
    }
    
    /**
     * Constructor that accepts an equipment ID
     * @param id    The Event ID 
     */
    public Event(int id) {
        eventID = id;
    }
    
    /**
     * Constructor that accepts all attributes and a database
     * 
     * @param eID
     * @param name
     * @param start
     * @param end
     * @param room
     * @param day
     * @param vid
     * @param description
     * @param audType
     * @param _db 
     */
    public Event(int eID, String name, int start, int end, int room, int day, boolean vid, String description, int audType, EventDatabase _db) {
        eventID = eID;
        eventName = name;
        startTimeID = start;
        endTimeID = end;
        roomID = room;
        dayID = day;
        video = vid;
        eventDescription = description;
        audienceType = audType;
        db = _db;
    }

    /* GETTERS */
    public int getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public int getStartTimeID() {
        return startTimeID;
    }

    public int getEndTimeID() {
        return endTimeID;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getDayID() {
        return dayID;
    }

    public boolean isVideo() {
        return video;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getAudienceType() {
        return audienceType;
    }

    public EventDatabase getDatabase() {
        return db;
    }

    
    /* SETTERS */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setStartTimeID(int startTimeID) {
        this.startTimeID = startTimeID;
    }

    public void setEndTimeID(int endTimeID) {
        this.endTimeID = endTimeID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setDescription(String description) {
        this.eventDescription = description;
    }

    public void setAudienceType(int audienceType) {
        this.audienceType = audienceType;
    }

    public void setDb(EventDatabase db) {
        this.db = db;
    }
    
    /**
     * 
     * @param values 
     */
    public void fetch(ArrayList<String> values) {
        String query = "SELECT * FROM event WHERE event_id= ? ;";
        ArrayList<ArrayList<String>> results;
        results = db.getData(query, values); //call getData using PreparedStatement
        ArrayList<String> row = results.get(1);

        setEventName(row.get(1));
        setStartTimeID(Integer.parseInt(row.get(2)));
        setEndTimeID(Integer.parseInt(row.get(3)));
        setRoomID(Integer.parseInt(row.get(4)));
        setDayID(Integer.parseInt(row.get(5)));
        setVideo(Boolean.parseBoolean(row.get(6)));
        setDescription(row.get(7));
        setAudienceType(Integer.parseInt(row.get(8)));
        
    }
    
    
    /**
     * 
     */
    public void put() {
        String id = "" + getEventID();
        String name = getEventName();
        int start = getStartTimeID();
        int end = getEndTimeID();
        int room = getRoomID();
        int day = getDayID();
        boolean vid = isVideo();
        String description = getEventDescription();
        int audience = getAudienceType();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        values.add(name);
        values.add("" + start);
        values.add("" + end);
        values.add("" + room);
        values.add("" + day);
        values.add("" + vid);
        values.add(description);
        values.add("" + audience);
        values.add(id);
        
        String updateStatement = "UPDATE event SET event_id= ? , event_name= ? , "
                + "time_id_start= ? , time_id_end= ? , "
                + "room_id= ? , day_id= ? , video_link= ? , "
                + "event_description= ? , event_audiencetype= ? WHERE event_id= ? ;";

        db.setData(updateStatement, values); //call setData using PreparedStatement
    }
    
    
    /**
     * 
     * @param newID 
     */
    public void post(int newID) {
        String id = "" + newID;
        String name = getEventName();
        int start = getStartTimeID();
        int end = getEndTimeID();
        int room = getRoomID();
        int day = getDayID();
        boolean vid = isVideo();
        String description = getEventDescription();
        int audience = getAudienceType();
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
        values.add(id);
        values.add(name);
        values.add("" + start);
        values.add("" + end);
        values.add("" + room);
        values.add("" + day);
        values.add("" + vidBoolInt);
        values.add(description);
        values.add("" + audience);
        
        String insertStatement = "INSERT INTO event VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ?);";

        db.setData(insertStatement, values); //call setData using PreparedStatement
    }
    
    
    /**
     * 
     */
    public void delete() {
        String id = "" + getEventID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        
        String deleteStatement = "DELETE FROM event WHERE event_id= ? ;";

        db.setData(deleteStatement, values); //call setData using PreparedStatement
    }
    
    
    
    
}
