package com.company;

/**
 * Model of the game stores all information about the current state of the game
 *
 */
public class Model
{
	/**
	 * Describes a grid representing the board the Tetris objects move in 
	 *
	 */
	public class Grid
	{
		
	}
	
	/**
	 * Describes a 4 piece block Tetris object
	 *
	 */
	public class Block
	{
		/**
		 * Constructs Block object with specified coordinates
		 */
		public Block() 
		{
			
		}
		
		/**
		 * Rotates specified block 90 degrees clockwise
		 * @param inputBlock block to be rotated
		 */
		public void rotate(Block inputBlock)
		{
			
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
