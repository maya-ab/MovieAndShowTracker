package ui;

import javax.swing.*;
import java.awt.*;

//Represents a button with a specific design
public class ButtonDesign {

    String buttonName;
    JButton button;

    //MODIFIES: this
    //EFFECTS: Creates button with given buttonName
    ButtonDesign(String buttonName) {
        String buttonText = formatButtonText(buttonName);
        button = new JButton(buttonText);
        this.buttonName = buttonName;
        formatButton();
    }

    public JButton getButton() {
        return button;
    }

    public void editText(String bn) {
        this.buttonName = bn;
    }

    //MODIFIES: this
    //EFFECTS: formats button's position and design
    public void formatButton() {
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setVerticalAlignment(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);

        button.setFont(new Font("Helvetica", Font.PLAIN, 18));
        button.setOpaque(true);
        button.setBackground(Color.getHSBColor(357, 86, 58));
        button.setOpaque(true);

        button.setBorderPainted(false);
    }

    //EFFECTS: returns a formatted string
    public String formatButtonText(String buttonName) {
        return "<html>\n"
                +
                "<head>\n"
                +
                "<style>\n"
                +
                "p {text-align: center;}\n"
                +
                "</style>\n"
                +
                "</head>\n"
                +
                "<body>\n"
                +
                "<p>" + buttonName
                +
                "</p>\n"
                +
                "\n"
                +
                "</body>\n"
                +
                "</html>";
    }
}
