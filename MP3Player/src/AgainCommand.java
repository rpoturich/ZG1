/**
 *
 * @author rachelpoturich
 */
public class AgainCommand implements Command{
    
    private PlayList playNext;
    
    public AgainCommand(PlayList playNext){
        
        this.playNext = playNext;
        
    }
    
    public void execute(){
        
        playNext.play(playNext.getSourceIndex());
        
    }
    
}
