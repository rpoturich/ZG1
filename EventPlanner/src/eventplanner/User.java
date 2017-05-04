package eventplanner;

import java.util.ArrayList;

/**
 *
 * @author rachelpoturich
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private EventDatabase db;
    private String username;

    public User() {
        id = 0;
        username = "";
        role = "";
        
    }

    public User(int _id) {
        id = _id;
    }

    public User(String _username) {
        username = _username;
    }

    public User(int _id, String _firstName, String _lastName, String _password, String _role, EventDatabase _db, String _username) {
        id = _id;
        firstName = _firstName;
        lastName = _lastName;
        password = _password;
        role = _role;
        db = _db;
        username = _username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public EventDatabase getDb() {
        return db;
    }

    public void setDb(EventDatabase db) {
        this.db = db;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public boolean login(String _user, String pass) {
        boolean isLoggedIn = false;

        ArrayList<String> values = new ArrayList<>();
        values.add(_user);
        //System.out.println(_user); //PRINT CHECK
        values.add(pass);
        //System.out.println(pass); //PRINT CHECK
        //System.out.println(values.size());

        //System.out.println("Attempting to login"); //PRINT CHECK

        String query = "SELECT * FROM user WHERE username= ? AND password= ?;";

        //System.out.println("Query: " + query);

        ArrayList<ArrayList<String>> results;
        results = db.getData(query, values); //call getData using PreparedStatement

        if (results.isEmpty()) {
            isLoggedIn = false;
        } else {
            isLoggedIn = true;
            ArrayList<String> row = results.get(1);

            System.out.println(row);
            System.out.println(row.get(1));
            
            
            setId(Integer.parseInt(row.get(0)));
            setUsername(row.get(1));
            setRole(row.get(3));
            
        }

        return isLoggedIn;
    }

}
