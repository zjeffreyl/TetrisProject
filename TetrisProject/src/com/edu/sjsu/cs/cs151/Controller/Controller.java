package com.edu.sjsu.cs.cs151.Controller;

import com.edu.sjsu.cs.cs151.Views.Message;
import com.edu.sjsu.cs.cs151.Models.Model;
import com.edu.sjsu.cs.cs151.Tetris;
import com.edu.sjsu.cs.cs151.Valve;
import com.edu.sjsu.cs.cs151.ValveResponse;
import com.edu.sjsu.cs.cs151.Views.GridView;
import com.edu.sjsu.cs.cs151.Views.HoldBlockView;
import com.edu.sjsu.cs.cs151.Views.MainGameView;
import com.edu.sjsu.cs.cs151.Views.View;

import java.awt.*;

public class Controller {
    private View view;
    private Model model;
    private HoldBlockView nextBlockView;
    private Valve[] valves;
    public Model.NextTetrominoGenerator nextTetrominoGenerator;
    private MainGameView mainGameView;
    private GridView gameGrid;

    private Model.Tetromino currentTetromino;

    public int roundsPassed = 0;

    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        nextTetrominoGenerator = model.new NextTetrominoGenerator();
        nextBlockView = view.getMainGameView().getNextBlock();

        valves = new Valve[]{new DoNewGameValve(), new DoHardDropValve(), new DoSoftDropValve(), new DoLeftValve(),
                new DoRightValve(), new DoRotateValve()};
        mainGameView = view.getMainGameView();
        gameGrid = mainGameView.getGameGrid();


    }

    public void spawnTetromino()
    {
        setCurrentTetromino(nextTetrominoGenerator.generateRandom());
        translateTetromino(4, 0);
        paintTetromino(true);
    }

    public void setCurrentTetromino(Model.Tetromino newTetromino)
    {
        currentTetromino = newTetromino;
    }

    //Paint or delete tetromino(based on its values)
    public void paintTetromino(boolean paint)
    {
        for(Model.Coordinate coord: currentTetromino.getCoordinates())
        {
            gameGrid.getSquares()[coord.getY()][coord.getX()].changeOccupied(paint, currentTetromino.getColor());
        }
    }

    public void doRotate()
    {
        if (currentTetromino.getColor() != Color.YELLOW)
        {
            paintTetromino(false);
            currentTetromino.rotate();
            paintTetromino(true);
        }
    }

    public synchronized boolean checkBound(int xMovement, int yMovement)
    {
        for(Model.Coordinate coordinate: currentTetromino.getCoordinates())
        {
            int predictedX = coordinate.getX() + xMovement;
            int predictedY = coordinate.getY() + yMovement;
            if(predictedX > 9 || predictedX < 0 || predictedY > 19 || hasCollision(predictedX, predictedY, coordinate))
            {
                System.out.println("Some collision");
                /*if(predictedY >= 19 || (hasCollision(predictedX, predictedY, coordinate) && predictedX == 0)){
                    System.out.println("Round: " + (roundsPassed ++));
                    newRound();
                }*/
                return false;
            }

        }
        return true;
    }


    public boolean hasCollision(int predictedX, int predictedY, Model.Coordinate currentCoord) {
        if (gameGrid.getSquares()[predictedY][predictedX].isOccupied()) {
            if(!currentTetromino.contains(predictedX, predictedY)) {
                return true;
            }
        }
        return false;
    }

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

    public void clearRow()
    {
        int clears = 0;
        int i = 19;
        //optimize by limit row parse
        while(i >= 0 && clears <= 4) {
            if (checkClearRow(i)) {
                clears++;
                gameGrid.setRowUnoccupied(i);
            }
            i--;
        }

    }

    public void shiftLines()
    {

    }

    public void fastDrop(){
        while(translateTetromino(0,1)) {

        }
    }

    public boolean translateTetromino(int addX, int addY)
    {
        if(checkBound(addX, addY))
        {
            paintTetromino(false);
            currentTetromino.moveTetromino(addX, addY);
            paintTetromino(true);
            return true;
        }
        return false;
    }

    public Model.Tetromino getCurrentTetromino()
    {
        return currentTetromino;
    }

    public synchronized void newRound()
    {
        roundsPassed++;
        spawnTetromino();
    }

    private class DoNewGameValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.NewGameMessage.class)
            {
                return ValveResponse.MISS;
            }
            roundsPassed = 0;
            spawnTetromino();
            return ValveResponse.EXECUTED;
        }

    }

    private class DoHardDropValve implements Valve
    {

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

    private class DoSoftDropValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.SlowDropMessage.class)
            {
                return ValveResponse.MISS;
            }
            translateTetromino(0, 1);
            return ValveResponse.EXECUTED;
        }
    }

    private class DoLeftValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.LeftMessage.class)
            {
                return ValveResponse.MISS;
            }
            translateTetromino(-1, 0);
            return ValveResponse.EXECUTED;
        }
    }

    private class DoRightValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message)
        {
            if (message.getClass() != Message.RightMessage.class)
            {
                return ValveResponse.MISS;
            }
            translateTetromino(1, 0);
            return ValveResponse.EXECUTED;
        }
    }

    private class DoRotateValve implements Valve
    {

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

    public void mainLoop() throws Exception
    {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while(response != ValveResponse.FINISH) {
            Thread.yield();
            if (!Tetris.queue.isEmpty()) {
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
