/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MarkoZivko96
 */
public class PlayNextCommand implements Command {
 
    private PlayList playNext;
    
    public PlayNextCommand(PlayList playNext){
        
        this.playNext = playNext;
        
    }

    
    public void execute(){
        
        int nextIndex = playNext.getSourceIndex() + 1 ;
        
        if( nextIndex < playNext.size() ) {
                    playNext.play(nextIndex) ;
                }

    }
        
    
}
