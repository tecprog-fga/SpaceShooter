/*********************************************************
  * File: MoveCommand.java
  * Purpose: MoveCommand class implementation
  ********************************************************/

package commands;

import org.apache.log4j.Logger;

import entity.Enemy;
import jplay.Sprite;

/**
 * class that manages the movements of objects on the screen
 */
public class MoveCommand implements Command {
	
	boolean errorOcurred = false;
	final static Logger logger = Logger.getLogger(MoveCommand.class);
	/**
	 * Distance to enemy movimentation in pixels
	 */
	private double distanceToMove = 50; {
	assert(distanceToMove >= 0):("Distância de deslocamento não pode ser negativa!");
	}
	private CommandType direction = null;
	/**
	 * enemy's on-screen movement of the game, in pixels
	 */
	private final int UNITARY_DISPLACEMENT = 1;

	/**
	 * Constructor method of class MoveCommand
	 * @param type type of player input
	 */
	public MoveCommand(CommandType type) {
		try {
			assert(type != null);
			this.direction = type;
		}
		catch(NullPointerException exception) {
			logger.error("Type object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}
	
	/**
	 * performs the displacement of the actor
	 * @param actor the actor
	 */ 
	public boolean executeDisplacement(Sprite actor) {
		
		boolean displacement = false;
		try {
			// the actor must move on the screen for a distance 
			assert(actor != null);
			if (this.distanceToMove > 0) {
				moveActor(actor);
				this.distanceToMove -= UNITARY_DISPLACEMENT;
				displacement = false;
			} else {
				displacement = true;
			}
		}
		catch(NullPointerException exception) {
			logger.error("Actor object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
			return displacement;
	}

	/**
	 * Move an array of actors by `distanceToGo` pixels in the command direction. Must be called inside the update() method to work properly.
	 * @param actors actor the actor to be moved by the command.
	 * @return true if movement is completed, false otherwise. 
	 */
	public boolean executeDisplacement(Sprite[] actors) {
		boolean displacement = false;
		try {
			// actors must move on the screen for a distance limit
			assert(actors != null);
			if (this.distanceToMove > 0) {
				for(Sprite actor: actors) {
					moveActor(actor);
				}
				this.distanceToMove -= UNITARY_DISPLACEMENT;
				displacement = false;
			} else {
				displacement = true;
			  }
		}
		catch(NullPointerException exception) {
			logger.error("Actor object array can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return displacement;
	}
	
	private void moveActor(Sprite actor) {
		try {
			// must move the game object in one of the four main directions
			assert(actor != null);
			switch(this.direction) {
			case LEFT:
				actor.x -= UNITARY_DISPLACEMENT;
				break;
				
			case DOWN:
				actor.y += UNITARY_DISPLACEMENT;
				break;
				
			case RIGHT:
				actor.x += UNITARY_DISPLACEMENT;
				break;
				
			case UP:
				actor.y -= UNITARY_DISPLACEMENT;
				break;
				
			default:
				//nothing to do
				assert(direction != null):("Direction can't be null!");
			break;
			}
		}
		catch(NullPointerException exception) {
			logger.error("Actor object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}
}