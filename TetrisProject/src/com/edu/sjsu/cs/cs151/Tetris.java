package com.edu.sjsu.cs.cs151;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tetris
{
    public static Queue<Message> queue = new LinkedList<Message>(){};
    private static View view;
    private static Model model;
    public static void main(String[] args) throws Exception {
        view = new View();
        model = new Model();
        Controller game = new Controller(view, model);
        game.mainLoop();
    }
}
