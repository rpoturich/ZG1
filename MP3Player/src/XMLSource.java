
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author rachelpoturich
 */
public class XMLSource implements Iterable<String>{
 
    private String xmlFile;
    private List<String> xml;
    
    public XMLSource(String _xmlFile){
        xmlFile = _xmlFile;
    }
    
    public Iterator<String> iterator(){
        return xml.iterator();
    }
    
}
