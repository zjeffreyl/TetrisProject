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

    public MainGameView getMainGameView() {
        return mainGameView;
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
            else if(key == KeyEvent.VK_LEFT)
            {
                System.out.println("Left Released");
                //send message to move left
            }
            else if(key == KeyEvent.VK_RIGHT)
            {
                System.out.println("Right Released");
                //send message to move right
            }
            else if(key == KeyEvent.VK_UP)
            {
                System.out.println("Up Released");
                //Send message to rotate
            }
            else if(key == KeyEvent.VK_DOWN)
            {
                System.out.println("Down Released");
                //Send message to fast drop
            }
        }
    }

}
