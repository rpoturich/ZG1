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
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author rachelpoturich
 */
public class SearchCommand implements Command {

    private ViewMode viewMode;
    private JTextField searchTextBox;
    private JButton searchButton;
    private ArrayList<ImgComponent> components;

    public SearchCommand(ViewMode _viewMode) {
        viewMode = _viewMode;
        searchTextBox = viewMode.getSearchField();
        searchButton = viewMode.getEnterSearch();
        components = viewMode.getComponentsArray();
    }

    @Override
    public void execute() {
        String labelToFind = searchTextBox.getText();

        if (labelToFind.equals("") || labelToFind == null) {
            JOptionPane.showMessageDialog(null, "Please enter search criteria");
        } else {

            ArrayList<ImgComponent> foundComponents = new ArrayList();
            ArrayList<String> foundLabels;

            for (ImgComponent ic : components) {
                foundLabels = ic.getLabels();

                for (String s : foundLabels) {
                    if (s.equalsIgnoreCase(labelToFind)) {
                        System.out.println(s); //PRINT CHECK
                        foundComponents.add(ic);
                    } //if

                } //inner for

            } //outer for

            JFrame foundFrame = new JFrame();
            foundFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            JPanel foundPanel = new JPanel(new GridLayout(2, 0));

            for (ImgComponent ic : foundComponents) {
                foundPanel.add(ic.getSmallIcon());
            }

            foundFrame.add(foundPanel);
            foundFrame.setSize(350, 350);
            foundFrame.setVisible(true);
        }
    }

    @Override
    public BufferedImage[] getImg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
