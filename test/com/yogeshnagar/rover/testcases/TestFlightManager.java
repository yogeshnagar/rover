package com.yogeshnagar.rover.testcases;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.controller.FlightManager;

import junit.framework.TestCase;

public class TestFlightManager extends TestCase {
	
	@Test
	public void testRoverMovementSuccess() {
		boolean movementSuccess = false;
		java.util.List<String> instructions = new ArrayList<String>();
		instructions.add("MM");
		try {
			FlightManager.getInstance().processInstructions(instructions);
			movementSuccess = true;
		} catch (BadRoverLocationException e) {
		}
		assertTrue(movementSuccess);
	}
	
	@Ignore
	public void  b() {
		boolean movementSuccess = false;
		java.util.List<String> instructions = new ArrayList<String>();
		instructions.add("MMMMMMMMMMMMM");
		try {
			FlightManager.getInstance().processInstructions(instructions);
			movementSuccess = true;
		} catch (BadRoverLocationException e) {
		}
		assertFalse(movementSuccess);
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
