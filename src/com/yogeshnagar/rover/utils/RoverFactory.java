package com.yogeshnagar.rover.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.yogeshnagar.rover.common.IRover;
import com.yogeshnagar.rover.mc.Rover;

/**
 * 
 * @author Yogesh Nagar
 * Factory for generating Rover's object
 */
public class RoverFactory {
	
	/**
	 * Returns a list of IRover implementation
	 * @param numberOfRovers Number of Rovers
	 * @return List of Rovers
	 */
	public static List<IRover> getRovers(int numberOfRovers) {
		return IntStream.rangeClosed(1, numberOfRovers)
				.mapToObj(i -> new Rover()).collect(Collectors.toList());
	}

}
