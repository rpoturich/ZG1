import static java.util.Arrays.asList;
import java.util.List;
import java.util.Iterator;

public class ArraySource implements Iterable<String> {
   private final List<String> list ;
   
   publicArraySource(String[] args) {
      list = asList(args) ;
   }
   
   public Iterator<String> iterator() {
      return list.iterator() ;
   }
}