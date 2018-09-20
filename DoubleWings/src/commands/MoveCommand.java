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

	public MoveCommand(CommandType type) {
		this.direction = type;
	}

	public boolean executeDisplacement(Sprite actor) {
		/**
		 * the enemy must move on the screen for a distance
		 */
		if (this.distanceToMove > 0) {
			moveActor(actor);
			this.distanceToMove -= UNITARY_DISPLACEMENT;
			return false;
		} else {
			return true;
		}
	}

	public boolean executeDisplacement(Sprite[] actors) {
		/**
		 * enemies must move on the screen for a distance limit
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