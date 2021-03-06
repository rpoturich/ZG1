/*
 * Command to resize and Image
 *
 * Pattern: Command
 */

package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.Image;
import edu.rit.swen383_800_g2.Composite.ImgComponent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class ResizeCommand implements Command {

    
    private ArrayList<BufferedImage> imgList;
    private BufferedImage[] resized;
    private ImgComponent img;
    private int columns;
    
    public ResizeCommand(ArrayList<BufferedImage> imgList, ImgComponent img, int columns){
        
        this.imgList = imgList;
        this.img = img;
        this.columns = columns;
        
    }
    
    @Override
    public void execute() {
        
        Image image = (Image) img;
        resized = image.resize(columns, imgList);
        
    }

   

    
}
