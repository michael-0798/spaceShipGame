package com.algonquincollege.cst8284_section310.simulation;
/*
 * Student name: 	Michael Wang
 * Student number:  040990568
 * Section:			310
 */
import java.util.Scanner;
/**
 * test class for the Space game
 * @author mikew
 * @since  Feb 11, 2021
 * @version 1.0
 */
public class SpaceProgram {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO instantiate the SpaceExplorationSimulator
		SpaceExplorationSimulator simulator = new SpaceExplorationSimulator();
		
		// ask user if they wish to show the bar
		System.out.println("do you want to show the bars in the play area"
		+"\n" + "press y' to show the bars, press other any key to ommit the bars");
		Scanner keyboard = new Scanner(System.in);
		String choice = keyboard.next();
		
		// set the value to the boolean barFlag in the object
		if("y".equalsIgnoreCase(choice)) {
			simulator.setBarFlag(true);
		}
		
		// TODO call the runSimulation() method
		simulator.runSimulation();
	}

	
}
