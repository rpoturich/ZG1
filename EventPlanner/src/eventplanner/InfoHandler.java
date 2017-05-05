/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventplanner;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import javax.swing.JOptionPane;

/**
 *
 * @author Abii
 */
public class InfoHandler {

    String nameEvent;
    Time date;
    String room;
    String namePresenter;
    private ArrayList<String> labels = new ArrayList<String>();
    private EventDatabase db;
    private User user;

    public InfoHandler(EventDatabase _db) {
        db = _db;
    }

    public InfoHandler(String nameEvent) {

        this.nameEvent = nameEvent;
    }

    public InfoHandler(String nameEvent, Time date, String room, String namePresenter) {

        this.nameEvent = nameEvent;
        this.date = date;
        this.room = room;
        this.namePresenter = namePresenter;

    }

    public InfoHandler(String nameEvent, String room, String namePresenter) {

        this.nameEvent = nameEvent;

        this.room = room;
        this.namePresenter = namePresenter;

    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Time getDate() {
        return date;
    }

    public void setDate(Time date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getNamePresenter() {
        return namePresenter;
    }

    public void setNamePresenter(String namePresenter) {
        this.namePresenter = namePresenter;
    }

    public EventDatabase getDb() {
        return db;
    }

    public void setDb(EventDatabase db) {
        this.db = db;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> orderByEventName() {
        ArrayList<String> business = new ArrayList<>();

        business = db.getDataForEventsByName();
        
        return business;
    }

    public ArrayList<InfoHandler> orderByDate(EventDatabase db) {
        ArrayList<InfoHandler> business = new ArrayList<InfoHandler>();

        //business = db.getDataForEventsByName("SELECT event.event_name, time.time, room.room_name, presenter.presenter_firstname FROM (time INNER JOIN event ON time.time_id= event.time_id_start)"
          //      + "INNER JOIN room ON(time.time_id= room.room_id)INNER JOIN presenter ON (presenter.presenter_id = event.event_id ) ORDER BY time asc ");
        for (int i = 0; i < business.size(); i++) {
            nameEvent = business.get(i).getNameEvent();
            date = business.get(i).getDate();
            room = business.get(i).getRoom();
            namePresenter = business.get(i).getNamePresenter();
        }
        return business;
    }

    public ArrayList<InfoHandler> orderByRoom(EventDatabase db) {
        ArrayList<InfoHandler> business = new ArrayList<InfoHandler>();

        //business = db.getDataForEventsByName("SELECT event.event_name, time.time, room.room_name, presenter.presenter_firstname FROM (time INNER JOIN event ON time.time_id= event.time_id_start)"
          //      + "INNER JOIN room ON(time.time_id= room.room_id)INNER JOIN presenter ON (presenter.presenter_id = event.event_id ) ORDER BY room_name asc ");
        for (int i = 0; i < business.size(); i++) {
            nameEvent = business.get(i).getNameEvent();
            date = business.get(i).getDate();
            room = business.get(i).getRoom();
            namePresenter = business.get(i).getNamePresenter();
        }
        return business;
    }

    public ArrayList<InfoHandler> orderByPresenter(EventDatabase db) {
        ArrayList<InfoHandler> business = new ArrayList<InfoHandler>();

        //business = db.getDataForEventsByName("SELECT event.event_name, time.time, room.room_name, presenter.presenter_firstname FROM (time INNER JOIN event ON time.time_id= event.time_id_start)"
               // + "INNER JOIN room ON(time.time_id= room.room_id)INNER JOIN presenter ON (presenter.presenter_id = event.event_id ) ORDER BY presenter_firstname asc ");
        for (int i = 0; i < business.size(); i++) {
            nameEvent = business.get(i).getNameEvent();
            date = business.get(i).getDate();
            room = business.get(i).getRoom();
            namePresenter = business.get(i).getNamePresenter();
        }
        return business;
    }

    public static String printEvents(ArrayList<String> events) {
        String result = "";
        //  if (events.size() != 0) {

        /*
        for (int i = 0; i < events.size(); i++) {
            result += events.get(i).getNameEvent();
            result += " " + events.get(i).getDate();
            result += " " + events.get(i).getRoom();
            result += " " + events.get(i).getNamePresenter();
            result += "\n";
            //     }
        }*/
        return result;

    }

    public void authenticate(String username, String password) {
        
        user = new User(username);
        user.setDb(db);

        System.out.println(user.getRole());
        
        if (user.login(username, password)) {
            String fullname = username;
            String role = user.getRole();
            JOptionPane.showMessageDialog(null, "Welcome!\nCurrent role: " + role);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect id or password.");
        }
    }
    
    
     public ArrayList<ArrayList<String>> attendeeList() {
		Event event = new Event();
		ArrayList<String> values = new ArrayList<String>();
		values.add("" + event.getEventID());
		ArrayList<ArrayList<String>> result;

		result = db.getData(
				"SELECT attendee.attendee_id, FROM (signup INNER JOIN attendee ON attendee.attendee_id= signup.signup_attendee_id)"
						+ " WHERE signup.signup_event_id= ?;",
				values);
		return result;

	}
	
	public void addAttendee(int id, String fname, String lname, int type, int year){
		Attendee att = new Attendee(id, fname, lname, type, year);
                att.setDb(db);
                att.post();
	}
}
