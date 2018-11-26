package com.edu.sjsu.cs.cs151;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    MainGameView mainGameView;
    HoldBlockView nextBlockView;

    public KeyListener(MainGameView mainGameView)
    {
        this.mainGameView = mainGameView;
        nextBlockView = mainGameView.getNextBlock();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE)
        {
            System.out.println("Space Released");
            //Released means can change once
            //nextBlockView.inputTetromino();
        }
    }
}
