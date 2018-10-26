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
		MoveCommand command = null;
		assert(type != null):("Objeto type não foi recebido!");
		// executes the command equivalent to the key pressed by the player
		switch(type) {
			case SHOOT:
				command = new MoveCommand(CommandType.SHOOT);
				assert(CommandType.SHOOT != null):("Comando SHOOT não recebido!");
			
			case UP:
				command = new MoveCommand(CommandType.UP);
				assert(CommandType.UP != null):("Comando UP não recebido!");
			
			case DOWN:
				command = new MoveCommand(CommandType.DOWN);
				assert(CommandType.DOWN != null):("Comando DOWN não recebido!");
				
			case LEFT:
				command = new MoveCommand(CommandType.LEFT);
				assert(CommandType.LEFT != null):("Comando LEFT não recebido!");
				
			case RIGHT:
				command = new MoveCommand(CommandType.RIGHT);
				assert(CommandType.RIGHT != null):("Comando RIGHT não recebido!");
			
			default:
				//nothing to do
		}
		return command;
	}
}