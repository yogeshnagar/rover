package com.yogeshnagar.rover.mars;

import com.yogeshnagar.rover.common.exceptions.RoversCrashedException;

/**
 * 
 * @author Yogesh Nagar
 * A Cell in a Plateau's Grid
 */
public class GridCell {

	private String roverId;
	
	private int xCoordinate;
	
	private int yCoordinate;
	
	/**
	 * Constructor
	 * @param xCoordinate X Coordinate on plateau
	 * @param yCoordinate Y Coordinate on plateau
	 */
	public GridCell(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Returns the Rover's Id
	 * @return ID
	 */
	public String getRoverId() {
		return roverId;
	}

	/**
	 * Sets the Rover's id at the location 
	 * @param roverId Rover's Id
	 * @throws RoversCrashedException In case two Rover's placed at one location
	 */
	public void setRoverId(String roverId) throws RoversCrashedException {
		if (this.roverId != null) {
			if (!this.roverId.equals(roverId)) {
				throw new RoversCrashedException("Rovers on the same location: (" + xCoordinate + " , " + yCoordinate + ")" + this.roverId + " and " + roverId);
			}
		}
		this.roverId = roverId;
	}
	
}
