package com.yogeshnagar.rover.common.exceptions;

/**
 * @author Yogesh Nagar
 * This exception is thrown in case two Rovers are moved
 * to a same Grid Location
 *
 */
public class RoversCrashedException extends RuntimeException {

	/**
	 * Constructor
	 * @param msg Rovers crashed message
	 */
	public RoversCrashedException(String msg) {
		super(msg);
	}
	
}
