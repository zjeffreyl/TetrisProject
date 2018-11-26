package com.edu.sjsu.cs.cs151;

public class Tetris
{
    //private static BlockingQueue<>
    private static View view;
    private static Model model;
    public static void main(String[] args)
    {
        view = new View();
        model = new Model();
        Controller game = new Controller(view, model);
        game.mainLoop();
    }
}
