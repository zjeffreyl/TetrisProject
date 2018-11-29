package com.edu.sjsu.cs.cs151.Views;

import com.edu.sjsu.cs.cs151.Models.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GridView extends JPanel
{
    int height;
    int width;
    public int rows;
    public int cols;
    public final static int SQUAREDIMENSION = 25;
    GridSquare[][] squares;
    public final int BORDEROFFSET = 5;
    public final Color DEFAULTCOLOR = Color.darkGray;

    public GridView(int cols, int rows)
    {
        this.rows = rows;
        this.cols = cols;

        height = rows * SQUAREDIMENSION + BORDEROFFSET;
        width = cols * SQUAREDIMENSION + BORDEROFFSET;
        setLayout(new GridLayout(rows, cols));
        setPreferredSize(new Dimension(width + 5, height + 5));
        setMaximumSize(new Dimension(width + 5, height + 5));
        squares = new GridSquare[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                squares[i][j] = new GridSquare(i, j, (SQUAREDIMENSION));
                this.add(squares[i][j]);
            }
        }
        setBackground(Color.darkGray);
    }

    public void createBorder(int xBorder, int yBorder)
    {
        setBorder(new EmptyBorder(yBorder, xBorder, yBorder, xBorder));
    }

    public GridSquare[][] getSquares()
    {
        return squares;
    }
}
