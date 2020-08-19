package com.yogeshnagar.rover.common.exceptions;

/**
 * @author Yogesh Nagar
 * This Exception is thrown in case, 
 * the rover is moved to the location
 * which is not present on the plateau.
 */
public class BadRoverLocationException extends RuntimeException {

	/**
	 * Constructor
	 * @param message Bad Rover Location Message
	 */
	public BadRoverLocationException(String message) {
		super(message);
	}
	
}

