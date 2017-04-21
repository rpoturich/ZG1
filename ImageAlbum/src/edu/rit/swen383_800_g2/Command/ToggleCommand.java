/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rit.swen383_800_g2.Command;

import edu.rit.swen383_800_g2.Composite.ImgComponent;
import edu.rit.swen838_800_g2.View.*;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author rachelpoturich
 */
public class ToggleCommand implements Command {

    private ViewMode viewMode;
    private JButton view;
    private JPanel centerPanel;
    private ArrayList<ImgComponent> components;
    
    public ToggleCommand(ViewMode _viewMode){
        viewMode = _viewMode;
        
        view = viewMode.getView();
        centerPanel = viewMode.getCenterPanel();
        components = viewMode.getComponentsArray();
    }
    
    @Override
    public void execute() {
        
        if (view.getText().equals("4")) {
            view.setText("1");
            centerPanel.setLayout(new GridLayout(0, 1));

            for (ImgComponent ic : components) {
                ic.getLargeIcon();
            }
        } else if (view.getText().equals("1")) {
            view.setText("4");
            centerPanel.setLayout(new GridLayout(0, 2));

            for (ImgComponent ic : components) {
                ic.getSmallIcon();
            }
        }

    }

    @Override
    public BufferedImage[] getImg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
