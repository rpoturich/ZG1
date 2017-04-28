/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventplanner;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author Abii
 */
public class Bussiness {

    String nameEvent;
    Time date;
   // int date;
    String room;
    String namePresenter;
    private ArrayList<String> labels = new ArrayList<String>();
    
    public Bussiness(){
        
        
    }
    public Bussiness(String nameEvent){
        
        this.nameEvent = nameEvent;
    }
    public Bussiness(String nameEvent, Time date, String room, String namePresenter) {

        this.nameEvent = nameEvent;
        this.date = date;
        this.room = room;
        this.namePresenter = namePresenter;

    }
public Bussiness(String nameEvent,  String room, String namePresenter) {

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

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> newLabels) {
        labels = newLabels;
    }

    public ArrayList<Bussiness> select(EventDatabase db) {
        ArrayList<Bussiness> bussiness = new ArrayList<Bussiness>();

        bussiness = db.getDataWithColumns("SELECT event_name,time,room_name,presenter_firstname FROM event,time,room,presenter ");
        for (int i = 0; i < bussiness.size(); i++) {
            nameEvent = bussiness.get(i).getNameEvent();
            date = bussiness.get(i).getDate();
            room = bussiness.get(i).getRoom();
            namePresenter = bussiness.get(i).getNamePresenter();
        }
        return bussiness;
    }

    public String printEvents(ArrayList<Bussiness> events) {
        String result = "";
      //  if (events.size() != 0) {

            for (int i = 0; i < events.size(); i++) {
                result += events.get(i).getNameEvent();
                result += " " + events.get(i).getDate();
                result += " " + events.get(i).getRoom();
                result += " " + events.get(i).getNamePresenter();
                result += "\n";
     //     }
        }
        return result;

    }
}
