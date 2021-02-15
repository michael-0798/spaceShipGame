package com.algonquincollege.cst8284_section310.simulation;
/*
 * Student name: 	Michael Wang
 * Student number:  040990568
 * Section:			310
 */
import java.util.Scanner;

/**
 * class for the Space game, including game rules, menu function, methods of the class
 * @author mikew
 * @since  Feb 11, 2021
 * @version 1.0
 *
 */
public class SpaceExplorationSimulator {
	/**
	 * set the constant value of rows of the play area, it can not be changed outside
	 */
	private static final int MAX_ROWS = 5;
	
	/**
	 * set the constant value of rows of the play area, it can not be changed outside
	 */
	private static final int MAX_COLUMNS = 20;
	
	/**
	 * set the max value of the ship, it's a final constant, too
	 */
	private final int MAX_SHIPS = 5;
	
	/**
	 * create the counter of destroyed ships, starts from 0, and accumulated in the prcess
	 */
	private int shipsDestroyed = 0;
	
	/**
	 * create the counter of escaped ships, starts from 0, and accumulated in the prcess
	 */
	private int shipsEscaped = 0;
	
	/**
	 * create the counter of turn, the variable will be used in the loop of process
	 */
	private int turnCount = 0;
	
	/**
	 * create the attribute to get the value from the scanner (user input) every
	 * round in the process
	 */
	private Scanner input = new Scanner(System.in);
	
	/**
	 * create a 2D array, which is the play area, row and colunm are the coordinate 
	 * for the spaceship or asteroid in the game,
	 * the accessible field is private, means that it can not be changed outside 
	 */
	private static Actor[][] actors = new Actor[MAX_ROWS][MAX_COLUMNS];
	
	/**
	 *  declare constant varibles for the moveActors() method
	 *  when get 0 from Math.random() in the moveActors(), it means the ship goes up;
	 *  1 for going right;
	 *  2 for going down
	 */
	private final int UP = 0,  RIGHT = 1, DOWN = 2;
	
	/**
	 * create a boolean attribute to determine if the user wants to draw the bars
	 * in the game;
	 * default value is false, when user wants bars, the boolean value will switch
	 * to true
	 */
	private boolean barFlag= false;
	
	/**
	 * non-parameter constructor to create object for SpaceExplorationSimulator class
	 * call constructor: SpaceExplorationSimulator(int shipsDestroyed, 
	 * int shipsEscaped, int turnCount,Actor[][] actors, boolean barFlag)
	 * set the default values as: shipsDestroyed =0, shipsEscaped=0, turnCount=0,
	 * actors= new Actor[MAX_ROWS][MAX_COLUMNS], barFlag=false;
	 */
	public SpaceExplorationSimulator() {
		this(0,0,0,actors,false);
	}
	
	/**
	 * constructor to create new object for SpaceExplorationSimulator
	 * chained to non-parameterd constructor
	 * @param shipsDestroyed: destroyed ships counter, once ship crashes, counter++
	 * @param shipsEscaped: escaped ships counter, once ship escapes, counter++
	 * @param turnCount: turn counter, when loop over 2D array, counter++
	 * @param actors: 2D array, icon including "S" for ship, "A" for asteroid, and ""
	 * @param barFlag: flag to determine if the user wants the "|" shows up
	 */
	public SpaceExplorationSimulator(int shipsDestroyed, int shipsEscaped, int turnCount,
			Actor[][] actors, boolean barFlag) {
		super();
		this.shipsDestroyed = shipsDestroyed;
		this.shipsEscaped = shipsEscaped;
		this.turnCount = turnCount;
		this.input = input;
		this.actors = actors;
		this.barFlag = barFlag;
	}

	/** run the simulation asking the user to continue for each turn,
	 or until the end of the simulation is detected.
	 add a new asteroid using the addAsteroid() method every 10 turns.
	 Senior Programmer: This method was verified, do not change it or
	                    we will fire you.
	*/
	public void runSimulation() {
		turnCount = 0;
		boolean continueSimulation = true;
		
		System.out.println("Welcome to Space Simulation");
		System.out.println("Use enter key to run next turn");
		System.out.println("Typing anything other than return will end program");
		initializeArray();
		drawSpaceSimulation();
		
		do {
			addAsteroid();
			prepareActorsForMovement();
			moveActors();
			drawSpaceSimulation();
			turnCount++;
			System.out.println("Use enter key to run next turn");
			System.out.println("Typing anything other than return will end program");
			String userInput = input.nextLine();
			if(userInput.length() > 0) {
				continueSimulation = false;
			}
		}while(continueSimulation && ! isEndOfSimulation());
		// once the simulation ends the program shuts down.
		System.out.println("Thanks for using the simulation");
	}

