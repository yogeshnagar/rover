package com.yogeshnagar.rover.utils;

import static com.yogeshnagar.rover.common.IConstants.EMPTY_SPACE;
import static com.yogeshnagar.rover.common.IConstants.ROVER_NAME_PREFIX;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import com.yogeshnagar.rover.common.Direction;
import com.yogeshnagar.rover.common.Location;
import com.yogeshnagar.rover.common.Move;
import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.common.exceptions.ProcessingException;

public class CommonUtils {
	
	private static Map<String, Move> moves = null;

	private static int counter = 0;
	
	private static BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Generates a new Rover Id
	 * @return Rover Id
	 */
	public synchronized static String getRoverId() {
		return ROVER_NAME_PREFIX + (++ counter);
	}
	
	/**
	 * Returns a location object based on Location String
	 * @param locationString Location String
	 * @return Location object
	 * @throws BadRoverLocationException In case wrong location String
	 */
	public static Location getLocation(String locationString) {
		String[] locationStrSplit = locationString.split(EMPTY_SPACE);
		if (locationStrSplit.length == 3) {
			int x_coordinate = Integer.parseInt(locationStrSplit[0]);
			int y_coordinate = Integer.parseInt(locationStrSplit[1]);
			return new Location(x_coordinate, y_coordinate, new Direction(locationStrSplit[2]));
		} else {
			throw new BadRoverLocationException("Not a valid location: " + locationString);
		}		
	}
	
	/**
	 * Returns a Direction based on Direction String
	 * @param directionStr Direction String
	 * @return Direction Object
	 * @throws BadRoverLocationException In case wrong direction String is passed
	 */
	public static Direction getDirection(String directionStr) {
		return new Direction(directionStr);
	}
	
	public static Properties getProperties(String propertiesFile) {
		try {
			Properties properties = new Properties();
			properties.load(CommonUtils.class.getResourceAsStream(propertiesFile));
			return properties;
		} catch (IOException e) {
			throw new ProcessingException(e.getMessage(), e);
		}
	}
	
	public static Optional<Move> getNextMove(String key) {
		if (moves == null) {
			Properties moveProperties = getProperties("/move.properties");
			moves = moveProperties.entrySet().stream()
				.map(moveEntry -> 
					new Move(moveEntry.getKey().toString(), moveEntry.getValue().toString()))
				.collect(Collectors.toMap(Move::getKey, m -> m));
		}
		return Optional.ofNullable(moves.get(key));
	}
	
	/**
	 * Read data from Console
	 * @return User Input
	 * @throws IOException
	 */
	public static String readLineFromConsole() throws IOException {
		return consoleInput.readLine().trim();
	}
	
	public static void closeConsole() {
		if (consoleInput != null) {
			try {
				consoleInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
