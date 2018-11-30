package com.edu.sjsu.cs.cs151.Models;

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

        public int getY() { return y; }

        public void setX(int x) { this.x = x; }

		public void setY(int y) { this.y = y; }

	}

	public class Tetromino
	{
	    Coordinate a;
	    Coordinate b;
	    Coordinate c;
	    Coordinate d;
	    Coordinate[] coords;
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
			coords = new Coordinate[]{this.a, this.b, this.c, this.d};
            this.color = color;
		}
		
		/**
		 * Rotates specified block 90 degrees clockwise
		 */
		public void rotate()
		{
			boolean inBounds = true;
			int[] xChange = new int[4];
			int[] yChange = new int[4];

			for (int m = 0; m < 4; m++)
			{
				int xResult = coords[m].x - coords[1].x;
				int yResult = coords[m].y - coords[1].y;

				int yNew = 0 * yResult + (-1) * xResult;
				int xNew = 1 * yResult + 0 * xResult;

				xChange[m] = xNew - xResult;
				yChange[m] = yNew - yResult;


				if (coords[m].x + xChange[m] >= 0 && coords[m].x + xChange[m] <= 9)
				{
					if (coords[m].y + yChange[m] >= 0 && coords[m].y + yChange[m] <= 19)
					{
						inBounds = true;
					} else
						inBounds = false;
				} else
					inBounds = false;

//				coords[m].setX(coords[m].getX() + xChange);
//				coords[m].setY(coords[m].getY() + yChange);

				//int xResult = inputBlock.getCoordinates()[x].getX() - inputBlock.getCoordinates()[1].getX();
			}

			if (inBounds)
			{
				for (int m = 0; m < 4; m++)
				{
					coords[m].x += xChange[m];
					coords[m].y += yChange[m];
				}
			}
		}

		public Coordinate[] getCoordinates()
        {
            return coords;
        }

        public void moveTetromino(int addX, int addY) {
			for(Coordinate coord: coords) {

				if (coord.x + addX >= 0 && coord.x + addX <= 9) {
					if (coord.y + addY >= 0 && coord.y + addY <= 19) {
						coord.x += addX;
						coord.y += addY;
					} else
						break;

				}
			}
		}

		public Color getColor() {
			return color;
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
