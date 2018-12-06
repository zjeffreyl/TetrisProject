package com.edu.sjsu.cs.cs151.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Class that handles the individual squares of the Grid
 */
public class GridSquare extends JComponent {
    private int row;
    private int col;
    private Rectangle rectangle;
    public Color color;
    private boolean occupied = false;

    /**
     * Contructs a square
     * @param r  the row
     * @param c  the column
     * @param dimension  size of square
     */
    public GridSquare(int r, int c, int dimension) {
        row = r;
        col = c;
        rectangle = new Rectangle(0, 0, dimension, dimension);
    }

    /**
     * Get the row
     * @return row the row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Get the column
     * @return col the col
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Change the occupied
     * @param occupied  whether or not the square is painted
     * @param color  the color to be painted
     */
    public void changeOccupied(boolean occupied, Color color)
    {
        this.occupied = occupied;
        this.color = color;
        repaint();
        revalidate();
    }

    /**
     * Checks whether or not the square is occupied (something is painted)
     * @return occupied
     */
    public boolean isOccupied()
    {
        return occupied;
    }

    /**
     * Get the dimension
     * @return width  the width of the grid
     */
    public int getDimension()
    {
        return rectangle.width;
    }

    /**
     * Paints the components onto the frame
     * @param g graphics parameter
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g.create();
        if(occupied)
        {
            g2D.setColor(color);
            g2D.fill(this.rectangle);
        }
        else {
            g2D.setColor(Color.darkGray);
        }
        g2D.setStroke(new BasicStroke(1));
        g2D.setPaint(Color.white);
        g2D.draw(rectangle);
    }
}
