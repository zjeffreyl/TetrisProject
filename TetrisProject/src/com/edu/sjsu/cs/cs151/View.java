package com.edu.sjsu.cs.cs151;

import java.awt.event.KeyEvent;

public class View {

    MainGameView mainGameView;
    public View()
    {
        mainGameView = new MainGameView(700);
        KeyListener keyListener = new KeyListener();
        mainGameView.addKeyListener(keyListener);
    }

    private class KeyListener implements java.awt.event.KeyListener {

        HoldBlockView nextBlockView;

        public KeyListener()
        {
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

}
