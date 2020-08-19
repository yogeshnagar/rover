package com.yogeshnagar.rover.common;

import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;

public interface IRover {

	/**
	 * Returns the unique String associated with the Rover
	 * @return Rover's Identity
	 */
	String getRoverId();
	
	/**
	 * Sets the unique String associated with the Rover
	 * @param roverId
	 */
	void setRoverId(String roverId);
	
	/**
	 * Returns the Location object associated with the Rover
	 * @return Location
	 */
	Location getLocation();
	
	/**
	 * Sets the Location object associated with the Rover
	 * @param location
	 */
	void setLocation(Location location);
	
	/**
	 * Helps the Rover's movement based on user input
	 * @param instructions Instructions String
	 * @throws BadRoverLocationException Thrown in case rover is moved to a Bad Location
	 */
	void move(String instructions) throws BadRoverLocationException;
	
}
