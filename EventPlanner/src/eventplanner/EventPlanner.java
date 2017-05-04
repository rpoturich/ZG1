/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventplanner;

import java.util.ArrayList;

/**
 *
 * @author rachelpoturich
 */
public class EventPlanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventDatabase db = new EventDatabase("jdbc:mysql://localhost:3306/events", "root", "G78x6y!#3m(]");
        db.connect();

        InfoHandler student = new InfoHandler(db);

        ArrayList<InfoHandler> events;

        events = student.select(db);

        //System.out.println(events.size() + " events");
        //System.out.println(student.printEvents(events));
        new EventPlannerFrame(events, student);

    }

}
