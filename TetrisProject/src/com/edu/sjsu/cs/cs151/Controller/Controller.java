package com.edu.sjsu.cs.cs151.Controller;

import com.edu.sjsu.cs.cs151.Views.Message;
import com.edu.sjsu.cs.cs151.Models.Model;
import com.edu.sjsu.cs.cs151.Tetris;
import com.edu.sjsu.cs.cs151.Valve.Valve;
import com.edu.sjsu.cs.cs151.Valve.ValveResponse;
import com.edu.sjsu.cs.cs151.Views.GridView;
import com.edu.sjsu.cs.cs151.Views.MainGameView;
import com.edu.sjsu.cs.cs151.Views.View;

import java.awt.*;
import java.util.Timer;

public class Controller {
    private View view;
    private Model model;
    private Valve[] valves;
    private Model.NextTetrominoGenerator nextTetrominoGenerator;
    private MainGameView mainGameView;
    private GridView gameGrid;

    private Model.Tetromino currentTetromino;

    public int roundsPassed = 0;
    public boolean needToStartNewRound = false;
    public boolean tetrominoDead = false;
    public boolean stopPoll = false;
    ValveResponse response;
    private static Timer timer;


    /**
     * Ctor for controller
     * @param view
     * @param model
     */
    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        nextTetrominoGenerator = model.new NextTetrominoGenerator();

