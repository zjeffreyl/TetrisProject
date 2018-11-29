package com.edu.sjsu.cs.cs151.Views;

import com.edu.sjsu.cs.cs151.Models.Model;

import javax.swing.*;

import static com.edu.sjsu.cs.cs151.Views.GridView.SQUAREDIMENSION;

public class HoldBlockView extends JPanel {

    String titleText;
    GridView grid;

    public HoldBlockView(String titleText)
    {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        this.titleText = titleText;
        JLabel titleHeading = new JLabel();
        titleHeading.setText(titleText);
        titleHeading.setAlignmentX(CENTER_ALIGNMENT);

        grid = new GridView(4,2);
        int gameGridXBorder = (grid.width - (SQUAREDIMENSION * grid.cols))/2;
        int gameGridYBorder = (grid.height - (SQUAREDIMENSION * grid.rows))/2;
        grid.createBorder(gameGridXBorder, gameGridYBorder + 2);

        add(titleHeading);
        add(grid);
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }
}
