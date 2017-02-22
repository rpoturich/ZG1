import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class FileSource implements Iterable<String> {
   private List<String> list = newArrayList<String>() ;
   
   public FileSource(String fileName) {
      loadFromFile(fileName) ;
   }
   
   public Iterator<String> iterator() {
      return list.iterator() ;
   }
   
   private void loadFromFile(String fileName) {
   
      try {
         InputStream fis= new FileInputStream(fileName) ;
         Reader isr= new InputStreamReader(fis) ;
         BufferedReaderreader = new BufferedReader(isr) ;
         String line = reader.readLine() ;
         
         while( line != null ) {
            list.add(line) ;
            line = reader.readLine() ;
         }
      } 
      catch(Exception ex) {
         list.clear();  // empty list on exception.
      }
      
      return ;
   }
   
}