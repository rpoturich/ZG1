package eventplanner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Dan on 17.4.2017..
 */
public class Time {

    public int timeID;
    public java.sql.Time time;
    private EventDatabase db;

    public Time(int timeID, java.sql.Time time){

        this.timeID = timeID;
        this.time = time;

    }

    public Time(int timeID){

        this.timeID = timeID;

    }

    public int getTimeID(){

        return timeID;

    }

    public java.sql.Time getTime(){

        return time;
    }

    public void setTimeID(int timeID){

        this.timeID = timeID;

    }

    public void setTime(java.sql.Time time){

        this.time = time;

    }


    public void fetch(ArrayList<String> values){
        String query = "SELECT * FROM time WHERE time_id= ? ;";
        
        ArrayList<ArrayList<String>> results;
        results = db.getData(query, values); //call getData using PreparedStatement
        ArrayList<String> row = results.get(1);

        setTimeID(Integer.parseInt(row.get(1)));
        String resultTime = row.get(2);
        DateFormat format = new SimpleDateFormat("HH:mm");
        java.sql.Time resultSqlTime = null; 
        
        try{
            resultSqlTime = new java.sql.Time(format.parse(resultTime).getTime());
            
        } catch(ParseException pe){
            pe.printStackTrace();
        }
        
        setTime(resultSqlTime);

    }

    
    public void put(){
        String id = "" + getTimeID();
        String sTime = "" + getTime();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        values.add(sTime);
        values.add(id);
        
        String updateStatement = "UPDATE time SET time_id= ? , time= ? WHERE time_id= ? ;";

        db.setData(updateStatement, values); //call setData using PreparedStatement

    }

    
    public void post(){
        String id = "" + getTimeID();
        String sTime = "" + getTime();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        values.add(sTime);
        
        String insertStatement = "INSERT INTO time VALUES ( ? , ? );";

        db.setData(insertStatement, values); //call setData using PreparedStatement

    }

    
    public void delete(){
        String id = "" + getTimeID();
        
        //set List of values for PreparedStatement
        ArrayList<String> values = new ArrayList<String>();
        values.add(id);
        
        String deleteStatement = "DELETE FROM time WHERE time_id= ? ;";

        db.setData(deleteStatement, values); //call setData using PreparedStatement

    }

}

