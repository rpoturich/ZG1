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
        // TODO code application logic here
        EventDatabase db = new EventDatabase("jdbc:mysql://:3306/events?useSSL=false","root","mysql");
        db.connect();
        
         Bussiness student = new Bussiness();
      
         ArrayList<Bussiness> events;
      
          events = student.select(db);
    
          System.out.println(student.printEvents(events));
          
          new EventPlannerFrame();

        
    }
    
}