	/**
	 * initialize the start layout of the game, 5 ships at the very left in each
	 * row, and call addAsteroid() to add asteroid
	 * accessiable field is private, so that it can't be changed outside the class
	 */
	private void initializeArray() {
		for(int row = 0; row < actors.length; row++) {
			actors[row][0] = new Actor('S');
		}
		addAsteroid();
	}

	/**
	 * when the method being called, there are 10% chance to add an asteroid into 
	 * the play ground
	 * accessiable field is private, so that it can't be changed outside the class
	 * 
	 * 1, use Math.random()*(101-1+1)+1 to get a random value from 1-101, compare 
	 * with the constant value CHANCE (which is 10), only when the random value 
	 * is less than 10, there will be one more new asteroid added into the area
	 * 
	 * 2, use Math.random()*(4-0+1)+0, to get a random value from 0-4, which decide
	 * the row coordinate for the new added asteroid, and put it into the far right
	 * column in the area;
	 * 
	 * 3, if the Actor[new row][new column] where the new asteroid will be added 
	 * is null, add the asteroid;
	 * 		if there's a spaceship, then remove ship and asteroid, and make the 
	 * destroyed ship counter++;
	 * 		if there's another asteroid, then remove both (set as null) 
	 * 
	 */
	private void addAsteroid() {
		final int CHANCE = 10;
		int result = (int)(Math.random() * (101-1+1) + 1);
		if(result < CHANCE) {
			int row = (int)(Math.random() * 5);
			int col = actors[row].length - 1;
			if(actors[row][col] == null) {
				actors[row][col] = new Actor('A');
			}
			else if(actors[row][col].getIcon() == 'S') {
				actors[row][col] = new Actor('A');
				shipsDestroyed++;
			}
			else {
				actors[row][col] = null;
			}
		}
	}
	
	/**
	 * loop over the 2-D array and move each actor, depending on the icon, 
	 * S for ship, A for asteroid.
	 * accessiable field is private, so that it can't be changed outside the class
	 * 
	 * situation1: if the Actor is a ship, and isMoved()value is false, then 
	 * 		switch isMoved() value to true, means it has been moved this turn
	 * 		use Math.random()*(2-0+1)+0 to get a random value from 0-2, and move
	 * 			if random value is 0, go up. call move(), set newRow as (row-1);
	 * 			1, go right, call move(), set newColumn value as (column+1);
	 * 			2, go down, call move(), set newRow value as (row+1);
	 * 		 
	 * situation2: if it's an asteroid, then move left, 
	 * 			call move(), set newCoumnvalue as (column-1)
	 * 
	 * the rules when a spaceship/asteroid meet another S/A is included in move()
	 */
	private void moveActors() {
		for(int row = 0; row < actors.length; row++) {
			for(int col = 0; col < actors[row].length; col++) {
				if(actors[row][col] != null && ! actors[row][col].isMoved() ) {
					actors[row][col].setMoved(true);
					if(actors[row][col].getIcon() == 'S') {
						int direction = (int)(Math.random() * 3);
						switch(direction) {
						case UP:
							move(row,col,row-1,col,'S');
							break;
						case RIGHT: 
							move(row, col, row, col+1,'S');
							break;
						case DOWN:
							move(row,col,row+1,col,'S');
							break;
						}
					}
					else { 
						move(row,col,row,col-1,'A');
					}
				}
			}
		}
	}
	
