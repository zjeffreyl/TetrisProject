package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class GridSquare extends JComponent {
    private int row;
    private int col;
    private Rectangle rectangle;

    public GridSquare(int r, int c, int dimension) {
        row = r;
        col = c;
        rectangle = new Rectangle(r, c, dimension, dimension);
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public int getDimension()
    {
        return rectangle.width;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g.create();
        g2D.setStroke(new BasicStroke(1));
        g2D.setPaint(Color.white);
        g2D.setColor(Color.BLUE);
        g2D.draw(rectangle);
    }
}
