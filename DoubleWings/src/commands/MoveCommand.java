package commands;

import entity.Enemy;
import jplay.Sprite;

public class MoveCommand implements Command {
	private double distanceToMove = 50;
	private CommandType direction = null;
	/**
	 * player's on-screen movement of the game, in pixels
	 */
	private final int UNITARY_DISPLACEMENT = 1;

	public MoveCommand(CommandType type) {
		this.direction = type;
	}

	public boolean executeDisplacement(Sprite actor) {
		if (this.distanceToMove > 0) {
			moveActor(actor);
			this.distanceToMove -= UNITARY_DISPLACEMENT;
			return false;
		} else {
			return true;
		}
	}

	public boolean executeDisplacement(Sprite[] actors) {
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