package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HoldBlockView extends JPanel {

    String titleText;
    GridView grid;
    public HoldBlockView(String titleText)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.titleText = titleText;
        JLabel titleHeading = new JLabel();
        titleHeading.setText(titleText);
        titleHeading.setAlignmentX(CENTER_ALIGNMENT);

        grid = new GridView(2,4);
        grid.setSize(new Dimension(10,10));

        add(titleHeading);
        add(grid);
    }
}
