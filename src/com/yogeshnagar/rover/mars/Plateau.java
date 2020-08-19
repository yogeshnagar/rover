package com.yogeshnagar.rover.mars;

import java.util.Observable;
import java.util.Observer;

import com.yogeshnagar.rover.common.IRover;
import com.yogeshnagar.rover.common.Location;
import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.common.exceptions.RoversCrashedException;

/**
 * 
 * @author Yogesh Nagar
 * This class represents the Plateau
 * This is the Observer for all the Rovers on Plateau Grid
 * Singleton Implementation
 */
public class Plateau implements Observer {
	
	private static volatile Plateau _plateau;
	
    private PlateauGrid grid;
	
	private int gridLength;
	
	private int gridWidth;
	
	/**
	 * Constructor
	 * @param length Grid's length on Plateau
	 * @param width Grid's width on Plateau
	 */
	private Plateau(int length, int width) {
		gridLength = length;
		gridWidth = width;
		grid = new PlateauGrid(gridLength, gridWidth);
	}
	
	/**
	 * Method to access the Plateau's instance
	 * @param length
	 * @param width
	 * @return
	 */
	public static Plateau getPlateauInstance(int length, int width) {
		if (_plateau == null) {
			synchronized (Plateau.class) {
				if (_plateau == null) {
					_plateau = new Plateau(length, width);
				}
			}
		}
		return _plateau;
	}
	
	/**
	 * Returns the Grid's length
	 * @return length
	 */
	public int getGridLength() {
		return gridLength;
	}
	
	/**
	 * Returns the Grid's width
	 * @return width
	 */
	public int getGridWidth() {
		return gridWidth;
	}

	/**
	 * Method is called in case notification sent by the Rover's
	 * Performs action on Rover's movement
	 */
	public void update(Observable observable, Object arg) {
		if (observable instanceof IRover) {
			IRover rover = (IRover) observable;
			Location oldLocation = (Location) arg;
			try {
				grid.setRoverIdAt(null, oldLocation.getXCoordinate()- 1, oldLocation.getYCoordinate() - 1);
				grid.setRoverIdAt(rover.getRoverId(), rover.getLocation().getXCoordinate() - 1, rover.getLocation().getYCoordinate() - 1);
			} catch (RoversCrashedException rce) {
				rce.printStackTrace();
			} catch (BadRoverLocationException e) {
				e.printStackTrace();
			}
		} 
	}

}
