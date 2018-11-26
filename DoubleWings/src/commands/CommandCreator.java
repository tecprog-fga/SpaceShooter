/*********************************************************
  * File: CommandCreator.java
  * Purpose: CommandCreator class implementation
  ********************************************************/

package commands;

import org.apache.log4j.Logger;

/**
 * class that manages the player commands
 */
public class CommandCreator {
	
	static boolean errorOcurred = false;
	final static Logger logger = Logger.getLogger(CommandCreator.class);
	
	/**
	 * Create a command for player
	 * @param type type of player input
	 * @return MoveCommand
	 */
	public static Command createPlayerCommand(CommandType type) {
		MoveCommand command = null;
		try {
			assert(type != null);
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
		}
		catch(NullPointerException exception) {
			logger.error("Type object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return command;
	}
}