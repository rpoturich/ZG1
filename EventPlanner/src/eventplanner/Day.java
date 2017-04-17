package eventplanner;

import java.util.ArrayList;

/**
 * Created by Dan on 17.4.2017..
 */
public class Day {

    public int dayID;
    public String dayName;
    private EventDatabase db;
    
    ArrayList<String> arrayList;
    ArrayList<ArrayList<String>> list;

    public Day(int dayID, String dayName){

        this.dayID = dayID;
        this.dayName = dayName;

    }

    public Day(int DayID){

        this.dayID = dayID;

    }

    public int getDayID(){

        return dayID;

    }

    public String getDayName(){

        return dayName;
    }

    public void setDayID(int dayID){

        this.dayID = dayID;

    }

    public void setDayName(String dayName){

        this.dayName = dayName;

    }


    public void fetch(ArrayList<String> values){
        String query = "SELECT * FROM day WHERE day_id= ? ;";
        
        ArrayList<ArrayList<String>> results;
        results = db.getData(query, values); //call getData using PreparedStatement
        ArrayList<String> row = results.get(1);

        setDayID(Integer.parseInt(row.get(1)));
        setDayName(row.get(2));

    }

    public void put(){
        String day = "" + getDayID();
        String name = getDayName();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(day);
        values.add(name);
        values.add(day);
        
        String updateStatement = "UPDATE day SET day_id= ? , day_name= ? WHERE day_id= ? ;";

        db.setData(updateStatement, values); //call setData using PreparedStatement

    }

    public void post(){
        String day = "" + getDayID();
        String name = getDayName();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(day);
        values.add(name);
        
        String insertStatement = "INSERT INTO day VALUES ( ? , ? );";

        db.setData(insertStatement, values); //call setData using PreparedStatement

    }

    public void delete(){
        String id = "" + getDayID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        
        String deleteStatement = "DELETE FROM day WHERE day_id= ? ;";

        db.setData(deleteStatement, values); //call setData using PreparedStatement

    }

}