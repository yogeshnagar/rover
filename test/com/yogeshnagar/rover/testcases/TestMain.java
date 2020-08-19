package com.yogeshnagar.rover.testcases;

import java.util.ArrayList;
import java.util.List;

import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.controller.FlightManager;

import junit.framework.TestCase;

public class TestMain extends TestCase {
	
	public void testMain0() {
		processInstruction("LMLMLMLMM");
	}
	
	public void testMain1() {
		processInstruction("MRRMRRMRM");
	}
	
	public void testMain2() {
		processInstruction("MRMRMR");
	}
	
	public void testMain3() {
		processInstruction("LRL");
	}
	
	private void processInstruction(String testInput) {
		System.out.println("Testing Input:" + testInput);
		List<String> instructionSet = new ArrayList<String>();
		instructionSet.add(testInput);
		try {
			List<String> result = FlightManager.getInstance().processInstructions(instructionSet);
			System.out.println("Location of rover:" + result.get(0));
		} catch (BadRoverLocationException e) {
			e.printStackTrace();
		}
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
