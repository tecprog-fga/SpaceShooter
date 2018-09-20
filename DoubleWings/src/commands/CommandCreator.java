package commands;

public class CommandCreator {
	
	public static Command createPlayerCommand(CommandType type) {
		/**
		 * executes the command equivalent to the key pressed by the player
		 */
		switch(type) {
			case SHOOT:
			return new MoveCommand(CommandType.UP);
			
			default:
			return new MoveCommand(type);
		}
	}
}