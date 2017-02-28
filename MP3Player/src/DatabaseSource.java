
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author rachelpoturich
 */
public class DatabaseSource implements Iterable<String>{
 
    private String database;
    private List<String> db;
    
    public DatabaseSource(String _database){
        database = _database;
    }
    
    public Iterator<String> iterator(){
        return db.iterator();
    }
    
}
