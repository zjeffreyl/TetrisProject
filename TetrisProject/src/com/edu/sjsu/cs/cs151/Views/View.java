package com.edu.sjsu.cs.cs151.Views;

import com.edu.sjsu.cs.cs151.Tetris;

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
                Tetris.queue.add(new Message.FastDropMessage());
                //nextBlockView.inputTetromino();
            }
            else if(key == KeyEvent.VK_LEFT)
            {
                Tetris.queue.add(new Message.LeftMessage());
                //send message to move left
            }
            else if(key == KeyEvent.VK_RIGHT)
            {
                Tetris.queue.add(new Message.RightMessage());
                //send message to move right
            }
            else if(key == KeyEvent.VK_UP)
            {
                Tetris.queue.add((Message) new Message.RotateMessage());
                int x;
                //Send message to rotate
            }
            else if(key == KeyEvent.VK_DOWN)
            {
                Tetris.queue.add((Message) new Message.SlowDropMessage());
                //Send message to fast drop
            }
        }
    }

}
