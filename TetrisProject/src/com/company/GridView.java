package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GridView extends JPanel
{
    int height;
    int width;
    public int rows;
    public int cols;
    public final static int squareDimension = 25;
    GridSquare[] squares;
    public final int borderOffset = 5;

    public GridView(int cols, int rows)
    {
        this.rows = rows;
        this.cols = cols;

        height = rows * squareDimension + borderOffset;
        width = cols * squareDimension + borderOffset;
        setLayout(new GridLayout(rows, cols));
        setPreferredSize(new Dimension(width + 5, height + 5));
        setMaximumSize(new Dimension(width + 5, height + 5));
        squares = new GridSquare[rows*cols];
        for(int i = 0; i < squares.length; i++)
        {
            squares[i] = new GridSquare(1,1, (squareDimension));
            this.add(squares[i]);
        }
        setBackground(Color.darkGray);
    }

    public void createBorder(int xBorder, int yBorder)
    {
        setBorder(new EmptyBorder(yBorder, xBorder, yBorder, xBorder));
    }
}
