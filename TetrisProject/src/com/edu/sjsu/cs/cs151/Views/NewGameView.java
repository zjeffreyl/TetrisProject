package com.edu.sjsu.cs.cs151.Views;

import javax.swing.*;
import java.awt.*;

public class NewGameView extends JPanel {
    public NewGameView()
    {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        JLabel text = new JLabel();
        text.setAlignmentY(CENTER_ALIGNMENT);
        text.setText("<html><center>Start A<br><center>  New Game?<br><center>Press N<html>");
        text.setFont(new Font("Arial", Font.BOLD, 70));
        text.setAlignmentX(CENTER_ALIGNMENT);

        JLabel instructions = new JLabel();
        instructions.setAlignmentY(CENTER_ALIGNMENT);
        instructions.setAlignmentX(CENTER_ALIGNMENT);
        instructions.setText("<html><center><br><br><br>Controls: <br>Left/Right: Translate<br>Up: Rotate<br>Down: Soft Drop<br>Space: Fast Drop<br><html>");
        instructions.setFont(new Font("Arial", Font.BOLD, 15));
        add(text);
        add(instructions);
    }
}