	/**
	 * move() function for both ship and asteroid, 
	 * accessiable field is private, so that it can't be changed outside the class
	 * 
	 * @param row 		current row index of the ship
	 * @param col		current column index of the ship
	 * @param newRow	the new row index where the ship will go to
	 * @param newCol	the new column index where the ship is going to 
	 * @param thing		the current cell's icon , identify it's ship or asteroid
	 * 
	 * rule description: 
		- Ship to Asteroid: Ship is destroyed, destroyedShip counter++;
		- Ship to Ship: The ship skips it's turn and does not move.
		- Asteroid to Ship: Ship is removed, asteroid moves.
		- Asteroid to Asteroid: Both asteroids are removed.
		- Ships are not allowed to leave the top or bottom of the play area
		- Ships to move past the very-right column are escaped, escapedShip counter++;
	 * 
	 * logic description:
	 * 		situation 1: when the "thing"(ship/asteroid) is in the board
	 * 			 1.1 : there are no obstacles in 4 directions, just go
	 * 			 1.2: there's some obstacle
	 * 				 1.21: the "thing" is a space ship
	 * 					 1.211: meets the asteroid, remove ship
	 * 					 1.212: meets another ship,or at top or bottom, no move
	 * 				 1.22: the "thing" is an asteroid
	 * 					 1.221: asteroid meets a space ship, remove ship
	 * 					 1.222: meets another asteroid, remove both
	 * 		situation 2: When ship/asteroid is going out of the board
	 * 			 2.1: it's a ship, and is going rightward, escape successfully
	 * 			 2.2: it's an asteroid, just both vanish
	 */	
	private void move(int row, int col, int newRow, int newCol, char thing) {
		// situation 1: when the "thing"(ship/asteroid) is in the board
		if(! isMoveOffBoard(newRow, newCol)) {
			// situation 1.1 : there are no obstacles in 4 directions, just go
			if(actors[newRow][newCol] == null) {
				actors[newRow][newCol] = actors[row][col];
				actors[row][col] = null;
			}else {  // situation 1.2: there's some obstacle
				if('S' == thing) {  // situation 1.21: the "thing" is a space ship
					// situation 1.211: meets the asteroid, remove ship
					if(actors[newRow][newCol].getIcon() == 'A') {
						actors[row][col] = null;
						shipsDestroyed++;
					}else {// situation 1.212: meets another ship,or at top or bottom, no move
					}
				}else {  // situation 1.22: the "thing" is an asteroid
					// situation 1.221: asteroid meets a space ship, remove ship
					if(actors[row][newCol].getIcon() == 'S') {
						actors[row][newCol] = actors[row][col];
						actors[row][col] = null;
						shipsDestroyed++;
					}else { // situation 1.222: meets another asteroid, remove both
						actors[row][newCol] = null;
						actors[row][col] = null;
					}
				}
			}
		} else { // situation 2: When ship/asteroid is going out of the board
			// situation 2.1: it's a ship, and is going rightward, escape successfully
			if(('S'== thing) && (newCol >= MAX_COLUMNS)) {
				actors[row][col] = null; 
				shipsEscaped++;
			}else if('A'==thing){  //situation 2.2: it's an asteroid, just vanish
				actors[row][col] = null;
			}
		}
	}

	
	/** draw the grid for the simulation using nested loops.
	 * default bar will be ""; if user wants to show the bar (the barFlag value
	 * is true), then bar will switch to "|"
	 * author name is on screen for each loop.
	 * accessiable field is private, so that it can't be changed outside the class
	 */
	private void drawSpaceSimulation() {
		String rowText = "";
		
		String bar = " "; 
		
		if(this.barFlag) {
			bar = "|";
		}
		
		for(Actor[] row: actors) {
			rowText += bar;
			for(Actor actor: row) {
				if(actor == null) {
					rowText += " ";
				}
				else {
					rowText += actor.getIcon();
				}
				rowText += bar;
			}
			rowText += "\n";
		}
		System.out.print(rowText);
		System.out.println("Ships destroyed: " + shipsDestroyed);
		System.out.println("Ships escaped: " + shipsEscaped);
		System.out.println("Turn number: " + turnCount);
		System.out.println("Simulation by: Michael Wang" );
		System.out.println();
	}

	
	/**
	 * if shipsDestroyed + shipsEscapged >= Max_ships, game over
	 * accessiable field is private, so that it can't be changed outside the class
	 * @return true, means the game is end; false means game continues
	 * 
	 */
	private boolean isEndOfSimulation() {
		boolean result = false;
		if(shipsDestroyed + shipsEscaped >= MAX_SHIPS) {
			result = true;
		}
		return result;
	}

	/**
	 * to check if the ship/asteroid would be outside the 2D array,
	 * accessiable field is private, so that it can't be changed outside the class
	 * @param newRow, the new row index for ship/asteroid after moving
	 * @param newCol, the new column index for ship/asteroid after moving
	 * @return true, means would go out of the 2D array; false, means would be 
	 * stay in the array
	 */
	private boolean isMoveOffBoard(int newRow, int newCol) {
		boolean isOffBoard = true;
		if((newRow >= 0 && newRow < MAX_ROWS)&&(newCol >= 0 && newCol < MAX_COLUMNS)) {
			isOffBoard = false;
		}
		return isOffBoard;
	}

	/**
	 *  This methods loops over the 2D array and re-activates all of the actors
		so that when the moveActors() methods loops over the array each actor
		will be moved at least once.
	 * accessiable field is private, so that it can't be changed outside the class

	 */
	private void prepareActorsForMovement() {
		for(int row = 0; row < actors.length; row++) {
			for(int col = 0; col < actors[row].length; col++) {
				if(actors[row][col] != null) {
					actors[row][col].setMoved(false);
				}
			}
		}
	}
	
	/**
	 * set the value of barFlag
	 * @param bool, if true, means the user wants to show the bar for play area
	 */
	public void setBarFlag(boolean bool) {
		this.barFlag=bool;
	}

}
