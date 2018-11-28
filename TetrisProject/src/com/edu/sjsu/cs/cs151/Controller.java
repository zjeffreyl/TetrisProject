package com.edu.sjsu.cs.cs151;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private View view;
    private Model model;
    private HoldBlockView nextBlockView;
    private Valve[] valves;
    Model.NextTetrominoGenerator nextTetrominoGenerator;
    private MainGameView mainGameView;

    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        nextTetrominoGenerator = model.new NextTetrominoGenerator();
        nextBlockView = view.mainGameView.getNextBlock();
        nextBlockView.inputTetromino(nextTetrominoGenerator.generateRandom());
        valves = new Valve[]{new DoNewGameValve(), new DoHardDropValve(), new DoSoftDropValve(), new DoLeftValve(),
                new DoRightValve(), new DoRotateValve()};
        mainGameView = view.getMainGameView();
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
        public ValveResponse execute(Message message)
        {
            return null;
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

//            Model.Tetromino tester = nextTetrominoGenerator.generateRandom();
//            tester.rotate();
            System.out.println("Rotate valve accessed successfully");

            return ValveResponse.EXECUTED;
        }
    }

    public void animateTetromino()
    {
        //new coordinate set
        //grid referenced here
    }

    public void mainLoop() throws Exception
    {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while(response != ValveResponse.FINISH) {

            message = Tetris.queue.poll();

            for (Valve valve : valves) {
                response = valve.execute(message);
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
        //give a new model tetromino to nextBlockView Object

//        // get reference to block
//        Model.Tetromino tester = nextTetrominoGenerator.generateRandom();
//        tester.moveTetromino(4, 0);
//        tester.rotate();
//
//
//        //spawn at grid (4,20) is center
//        mainGameView.getGameGrid().spawnTetromino(tester);
//
//        //create a timer
//
//        ActionListener listener = event ->
//        {
//            System.out.println("Jeffrey smells");
////            mainGameView.getGameGrid().clearTetromino(tester);
////            tester.rotate();
////            mainGameView.getGameGrid().spawnTetromino(tester);
//            tester.moveTetromino(0, 1);
//        };
//
//        final int DELAY = 1000;
//        Timer t = new Timer(DELAY, listener);
//        t.start();

        //Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("Jeffrey smells");
////                mainGameView.getGameGrid().clearTetromino(tester);
////                tester.rotate();
////                System.out.println(tester.getCoordinates()[0].getX());
////                mainGameView.getGameGrid().spawnTetromino(tester);
//            }
//        },0,1000);
    }
}
