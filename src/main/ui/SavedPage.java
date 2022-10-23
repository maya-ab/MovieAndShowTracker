
package ui;

import ui.console.Main;

import javax.swing.*;
import java.awt.*;

//Source: based on https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// TextFieldDemo, TabbedPaneDemo, Button Demo, IconDemo, LabelDemo


//Creates screen telling user they've saved their titles
public class SavedPage extends JPanel {

    //MODIFIES: this
    //EFFECTS: Sets up layout
    public SavedPage() {
        super(new GridLayout(1, 1));
        ImageIcon ic = createImageIcon("Images/saved.gif");
        JLabel exitLabel = new JLabel("Your Titles Have been saved!");
        exitLabel.setIcon(ic);
        add(exitLabel);
        exitLabel.setVerticalTextPosition(SwingConstants.TOP);
        exitLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    //EFFECTS: Creates and returns an image icon with given path and description
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainPage.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates and sets up GUI window
    public static void createGUI() {
        JFrame frame = new JFrame("");

        SavedPage newContentPane = new SavedPage();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

}