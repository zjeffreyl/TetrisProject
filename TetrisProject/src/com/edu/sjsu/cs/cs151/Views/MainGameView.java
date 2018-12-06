package com.edu.sjsu.cs.cs151.Views;

import com.edu.sjsu.cs.cs151.Views.GridView;
import com.edu.sjsu.cs.cs151.Views.HoldBlockView;

import javax.swing.*;
import java.awt.*;

/**
 * The main display for the game
 */
public class MainGameView extends JFrame {

    private HoldBlockView holdBlock;
    private HoldBlockView nextBlock;
    private int dimension;
    private GridView gameGrid;
    private NewGameView panel;
    public static final int NEW_GAME_VIEW_DIMENSION = 450;

    /**
     * Constructs the main game view
     * @param dimension dimension of view
     */
    public MainGameView(int dimension)
    {
        this.dimension = dimension;
        //set frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(dimension, dimension);

        setGamePanels();
    }

    /**
     * Set the game panels into the frame
     */
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

        gameGrid = new GridView(10, 20);
        int gameGridXBorder = (mainGridWidth - gameGrid.width)/2;
        int gameGridYBorder = (mainGridHeight - gameGrid.height)/2;
        gameGrid.createBorder(gameGridXBorder - 7, gameGridYBorder - 30);

        setLayout(layout);
        add(north, layout.NORTH);
        add(holdBlock, layout.WEST);
        add(south, layout.SOUTH);
        add(nextBlock, layout.EAST);
        add(gameGrid, layout.CENTER);
    }

    /**
     * Get the game grid
     * @return gameGrid  the game grid
     */
    public GridView getGameGrid() {
        return gameGrid;
    }

    /**
     * Get the next block
     * @return nextBlock  the next block
     */
    public HoldBlockView getNextBlock()
    {
        return nextBlock;
    }

    public void removeNewGameView()
    {
        getLayeredPane().remove(panel);
        remove(panel);
        revalidate();
        repaint();
    }

    /**
     * Create a new game view
     */
    public void addNewGameView()
    {
        panel = new NewGameView();
        panel.setSize(NEW_GAME_VIEW_DIMENSION,NEW_GAME_VIEW_DIMENSION);
        panel.setPreferredSize(new Dimension(NEW_GAME_VIEW_DIMENSION,NEW_GAME_VIEW_DIMENSION));
        panel.setLocation(125, 125);
        getLayeredPane().add(panel, JLayeredPane.DEFAULT_LAYER);
        add(panel);
    }

    public NewGameView getNewGameView()
    {
        return panel;
    }
}
