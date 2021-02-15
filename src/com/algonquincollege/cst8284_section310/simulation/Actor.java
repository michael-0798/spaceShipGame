package com.algonquincollege.cst8284_section310.simulation;
/*
 * Student name: 	Michael Wang
 * Student number:  040990568
 * Section:			310
 */
/**
 * object of class Actor is the basic element of the Space game. Basically, the 
 * "space" is an array of Actor's objects, and the game game is to change each 
 * element (icon) in the array (ie. change 'A' to null) to make the spaceship and 
 * the asteroid looks like "flying". So the attribute as char icon is essential.
 * Also, it's necessary to identify whether the icon has been moved in the specific
 * round (turn), so the attribute as Boolean isMoved is a must, too.
 * 
 * @author mikew
 * @since  Feb 11, 2021
 * @version 1.0
 *
 */
public class Actor {
	
	/**
	 * in this case,icon will be 'S' representing 'spaceship', or 'A' as 'asteroid' 
	 * access modifier is private, so that it can not be modified outside this class
	 */
	private char icon;
	
	/**
	 * if isMoved is false, the specific Actor object is available to move this round;
	 * after being moved, the value should be switch to true;
	 */
	private boolean isMoved;
	
	/**
	 * default constructor, call the constructor Actor(char icon), and set the defualt
	 * value of icon as 'A'
	 */
	public Actor() {
		this('A');
	}
	
	/**
	 * one-parametered constructor, call constructor Actor(char icon, boolean isMoved)
	 * set the default value of isMoved as false;
	 * @param icon: 'A' for asteroid, 'S' for spaceship
	 */
	public Actor(char icon) {
		this(icon,false);
	}
	
	/**
	 * two-parameterd constructor
	 * @param icon		'A' for asteroid, 'S' for spaceship
	 * @param isMoved	true means that it has moved, false means it can be moved
	 */
	public Actor(char icon, boolean isMoved) {
		this.icon = icon;
		this.isMoved = isMoved;
	}

	/**
	 * to get the value of icon, it will be used in the SpaceExplorationSimulator
	 * class to identify whether the current element in the array is a spaceship
	 * or an asteroid
	 * @return 	the value of icon, usually be 'A'(asteroid) or 'S'(spaceship)
	 *  in this case
	 */
	public char getIcon() {
		return icon;
	}

	/**
	 * to set the value of icon
	 * @param icon  usually input 'A'(asteroid) or 'S'(spaceship) in this case
	 */
	public void setIcon(char icon) {
		this.icon = icon;
	}

	/**
	 * to identify if the element has been moved
	 * @return if the element has been moved, return true; if not, return false,
	 * means that the element is available to move this round
	 */
	public boolean isMoved() {
		return isMoved;
	}

	/**
	 * to set the boolean value, so as to make the element availabe to move or not 
	 * @param isMoved   input true, if the element has been moved in this round;
	 * input false, makes it available to move
	 */
	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}
	
	
}
