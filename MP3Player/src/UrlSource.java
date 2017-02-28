
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author rachelpoturich
 */
public class UrlSource implements Iterable<String>{
 
    private String urlSrc;
    private List<String> url;
    
    public UrlSource(String _urlSrc){
        urlSrc = _urlSrc;
    }
    
    public Iterator<String> iterator(){
        return url.iterator();
    }
    
}
