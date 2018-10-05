/*********************************************************
  * File: CommandCreator.java
  * Purpose: CommandCreator class implementation
  ********************************************************/

package commands;

/**
 * class that manages the player commands
 */
public class CommandCreator {
	
	/**
	 * Create a command for player
	 * @param type type of player input
	 * @return MoveCommand
	 */
	public static Command createPlayerCommand(CommandType type) {
		/*
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