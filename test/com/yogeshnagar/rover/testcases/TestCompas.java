package com.yogeshnagar.rover.testcases;

import java.util.ArrayList;

import org.junit.Ignore;

import com.yogeshnagar.rover.common.Compas;
import com.yogeshnagar.rover.common.Direction;
import com.yogeshnagar.rover.common.IConstants;
import com.yogeshnagar.rover.common.Location;
import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.controller.FlightManager;

import junit.framework.TestCase;

public class TestCompas extends TestCase {
	
	public void testCompasSuccess() {
		boolean locationUpdated = false;
		Location testLocation = new Location(1, 2, new Direction(IConstants.NORTH_FACING));
		try {
			String instructions = "LMLMLMLMM";
			Compas.updateLocation(testLocation, instructions);
			locationUpdated = true;
		} catch (BadRoverLocationException bre) {
		}
		assertTrue(locationUpdated);
	}
	
	@Ignore
	public void xtestCompasFailure() {
		boolean locationUpdated = false;
		Location testLocation = new Location(1, 2, new Direction(IConstants.NORTH_FACING));
		try {
			String instructions = "LMLMLMLMMMMMMMMMMMM";
			Compas.updateLocation(testLocation, instructions);
			locationUpdated = true;
		} catch (BadRoverLocationException bre) {
		}
		assertFalse(locationUpdated);
	}
	
	public void setUp() {
		try {
			java.util.List<String> initialLocations = new ArrayList<String>();
			initialLocations.add("1 2 N");
			FlightManager.getInstance().initialize(5, 5, initialLocations);
		} catch (BadRoverLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void tearDown() {
		FlightManager.clear();
	}

}
