package com.edu.sjsu.cs.cs151.Views;

import com.edu.sjsu.cs.cs151.Models.Model;

import javax.swing.*;

import static com.edu.sjsu.cs.cs151.Views.GridView.SQUAREDIMENSION;

/**
 * A display for hold block
 */
public class HoldBlockView extends JPanel {

    /**
     * Constructs a panel for hold block
     * @param titleText  the title of the box
     */
    public HoldBlockView(String titleText)
    {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
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
