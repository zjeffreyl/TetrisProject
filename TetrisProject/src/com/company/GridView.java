package com.company;

import javax.swing.*;
import java.awt.*;

public class GridView extends JPanel
{
    int height;
    int width;
    public final int squareDimension = 40;
    public final int widthOffset = 20;
    public final int heightOffset = 40;
    GridSquare[] squares;

    public GridView(int rows, int cols)
    {
        height = rows * squareDimension + heightOffset;
        width = cols * squareDimension + widthOffset;
        this.setLayout(new GridLayout(rows, cols, 0, 0));
        this.setBackground(Color.darkGray);
        squares = new GridSquare[rows*cols];
        for(int i = 0; i < squares.length; i++)
        {
            squares[i] = new GridSquare(1,1, (squareDimension));
            this.add(squares[i]);
        }
        this.setSize(width, height);
        this.setVisible(true);
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }
}
