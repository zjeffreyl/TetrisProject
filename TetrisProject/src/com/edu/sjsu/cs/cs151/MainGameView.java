package com.edu.sjsu.cs.cs151;

import javax.swing.*;
import java.awt.*;

public class MainGameView extends JFrame {

    private HoldBlockView holdBlock;
    private HoldBlockView nextBlock;
    private int dimension;

    public MainGameView(int dimension)
    {
        this.dimension = dimension;
        //set frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(dimension, dimension);

        setGamePanels();

        KeyListener keyListener = new KeyListener(this);
        this.addKeyListener(keyListener);
    }

    public void setGamePanels()
    {
        //layout Sizes
        int mainGridWidth = (dimension * 3)/7;
        int mainGridHeight = (dimension * 6)/7;

        //side/top bottom panels
        int sidePanelWidth = (dimension - mainGridWidth)/2;
        int bottomPanelHeight = dimension / 28;
        int topPanelHeight = 3 * bottomPanelHeight;

        //Layout
        BorderLayout layout = new BorderLayout();

        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(dimension, topPanelHeight));
        //Tetris Label
        JLabel title = new JLabel("TETRIS");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        north.add(title);


        holdBlock = new HoldBlockView("Hold Block");
        holdBlock.setPreferredSize(new Dimension(sidePanelWidth, dimension));
        holdBlock.setBackground(Color.CYAN);

        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(dimension, bottomPanelHeight));
        south.setBackground(Color.RED);

        nextBlock = new HoldBlockView("Next Block");
        nextBlock.setBackground(Color.YELLOW);
        nextBlock.setPreferredSize(new Dimension(sidePanelWidth, dimension));

        GridView gameGrid = new GridView(10, 20);
        int gameGridXBorder = (mainGridWidth - gameGrid.width)/2;
        int gameGridYBorder = (mainGridHeight - gameGrid.height)/2;
        gameGrid.createBorder(gameGridXBorder - 7, gameGridYBorder - 30);
        gameGrid.squares[1][1].color = Color.blue;
        gameGrid.squares[1][1].changeOccupied(true);

        setLayout(layout);
        add(north, layout.NORTH);
        add(holdBlock, layout.WEST);
        add(south, layout.SOUTH);
        add(nextBlock, layout.EAST);
        add(gameGrid, layout.CENTER);
    }

    public HoldBlockView getNextBlock()
    {
        return nextBlock;
    }

    public HoldBlockView getHoldBlock()
    {
        return holdBlock;
    }
}
