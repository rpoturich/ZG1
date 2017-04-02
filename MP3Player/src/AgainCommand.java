/**
 *
 * @author rachelpoturich
 */
public class AgainCommand implements Command{
    
    private PlayList playNext;
    
    public AgainCommand(PlayList playNext){
        
        this.playNext = playNext;
        
    }
    
    public void execute(String[] arguments){
        
        playNext.play(playNext.getSourceIndex());
        
    }
    
}
