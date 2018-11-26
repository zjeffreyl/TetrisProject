package com.edu.sjsu.cs.cs151;

import java.awt.*;
import java.util.Random;

/**
 * Model of the game stores all information about the current state of the game
 *
 */
public class Model
{

	public class Grid
	{
		
	}

	public class Coordinate
    {
        private int x;
        private int y;

        public Coordinate(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

	public static class Tetromino
	{
	    Coordinate a;
	    Coordinate b;
	    Coordinate c;
	    Coordinate d;
	    Color color;
		/**
		 * Constructs Block object with specified coordinates
		 */
		public Tetromino(Coordinate a, Coordinate b, Coordinate c, Coordinate d, Color color)
		{
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.color = color;
		}
		
		/**
		 * Rotates specified block 90 degrees clockwise
		 * @param inputBlock block to be rotated
		 */
		public void rotate(Tetromino inputBlock)
		{
			
		}

		public Coordinate[] getCoordinates()
        {
            Coordinate[] coords = {a, b, c ,d};
            return coords;
        }
	}

	public class NextTetrominoGenerator
    {
        private Tetromino o = new Tetromino(new Coordinate(0,0), new Coordinate(1,0), new Coordinate(0,1), new Coordinate(1,1), Color.yellow);
        private Tetromino i = new Tetromino(new Coordinate(0,0), new Coordinate(1,0), new Coordinate(2,0), new Coordinate(3,0), Color.cyan);
        private Tetromino j = new Tetromino(new Coordinate(0,0), new Coordinate(1,0), new Coordinate(1,1), new Coordinate(2,1), Color.blue);
        private Tetromino l = new Tetromino(new Coordinate(0,1), new Coordinate(1,1), new Coordinate(2,1), new Coordinate(2,0), Color.orange);
        private Tetromino s = new Tetromino(new Coordinate(0,1), new Coordinate(1,1), new Coordinate(1,0), new Coordinate(2,0), Color.green);
        private Tetromino z = new Tetromino(new Coordinate(0,0), new Coordinate(1,0), new Coordinate(1,1), new Coordinate(2,1), Color.red);
        private Tetromino t = new Tetromino(new Coordinate(0,1), new Coordinate(1,0), new Coordinate(1,1), new Coordinate(2,1), Color.magenta);

        public Tetromino[] nextTetromino = {o,i,j,l,s,z,t};

        public Tetromino generateRandom()
        {
            Random Dice = new Random();
            int n = Dice.nextInt(7);
            return nextTetromino[n];
        }
    }

	/**
	 * Describes a window that can hold the next Tetris object until user releases
	 *
	 */
	public class HoldBlock
	{
		
	}
	
	/**
	 * Describes a window that displays and tracks the score
	 *
	 */
	public class Score
	{
		
	}
	
	/**
	 * Returns true if round has started, false otherwise
	 * @return true or false
	 */
	public boolean roundStarted()
	{
		return false;
	}
	
	/**
	 * Returns true if round is over, false otherwise
	 * @return true or false
	 */
	public boolean roundOver()
	{
		return false;
	}
	
	/**
	 * Returns true if game has started, false otherwise
	 * @return true or false
	 */
	public boolean gameStarted()
	{
		return false;
	}
	
	/**
	 * Returns true if game is over, false otherwise
	 * @return true or false
	 */
	public boolean gameOver()
	{
		return false;
	}
}
