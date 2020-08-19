package com.yogeshnagar.rover.mc;

import java.util.Observable;

import com.yogeshnagar.rover.controller.FlightManager;
import com.yogeshnagar.rover.common.Compas;
import com.yogeshnagar.rover.common.IRover;
import com.yogeshnagar.rover.common.Location;
import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;

public class Rover extends Observable implements IRover {
	
	private FlightManager flightManager = FlightManager.getInstance();
	
	private Location location;
	
	private String roverId;

	/**
	 * Constructor
	 */
	public Rover() {
		this.addObserver(flightManager.getPlateauInstance());
	}

	/**
	 * Return Location Object
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Returns the Rover's Id
	 */
	public String getRoverId() {
		return roverId;
	}

	/**
	 * Sets Rover's Location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Sets Rover's Id
	 */
	public void setRoverId(String roverId) {
		this.roverId = roverId;
	}
	
	/**
	 * toString()
	 */
	public String toString() {
		return getRoverId() + " " + getLocation();
	}

	/**
	 * Moves the Rover to new Location
	 */
	public void move(String instructions) throws BadRoverLocationException {
		Location oldLocation = null;
		try {
			oldLocation = getLocation().clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Clone not supported.");
		}
		Compas.updateLocation(getLocation(), instructions);
		setChanged();
		notifyObservers(oldLocation);
	}
	
}
