

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MarkoZivko96
 */
public class QuitCommand implements Command {
    
    public void execute(String[] arguments){
        
        System.exit(0);
        
    }
    
}
