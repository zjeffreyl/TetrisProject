package com.edu.sjsu.cs.cs151;

import javax.swing.*;
import java.awt.*;

public class GridSquare extends JComponent {
    private int row;
    private int col;
    private Rectangle rectangle;
    public Color color;
    private boolean occupied = false;

    public GridSquare(int r, int c, int dimension, Color color) {
        row = r;
        col = c;
        this.color = color;
        rectangle = new Rectangle(0, 0, dimension, dimension);
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public void changeOccupied(boolean occupied)
    {
        this.occupied = occupied;
        revalidate();
    }

    public int getDimension()
    {
        return rectangle.width;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g.create();
        if(occupied)
        {
            g2D.setColor(color);
            g2D.fill(this.rectangle);
        }
        g2D.setStroke(new BasicStroke(1));
        g2D.setPaint(Color.white);
        g2D.draw(rectangle);
    }
}
