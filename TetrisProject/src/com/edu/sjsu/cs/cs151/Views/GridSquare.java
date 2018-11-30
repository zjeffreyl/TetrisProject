package com.edu.sjsu.cs.cs151.Views;

import javax.swing.*;
import java.awt.*;

public class GridSquare extends JComponent {
    private int row;
    private int col;
    private Rectangle rectangle;
    public Color color;
    private boolean occupied = false;

    public GridSquare(int r, int c, int dimension) {
        row = r;
        col = c;
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

    public void changeOccupied(boolean occupied, Color color)
    {
        this.occupied = occupied;
        this.color = color;
        repaint();
        revalidate();
    }

    public boolean isOccupied()
    {
        return occupied;
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
        else {
            g2D.setColor(Color.darkGray);
        }
        g2D.setStroke(new BasicStroke(1));
        g2D.setPaint(Color.white);
        g2D.draw(rectangle);
    }
}
