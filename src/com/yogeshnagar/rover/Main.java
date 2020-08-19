package com.yogeshnagar.rover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yogeshnagar.rover.common.IConstants;
import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.controller.FlightManager;
import com.yogeshnagar.rover.utils.CommonUtils;

/**
 * 
 * @author Yogesh Nagar
 * Main class to Rum Application
 */
public class Main {

	/**
	 * @param args Arguments 
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		
		try {
			// Setting default Pleateau dimensions
			int plateauLength=5;
			int plateauWidth=5;
			
			// Initializing input lists
			List<String> initialLocations = new ArrayList<String>();
			List<String> instructionList = new ArrayList<String>();
			
			// Read Plateau size from the Console
			String pleateauCoordinateString = CommonUtils.readLineFromConsole();
			String[] plateauCoordinate = pleateauCoordinateString.split(IConstants.EMPTY_SPACE);
			
		    // Process input read from Console	
			try {
				plateauLength = Integer.parseInt(plateauCoordinate[0]);
				plateauWidth = Integer.parseInt(plateauCoordinate[1]);
			} catch (NumberFormatException ne) {
				System.err.println("Not a valid plateau dimensions : " + pleateauCoordinateString);
				System.exit(1);
			}
			
			// Read Rovers initial location and Instructions from Console
			String input = null;
			do {
				input = CommonUtils.readLineFromConsole();
				if (input.trim().equalsIgnoreCase("")) {
					break;
				}
				initialLocations.add(input);
				input = CommonUtils.readLineFromConsole();
				instructionList.add(input);
			} while (!input.trim().equalsIgnoreCase(""));

			// Initialize Flight Manager and Process instructions
			FlightManager flightManager = FlightManager.getInstance();
			try {
				flightManager.initialize(plateauLength, plateauWidth, initialLocations);
		        List<String> newLocationStrings = flightManager.processInstructions(instructionList);
				if (newLocationStrings != null) {
					for (String location: newLocationStrings) {
						System.out.println(location);
					}
				}
			} catch (BadRoverLocationException bre) {
				bre.printStackTrace();
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			CommonUtils.closeConsole();
		}
	}

}
