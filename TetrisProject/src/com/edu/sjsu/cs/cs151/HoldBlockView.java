package com.edu.sjsu.cs.cs151;

import javax.swing.*;

import static com.edu.sjsu.cs.cs151.GridView.squareDimension;

public class HoldBlockView extends JPanel {

    String titleText;
    GridView grid;

    public HoldBlockView(String titleText)
    {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        this.titleText = titleText;
        JLabel titleHeading = new JLabel();
        titleHeading.setText(titleText);
        titleHeading.setAlignmentX(CENTER_ALIGNMENT);

        grid = new GridView(4,2);
        int gameGridXBorder = (grid.width - (squareDimension * grid.cols))/2;
        int gameGridYBorder = (grid.height - (squareDimension * grid.rows))/2;
        grid.createBorder(gameGridXBorder, gameGridYBorder + 2);

        add(titleHeading);
        add(grid);
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    //
    public void inputTetromino(Model.Tetromino newTetromino)
    {
        Model.Coordinate[] coords = newTetromino.getCoordinates();
        for(Model.Coordinate coordinate : coords)
        {
            GridSquare square = grid.squares[coordinate.getY()][coordinate.getX()];
            square.color = newTetromino.color;
            //Color in
            square.changeOccupied(true, newTetromino.color);
        }
    }
}
