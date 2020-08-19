package com.yogeshnagar.rover.common;

import java.util.concurrent.atomic.AtomicInteger;

import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.controller.FlightManager;
import com.yogeshnagar.rover.mars.Plateau;

public class Location implements Cloneable {
	
	private AtomicInteger xCoordinate;
	
	private AtomicInteger yCoordinate;
	
	private Direction direction;

	private Plateau plateau = FlightManager.getInstance().getPlateauInstance();
	
	public Location(int xCoordinate, int yCoordinate, Direction direction) {
		this.xCoordinate = new AtomicInteger(xCoordinate);
		this.yCoordinate = new AtomicInteger(yCoordinate);
		this.direction = direction;
	}
	
	/**
	 * @return the x_coordinate
	 */
	public int getXCoordinate() {
		return xCoordinate.get();
	}

	/**
	 * @param x_coordinate the x_coordinate to set
	 * @throws BadRoverLocationException 
	 */
	public void increaseXCoordinate() {
		int x = xCoordinate.incrementAndGet();
		if (x < 0 || x > plateau.getGridLength()) {
			xCoordinate.decrementAndGet();
			throw new BadRoverLocationException("Location not present on plateau : " + xCoordinate + " , " + yCoordinate);
		}
	}
	
	public void decreaseXCoordinate() {
		int x = xCoordinate.decrementAndGet();
		if (x < 0 || x > plateau.getGridLength()) {
			xCoordinate.incrementAndGet();
			throw new BadRoverLocationException("Location not present on plateau : " + xCoordinate + " , " + yCoordinate);
		}
	}

	/**
	 * @return the y_coordinate
	 */
	public int getYCoordinate() {
		return yCoordinate.get();
	}

	/**
	 * @param y_coordinate the y_coordinate to set
	 * @throws BadRoverLocationException 
	 */
	public void increaseYCoordinate() {
		int y = yCoordinate.incrementAndGet();
		if (y < 0 || y > plateau.getGridWidth()) {
			yCoordinate.decrementAndGet();
			throw new BadRoverLocationException("Location not present on plateau : " + xCoordinate + " , " + yCoordinate);
		}
	}
	
	public void decreaseYCoordinate() {
		int y = yCoordinate.decrementAndGet();
		if (y < 0 || y > plateau.getGridWidth()) {
			yCoordinate.incrementAndGet();
			throw new BadRoverLocationException("Location not present on plateau : " + xCoordinate + " , " + yCoordinate);
		}
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Clones the Location object
	 */
	public Location clone() throws CloneNotSupportedException {
		return new Location(getXCoordinate(), getYCoordinate(), direction);
	}
	
	/**
	 * Location's String
	 */
	public String toString() {
		return new StringBuilder().append(getXCoordinate()).append(IConstants.EMPTY_SPACE).
		append(getYCoordinate()).append(IConstants.EMPTY_SPACE).append(getDirection().toString()).toString();
	}

}
