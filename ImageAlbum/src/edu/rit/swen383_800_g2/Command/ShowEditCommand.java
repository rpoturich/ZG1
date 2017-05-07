/*
 * Command to show the EditMode window and manipulate an Image
 *
 * Pattern: Command
 */


package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import edu.rit.swen838_800_g2.View.EditMode;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author rachelpoturich
 */
public class ShowEditCommand implements Command{

    private ImgComponent ic;
    
    public ShowEditCommand(ImgComponent i){
        ic = i;
    }
    
    @Override
    public void execute() {
        JFrame editFrame = new EditMode(ic);
        editFrame.setVisible(true);
        editFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    
    
}
