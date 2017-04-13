
package edu.rit.swen838_800_g2.View;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rachelpoturich
 */
public abstract class ViewMode extends JFrame{
    
    protected JPanel mainPanel;
    
    public ViewMode(){
    
        setTitle("Image Album");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(mainPanel);
        setVisible(true);
    
    }
    
    public abstract void loadImages();
    
}
