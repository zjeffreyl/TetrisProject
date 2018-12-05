package com.edu.sjsu.cs.cs151.Views;

import com.edu.sjsu.cs.cs151.Tetris;

import java.awt.event.KeyEvent;

/**
 * View is the class through which all user interface/action occurs. Sends input as messages to the queue for
 * controller to process
 */
public class View {

    private MainGameView mainGameView;

    /**
     * View constructor initializes game view and adds a key listener to the frame
     */
    public View()
    {
        mainGameView = new MainGameView(700);
        KeyListener keyListener = new KeyListener();
        mainGameView.addKeyListener(keyListener);
    }

    /**
     * Returns main game view
     * @return mainGameView
     */
    public MainGameView getMainGameView() {
        return mainGameView;
    }

    /**
     * Listens for user key presses
     */
    private class KeyListener implements java.awt.event.KeyListener {

        HoldBlockView nextBlockView;

        /**
         * Adds next block to the next block window
         */
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

        /**
         * Checks when user releases key, therefore only checks for single press and not hold of key
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_SPACE)
            {
                Tetris.queue.add(new Message.FastDropMessage());
                //send message to fast drop
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
            }
            else if(key == KeyEvent.VK_DOWN)
            {
                Tetris.queue.add((Message) new Message.SlowDropMessage());
                //Send message to fast drop
            }
            else if(key == KeyEvent.VK_N)
            {
                Tetris.queue.add((Message) new Message.NewGameMessage());
            }
            else if(key == KeyEvent.VK_Q)
            {
                Tetris.queue.add((Message) new Message.QuitMessage());
            }
        }
    }

}
