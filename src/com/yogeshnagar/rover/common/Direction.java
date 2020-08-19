package com.yogeshnagar.rover.common;

/**
 * 
 * @author Yogesh Nagar
 * Enumeration for the Direction of the rover
 */
public class Direction {
	
	private String key;
	
	/**
	 * Constructor
	 * @param id Identity of Direction
	 */
	public Direction(String key) {
		this.key = key;
	}
	
	/**
	 * 
	 */
	public String getDirectionKey() {
		return key;
	}
	
}
