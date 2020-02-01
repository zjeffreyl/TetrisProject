# Tetris

This is an implementation of Tetris using Java and Java Swing framework for t he GUI.

## Installation

Make sure java version is at least 1.8

Navigate to downloaded directory and <saved-location>/TetrisProject/TetrisProject/src
```bash
javac ./com/edu/sjsu/cs/cs151/Tetris.java
java com.edu.sjsu.cs.cs151.Tetris
```

## Result

![Tetris](https://github.com/zjeffreyl/TetrisProject/blob/master/images/Tetris.2020-02-01%2000_10_09.gif)


## Design and Development

The first goal was to come up with the game’s use cases:

1. Start Screen with start playing
2. The grid will appear and the next block box 
3. Player presses Start
4. The blocks drop starts
5. Block stack bottom
6. Round is over
7. Game is over 

* Variation #1
  * In step 4, the player decides to shift the block  
  * Player can shift block side to side across x axis
  * Continue with step 5

* Variation #2 
  * In step 4, the player decides to rotate the box	 
  * Player can rotate block 90 degrees
  * Player can rotate block down to draw down y axis
  * Continue with step 5

* Variation #3
	* In step 4, the player decides to fast drop
  * Player clicks to drop immediately down the y axis
  * Continue with step 5

* Variation #4
	* In step 5, the stack will reach the top
	* Terminate game when the stack goes above max y grid
	* Continue to step 6

* Variation #5
	* In step 5, the stack will be full 
  * Will clear the lines that are filled completely with blocks (only x axis lines)
  * Continue with step 6

* Variation #6
	* In step 6, the round is over, new round starts
  * No lines are completed filled 
  * Next block spawns
  * Continue with step 4

* Variation #7
	* In step 6, the next round begins and the grid is at the highest y point
	* Game is over
	* Go back to step 1

This provided information on constructing a basic UML diagram

![Tetris-UML-diagram](https://github.com/zjeffreyl/TetrisProject/blob/master/images/Tetris%20UML%20diagram.png)

This helped construct a MVC based sequence diagram

![Tetris-sequence-diagram](https://github.com/zjeffreyl/TetrisProject/blob/master/images/Sequence%20Diagram%202.png)
