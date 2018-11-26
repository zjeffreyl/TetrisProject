package com.edu.sjsu.cs.cs151;


import sun.plugin2.message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tetris
{
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
    private static View view;
    private static Model model;
    public static void main(String[] args) throws Exception {
        view = new View();
        model = new Model();
        Controller game = new Controller(view, model);
        game.mainLoop();
    }
}
