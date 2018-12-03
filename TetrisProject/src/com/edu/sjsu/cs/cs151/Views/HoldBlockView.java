package com.edu.sjsu.cs.cs151.Views;

import com.edu.sjsu.cs.cs151.Models.Model;

import javax.swing.*;

import static com.edu.sjsu.cs.cs151.Views.GridView.SQUAREDIMENSION;

/**
 * A display for hold block
 */
public class HoldBlockView extends JPanel {

    String titleText;
    GridView grid;

    /**
     * Constructs a panel for hold block
     * @param titleText  the title of the box
     */
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

    /**
     * Get the height
     * @return height
     */
    @Override
    public int getHeight() {
        return super.getHeight();
    }

    /**
     * Get the width
     * @return the width
     */
    @Override
    public int getWidth() {
        return super.getWidth();
    }
}
