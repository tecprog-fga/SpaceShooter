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
		assert(type != null):("Objeto type não foi recebido!");
		// executes the command equivalent to the key pressed by the player
		switch(type) {
			case SHOOT:
				assert(CommandType.UP != null):("Comando não recebido!");
			return new MoveCommand(CommandType.UP);
			
			default:
				assert(type != null):("Objeto type não recebido!");
			return new MoveCommand(type);
		}
	}
}