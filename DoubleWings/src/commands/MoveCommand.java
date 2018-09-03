package commands;

import jplay.Sprite;

public class MoveCommand implements Command {
	/**
	 * The distance yet to be traveled by the actor of this command.
	 * */
	private double distanceToMove = 50;
	private CommandType direction = null;
	private final int UNITARY_DISPLACEMENT = 1;

	/**
	 * 
	 * @param direction string specifying the direction to move the actor.
	 */
	public MoveCommand(CommandType type) {
		this.direction = type;
	}

	/**
	 * Move an actor by `distanceToGo` pixels in the command direction. Must be called inside the update() method to work properly.
	 * @param actor the actor to be moved by the command.
	 * @return true if movement is completed, false otherwise. 
	 */
	public boolean executeDisplacement(Sprite actor) {
		if (this.distanceToMove > 0) {
			moveActor(actor);
			this.distanceToMove -= UNITARY_DISPLACEMENT;
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Move an array of actors by `distanceToGo` pixels in the command direction. Must be called inside the update() method to work properly.
	 * @param actor the actor to be moved by the command.
	 * @return true if movement is completed, false otherwise. 
	 */
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

	/**
	 * Define how the actor will be moved in each call to this command. Must override.
	 * @param actor the actor to be moved by the command. 
	 */
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