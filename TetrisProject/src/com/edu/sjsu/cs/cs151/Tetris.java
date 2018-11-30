package com.edu.sjsu.cs.cs151;


import com.edu.sjsu.cs.cs151.Controller.Controller;
import com.edu.sjsu.cs.cs151.Models.Model;
import com.edu.sjsu.cs.cs151.Views.Message;
import com.edu.sjsu.cs.cs151.Views.View;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris
{
    public static Queue<Message> queue = new LinkedList<Message>(){};
    private static View view;
    private static Model model;
    private static Controller game;

    public static class DropTimer extends TimerTask {
        @Override
        public void run() {
            game.translateTetromino(0,1);
        }
    }

    public static void main(String[] args) throws Exception {
        view = new View();
        model = new Model();
        game = new Controller(view, model);
        game.spawnTetromino();
        Timer timer = new Timer();
        timer.schedule(new DropTimer(),1000, 1000);

        game.mainLoop();
    }
}
