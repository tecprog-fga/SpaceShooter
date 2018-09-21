/*********************************************************
  * File: MoveCommand.java
  * Purpose: MoveCommand class implementation
  ********************************************************/

package commands;

import entity.Enemy;
import jplay.Sprite;

public class MoveCommand implements Command {
	/**
	 * Distance to enemy movimentation in pixels
	 */
	private double distanceToMove = 50;
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
		this.direction = type;
	}
	
	/**
	 * performs the displacement of the actor
	 * @param actor the actor
	 */ 
	public boolean executeDisplacement(Sprite actor) {
		/**
		 * the actor must move on the screen for a distance
		 */
		if (this.distanceToMove > 0) {
			moveActor(actor);
			this.distanceToMove -= UNITARY_DISPLACEMENT;
			return false;
		} else {
			return true;
		}
	}

	/**
	 * performs the movement of a set of actors
	 * @param actors array of actors
	 * @return
	 */
	public boolean executeDisplacement(Sprite[] actors) {
		/**
		 * actors must move on the screen for a distance limit
		 */
		if (this.distanceToMove > 0) {
			for(Sprite actor: actors) {
				moveActor(actor);
			}
			this.distanceToMove -= UNITARY_DISPLACEMENT;
			return false;
		} else {
			return true;
		  }
	}

	/**
	 * move the actor across the screen
	 * @param actor object that will be moved by the screen
	 */
	private void moveActor(Sprite actor) {
		/**
		 * must move the game object in one of the four main directions
		 */
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
		}
	}

}