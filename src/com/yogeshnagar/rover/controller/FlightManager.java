package com.yogeshnagar.rover.controller;

import java.util.ArrayList;
import java.util.List;

import com.yogeshnagar.rover.common.IRover;
import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.mars.Plateau;
import com.yogeshnagar.rover.utils.CommonUtils;
import com.yogeshnagar.rover.utils.RoverFactory;

/**
 * 
 * @author Yogesh Nagar
 * Controller class to perform instruction processing and initializing
 * Singleton Implementation
 */
public class FlightManager {
	
	private static volatile FlightManager _manager;
	
	private Plateau _plateau;
	
	private List<IRover> _roverList;
	
	private int numberOfRovers;
	
	/**
	 * Constructor
	 */
	private FlightManager() {
	}
	
	/**
	 * Returns the singleton instance of class
	 * @return
	 */
	public static FlightManager getInstance() {
		if (_manager == null) {
			synchronized (FlightManager.class) {
				if (_manager == null) {
					_manager = new FlightManager();
				}
			}
		}
		return _manager;
	}
	
	/**
	 * Used to reinitialize the instance - Test Method
	 */
	public synchronized static void clear() {
		_manager = null;
	}
	
	/**
	 * 
	 * @param plateauLength
	 * @param plateauWidth
	 * @param initialLocations
	 * @throws BadRoverLocationException
	 */
	public void initialize(int plateauLength, int plateauWidth, List<String> initialLocations) throws BadRoverLocationException {
		_plateau = Plateau.getPlateauInstance(plateauLength, plateauWidth);
		_roverList = RoverFactory.getRovers(initialLocations.size());
		prepareRovers(_roverList, initialLocations);
	}
	
	/**
	 * 
	 * @param rovers
	 * @param initialLocations
	 * @throws BadRoverLocationException
	 */
	private void prepareRovers(List<IRover> rovers, List<String> initialLocations) throws BadRoverLocationException {
		List<String> locationList = new ArrayList<String>(initialLocations);
		int index = 0;
		for (IRover rover: rovers) {
			String locationString = locationList.get(index);
			index ++;
			rover.setRoverId(CommonUtils.getRoverId());
			rover.setLocation(CommonUtils.getLocation(locationString));
		}
	}
	
	/**
	 * 
	 * @param instructionSet
	 * @return
	 * @throws BadRoverLocationException
	 */
	public List<String> processInstructions(List<String> instructionSet) throws BadRoverLocationException {
		int index = 0;
		List<String> newLocations = new ArrayList<String>();
		for (String instructions: instructionSet) {
			IRover rover = _roverList.get(index);
			rover.move(instructions);
			newLocations.add(rover.getLocation().toString());
			index ++;
		}
		return newLocations;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfRovers() {
		return numberOfRovers;
	}
	
	/**
	 * 
	 * @return
	 */
	public Plateau getPlateauInstance() {
		return _plateau;
	}

}
