/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileLoader;

/**
 *
 * @author rachelpoturich
 */
public class FileLoader {
    
    private String path;
    
    public FileLoader(String pathOrUrl){
    
        path = pathOrUrl;
        
    }
    
    //is a path
    public void loadFromFile(){}
    
    
    //is a url
    public void loadFromWeb(){}
    
}
