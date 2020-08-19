package com.yogeshnagar.rover.common;

import static com.yogeshnagar.rover.common.IConstants.LEFT_MOVE;
import static com.yogeshnagar.rover.common.IConstants.RIGHT_MOVE;
import static com.yogeshnagar.rover.common.IConstants.WALK;

import java.util.Optional;

import com.yogeshnagar.rover.common.exceptions.BadRoverLocationException;
import com.yogeshnagar.rover.utils.CommonUtils;

/**
 * 
 * @author Yogesh Nagar
 * Utility class for performing direction and location related computations
 */
public class Compas { 
	
	/**
	 * Updates the Location object as per the instructions provided
	 * @param currentLocation Current Rover Location Object
	 * @param instructions Instructions received
	 * @throws BadRoverLocationException Thrown in case instructions leads to a Bad location
	 */
	public static void updateLocation(Location currentLocation, String instructions) throws BadRoverLocationException {
		int instructionsLength = instructions.length();
		Direction direction = currentLocation.getDirection();
		for (int index = 0; index < instructionsLength; index ++) {
			char instruction = instructions.charAt(index);
			switch (instruction) {
				case LEFT_MOVE:
					direction = computeDirection(direction, instruction);
					currentLocation.setDirection(direction);
					break;
				case RIGHT_MOVE:
					direction = computeDirection(direction, instruction);
					currentLocation.setDirection(direction);
					break;
				case WALK:
					moveAhead(currentLocation);
					break;
				default:
					throw new BadRoverLocationException("Instruction not recognized : " + instruction);
			}
		}
	}
	
	/**
	 * Method computes the New Direction based on Current direction and Movement
	 * @param currentDirection Current Direction
	 * @param move Movement
	 * @return Direction object
	 */
	private static Direction computeDirection(Direction currentDirection, char move) {
		String moveKey = currentDirection.getDirectionKey().concat("-").concat(Character.toString(move));
		Optional<Move> nextMove = CommonUtils.getNextMove(moveKey);
		if (nextMove.isPresent()) {
			return new Direction(nextMove.get().getValue());
		} else {
			return currentDirection;
		}
	}
	
	/**
	 * Computes the forward movement of the Rover. 
	 * @param location Current location object
	 * @throws BadRoverLocationException Thrown in case of Bad Location
	 */
	private static void moveAhead(Location location) {
		Optional<Move> nextMove = CommonUtils.getNextMove(location.getDirection().getDirectionKey());
		if (nextMove.isPresent()) {
			if (nextMove.get().getValue().equalsIgnoreCase("INCREASE-X")) {
				location.increaseXCoordinate();
			} else if (nextMove.get().getValue().equalsIgnoreCase("INCREASE-Y")) {
				location.increaseYCoordinate();
			}
		}
	}

}
