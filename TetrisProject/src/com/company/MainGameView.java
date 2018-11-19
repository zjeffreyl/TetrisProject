package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainGameView extends JFrame {

    public MainGameView(int dimension)
    {
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);
        setSize(dimension, dimension);
        setLocationRelativeTo(null);

        int gridWidth = (dimension * 2)/5;
        int gridHeight = (dimension * 4)/5;
        int horizontalPadding = (dimension * 3)/10 - 14;
        int verticalPadding = (dimension)/ 10 - 38;
        //side block dimension
        int xSideGridDimension = (dimension * 4)/25;
        int ySideGridDimension = (dimension * 2)/25;


        GridView grid = new GridView(20, 10);
        //grid.squares[0].getGraphics().setColor(Color.BLUE);
        grid.setLocation((dimension/2) - (grid.getWidth()/2),dimension/20);
        //grid.setBorder(new EmptyBorder(20, 10, 20, 10));
        add(grid, BorderLayout.CENTER);

        //5 x 5
        HoldBlockView eastPanel = new HoldBlockView("Next Block");
        eastPanel.setPreferredSize(new Dimension(horizontalPadding, dimension - (verticalPadding * 2)));
        add(eastPanel, BorderLayout.EAST);

        //5 x 5
        HoldBlockView westPanel = new HoldBlockView("Hold Block");
        westPanel.grid.setPreferredSize(new Dimension(xSideGridDimension, ySideGridDimension));
        westPanel.setPreferredSize(new Dimension(horizontalPadding, dimension - (verticalPadding * 2)));
        add(westPanel, BorderLayout.WEST);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(verticalPadding, verticalPadding));
        add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(verticalPadding,verticalPadding));
        add(southPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
