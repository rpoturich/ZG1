/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MarkoZivko96
 */
public class PlayPreviousCommand implements Command{

    private PlayList playPrevious;
    
    public PlayPreviousCommand(PlayList playPrevious){
     
        this.playPrevious = playPrevious;
        
    }

    
    @Override
    public void execute() {
        
        int prevIndex = playPrevious.getSourceIndex() - 1 ;

        
                if( prevIndex >= 0 ) {
                    playPrevious.play(prevIndex) ;
                }

    }
    
    
    
}