        valves = new Valve[]{new DoNewGameValve(), new DoHardDropValve(), new DoSoftDropValve(), new DoLeftValve(),
                new DoRightValve(), new DoRotateValve(), new DoQuitValve()};
        mainGameView = view.getMainGameView();
        gameGrid = mainGameView.getGameGrid();

    }

    public Timer getTimer()
    {
        return timer;
    }

    /**
     * Spawns a tetromino
     */
    public void spawnTetromino()
    {
        setCurrentTetromino(nextTetrominoGenerator.generateRandom());
        if(!translateTetromino(4, 0, true))
        {
            System.out.println("Game Over");
            timer.cancel();
            gameOverCleanUp();
            return;
        }
        paintTetromino(true);
    }

    public void gameOverCleanUp()
    {
        System.out.println("Game Over");
        setCurrentTetromino(null);
        view.getMainGameView().addNewGameView();
    }

    public void clearGrid()
    {
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                gameGrid.getSquares()[i][j].changeOccupied(false, null);
            }
        }
    }

    public void newGame()
    {
        if(timer != null)
        {
            timer.cancel();
        }
        clearGrid();
        roundsPassed = 0;
        view.getMainGameView().removeNewGameView();
        timer = new Timer();
        timer.schedule(new Tetris.DropTimer(),1500, 1000);
        newRound();
    }

    /**
     * Sets the current tetromino
     * @param newTetromino  the tetromino to be set
     */
    public void setCurrentTetromino(Model.Tetromino newTetromino)
    {
        currentTetromino = newTetromino;
    }

    /**
     * Paint or delete tetromino (based on its values)
     * @param paint  removes or paints the tetromino based on true or false
     */
    public void paintTetromino(boolean paint)
    {
        for(Model.Coordinate coord: currentTetromino.getCoordinates())
        {
            gameGrid.getSquares()[coord.getY()][coord.getX()].changeOccupied(paint, currentTetromino.getColor());
        }
    }

    /**
     * Rotate the tetromino
     */

    public void doRotate()
    {
        if(currentTetromino == null)
        {
            return;
        }
        if (currentTetromino.getColor() != Color.YELLOW)
        {
            int[] rotateResults;
            boolean collision = false;
            paintTetromino(false);

            // obtain newly rotated coordinates
            rotateResults = currentTetromino.rotate();

            // rotate Tetromino back to original state
            currentTetromino.rotate();
            currentTetromino.rotate();
            currentTetromino.rotate();

            for(int x = 0; x < 8; x += 2)
            {
                if (hasCollision(rotateResults[x], rotateResults[x+1])) {
                    collision = true;
                    paintTetromino(true);
                    break;
                }
            }

            if (!collision)
            {
                currentTetromino.rotate();
                paintTetromino(true);
            }
        }
    }

    /**
     * Checks the bound based on x & y movement
     * @param xMovement  the amount of x to be added to col
     * @param yMovement  the amount of y to be added to row
     * @return boolean   whether or not the bound was touched
     */
    public synchronized boolean checkBound(int xMovement, int yMovement, boolean falling)
    {
        for(Model.Coordinate coordinate: currentTetromino.getCoordinates())
        {
            int predictedX = coordinate.getX() + xMovement;
            int predictedY = coordinate.getY() + yMovement;
            //STOP the movement direction conditions
            //1 next move for any of the blocks move past x perimeter
            //2 next move for any of the blocks move past y bottom
            //3 next move for any blocks contain an occupied block
            if (predictedX > 9 || predictedX < 0 || predictedY > 19 || hasCollision(predictedX, predictedY)) {
                //Condition for the block to move no further via stop falling
                //1 next move for any of the blocks move past y bottom
                //2 next move has a collision with occupied when the move is vertical
                if(predictedY > 19 || (hasCollision(predictedX, predictedY) && falling))
                {
                    //We will need to stop any further controls until next second
                    stopPoll = true;
                    //End the reference to this tetromino
                    tetrominoDead = true;
                    //start polling again
                    stopPoll = false;
                    return false;
                }
                tetrominoDead = false;
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if there is a collision
     * @param predictedX  the possible new X coordinate
     * @param predictedY  the possible new Y coordinate
     * @return boolean    whether or not a collision occurred
     */

    public boolean hasCollision(int predictedX, int predictedY) {
        if (predictedX >= 0 && predictedX <= 9 && predictedY >= 0 && predictedY <= 19) {
            if (gameGrid.getSquares()[predictedY][predictedX].isOccupied()) {
                if (!currentTetromino.contains(predictedX, predictedY)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Checks if a row can be cleared
     * @param checkRowAt  the row of the array
     * @return  boolean   whether or not the all the columns of the row is occupied
     */
    public boolean checkClearRow(int checkRowAt)
    {
        int counter = 0;
        // Optimized check row
        if(gameGrid.getSquares()[checkRowAt][0].isOccupied()) {
            for(int i = 0; i < 10; i++) {
                if(gameGrid.getSquares()[checkRowAt][i].isOccupied())
                {
                    counter++;
                }
            }
            if (counter == 10)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear the row if all the columns are occupied
     */
    public void clearRow()
    {
        int clears = 0;
        int i = 19;
        //optimize by limit row parse
        while(i >= 0 && clears <= 4) {
            while (checkClearRow(i)) {
                clears++;
                shiftLines(i);
                //gameGrid.setRowUnoccupied(i);
            }
            i--;
        }

    }

    /**
     * Shift the lines once a row has been cleared
     * @param row  the row that was cleared
     */
    public void shiftLines(int row)
    {
        while (row > 0)
        {
            for (int x = 0; x <= 9; x++) {
                gameGrid.getSquares()[row][x].changeOccupied(gameGrid.getSquares()[row-1][x].isOccupied(),
                        gameGrid.getSquares()[row-1][x].color);
            }
            row--;
        }
    }

    /**
     * Fast drop when 'space' pressed
     */
    public void fastDrop(){
        while(translateTetromino(0,1, true)) {

        }
    }

    /**
     * Translate the tetromino left, right, down
     * @param addX  the amount of left/right shift
     * @param addY  the amount of down shift
     * @return boolean  whether or not the amount can be shifted
     */
    public boolean translateTetromino(int addX, int addY, boolean falling)
    {
        if(currentTetromino == null)
        {
            return false;
        }
        if(checkBound(addX, addY, falling))
        {
            paintTetromino(false);
            currentTetromino.moveTetromino(addX, addY);
            paintTetromino(true);
            return true;
        }
        return false;
    }

    /**
     * Get the current tetromino
     * @return currentTetromino  the current tetromino
     */
    public Model.Tetromino getCurrentTetromino()
    {
        return currentTetromino;
    }

    /**
     * Creates a new round
     */
    public synchronized void newRound()
    {
        roundsPassed++;
        clearRow();
        spawnTetromino();
        tetrominoDead = false;
    }

    /**
     * A class for New Game Valve
     */
    private class DoNewGameValve implements Valve
    {

        /**
         * Execute the message of creating new game using valve
         * @param message  the message
         * @return  ValveResponse  the valve response
         */
        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.NewGameMessage.class)
            {
                return ValveResponse.MISS;
            }
            newGame();
            return ValveResponse.EXECUTED;
        }

    }

    /**
     * A class for Hard Drop Valve
     */
    private class DoHardDropValve implements Valve
    {

        /**
         * Execute the message of hard drop using valve
         * @param message  the message
         * @return  ValveResponse  the valve response
         */
        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.FastDropMessage.class)
            {
                return ValveResponse.MISS;
            }
            fastDrop();
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * a class for Soft Drop Valve
     */
    private class DoSoftDropValve implements Valve
    {

        /**
         * Execute the message of soft drop using valve
         * @param message  the message
         * @return  ValveResponse  the valve response
         */
        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.SlowDropMessage.class)
            {
                return ValveResponse.MISS;
            }
            translateTetromino(0, 1, true);
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * a class for shift Left Valve
     */
    private class DoLeftValve implements Valve
    {
        /**
         * Execute the message of shift Left using valve
         * @param message  the message
         * @return ValveResponse  the valve response
         */
        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.LeftMessage.class)
            {
                return ValveResponse.MISS;
            }
            translateTetromino(-1, 0, false);
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * a class for shift Right Valve
     */
    private class DoRightValve implements Valve
    {
        /**
         * Execute the message of shift Right using valve
         * @param message  the message
         * @return ValveResponse  the valve response
         */
        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.RightMessage.class)
            {
                return ValveResponse.MISS;
            }
            translateTetromino(1, 0, false);
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * a class for Rotate valve
     */
    private class DoRotateValve implements Valve
    {

        /**
         * Execute the message of Rotate using valve
         * @param message  the message
         * @return  ValveResponse  the valve response
         */
        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.RotateMessage.class)
            {
                return ValveResponse.MISS;
            }

            doRotate();

            return ValveResponse.EXECUTED;
        }
    }

    private class DoQuitValve implements Valve
    {
        /**
         * Execute the message of shift Left using valve
         * @param message  the message
         * @return ValveResponse  the valve response
         */
        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.QuitMessage.class)
            {
                return ValveResponse.MISS;
            }
            timer.cancel();
            gameOverCleanUp();
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * The mainLoop that calls the valve responses
     */
    public void mainLoop()
    {
        response = ValveResponse.EXECUTED;
        Message message = null;
        while(response != ValveResponse.FINISH) {
            Thread.yield();
            if (!Tetris.queue.isEmpty() && !stopPoll) {
                message = Tetris.queue.poll();

                for (Valve valve : valves) {
                    response = valve.execute(message);
                    if (response != ValveResponse.MISS) {
                        break;
                    }
                }
            }
        }
    }
}
