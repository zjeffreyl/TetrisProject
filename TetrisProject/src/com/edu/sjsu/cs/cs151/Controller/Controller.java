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

    public boolean checkBound(int xMovement, int yMovement)
    {
        for(Model.Coordinate coordinate: currentTetromino.getCoordinates())
        {
            int totalXMovement = coordinate.getX() + xMovement;
            int totalYMovement = coordinate.getY() + yMovement;
            if(totalXMovement > 9 || totalXMovement < 0 || totalYMovement > 19)
            {
                return false;
            }

        }
        return true;
    }

    public void translateTetromino(int addX, int addY)
    {
        if(checkBound(addX, addY))
        {
            paintTetromino(false);
            currentTetromino.moveTetromino(addX, addY);
            paintTetromino(true);
        }
    }

    public Model.Tetromino getCurrentTetromino()
    {
        return currentTetromino;
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
