/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mirko Siljeg
 */
public class PauseCommand implements Command{
    
     
    PlayList pl;
    
    public PauseCommand(PlayList pl_){
        pl = pl_;
    }
    
    public void execute(){
        pl.pause();
    }
    
}
