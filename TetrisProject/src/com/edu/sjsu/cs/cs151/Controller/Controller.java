package com.edu.sjsu.cs.cs151.Controller;

import com.edu.sjsu.cs.cs151.Models.Model;
import com.edu.sjsu.cs.cs151.Valve;
import com.edu.sjsu.cs.cs151.ValveResponse;
import com.edu.sjsu.cs.cs151.Views.GridView;
import com.edu.sjsu.cs.cs151.Views.HoldBlockView;
import com.edu.sjsu.cs.cs151.Views.MainGameView;
import com.edu.sjsu.cs.cs151.Views.View;
import sun.plugin2.message.Message;

import java.util.Timer;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private View view;
    private Model model;
    private HoldBlockView nextBlockView;
    private BlockingQueue<Message> messageQueue;
    private List<Valve> valves = new LinkedList<Valve>();
    Model.NextTetrominoGenerator nextTetrominoGenerator;
    private MainGameView mainGameView;
    private GridView gameGrid;

    private Model.Tetromino currentTetromino;

    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        nextTetrominoGenerator = model.new NextTetrominoGenerator();
        //nextBlockView = view.mainGameView.getNextBlock();
        //nextBlockView.inputTetromino(nextTetrominoGenerator.generateRandom());
        mainGameView = view.getMainGameView();
        gameGrid = mainGameView.getGameGrid();

        //Needs to be in VALVE!
        currentTetromino = nextTetrominoGenerator.generateRandom();
    }

    //Paint or delete tetromino(based on its values)
    public void paintTetromino(boolean paint)
    {
        for(Model.Coordinate coord: currentTetromino.getCoordinates())
        {
            gameGrid.getSquares()[coord.getY()][coord.getX()].changeOccupied(paint, currentTetromino.getColor());
        }
    }

    public void doRotate(Model.Tetromino tetromino)
    {

    }

    private class DoNewGameValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoHardDropValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoSoftDropValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoLeftValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoRightValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoRotateValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message)
        {
//            if (message.getClass() != RotateMessage.class)
//            {
//                return ValveResponse.MISS;
//            }

            Model.Tetromino tester = nextTetrominoGenerator.generateRandom();
            tester.rotate();

            return ValveResponse.EXECUTED;
        }
    }

    public void mainLoop() throws Exception
    {
        //give a new model tetromino to nextBlockView Object

        //create a timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
 
            }
        },0,1000);
    }
}
