package com.edu.sjsu.cs.cs151;


import com.edu.sjsu.cs.cs151.Controller.Controller;
import com.edu.sjsu.cs.cs151.Models.Model;
import com.edu.sjsu.cs.cs151.Views.Message;
import com.edu.sjsu.cs.cs151.Views.View;

import java.util.LinkedList;
import java.util.Queue;

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
