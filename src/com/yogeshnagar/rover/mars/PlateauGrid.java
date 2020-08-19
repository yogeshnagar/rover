package com.yogeshnagar.rover.mars;

import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.common.exceptions.RoversCrashedException;

/**
 * 
 * @author Yogesh Nagar
 * Class represents the Plateau's Grid
 */
public class PlateauGrid {
	
	private GridCell[][] locationGrid;
	
	/**
	 * Constructor
	 * @param length
	 * @param width
	 */
	public PlateauGrid(int length, int width) {
		locationGrid = new GridCell[length][width];
		for (int outer=0; outer < length; outer ++) {
			for (int inner=0; inner < width; inner ++) {
				locationGrid[outer][inner] = new GridCell(outer, inner);
			}
		}
	}
	
	/**
	 * Updates the Rover's location on Plateau
	 * @param id Rover's Id
	 * @param xCoordinate Rover's X Coordinate
	 * @param yCoordinate ROver's Y Coordinate
	 * @throws RoversCrashedException In case two Rovers placed at one location
	 * @throws BadRoverLocationException In case Rover is moved to non existing location
	 */
	public void setRoverIdAt(String id, int xCoordinate, int yCoordinate) throws RoversCrashedException, BadRoverLocationException {
		if (xCoordinate < 0 || xCoordinate > locationGrid.length || 
				yCoordinate < 0 || yCoordinate > locationGrid[xCoordinate].length) {
			throw new BadRoverLocationException("Bad rover location : ");
		}
		GridCell cell = locationGrid[xCoordinate][yCoordinate];
		cell.setRoverId(id);
	}

}
