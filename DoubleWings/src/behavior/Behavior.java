/*********************************************************
  * File: Behavior.java
  * Purpose: Behavior class implementation
  ********************************************************/

package behavior;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import commands.Command;

/**
 * class responsible for behavior
 */
public class Behavior {
	
	static boolean errorOcurred = false;
	final static Logger logger = Logger.getLogger(Behavior.class);

	private static ArrayList<Command> COMMAND_LIST; {
	assert(COMMAND_LIST != null):("Objeto COMMAND_LIST não foi recebido!");
	}
	/**
	 * Constructor of Behavior class
	 * @param commandList
	 */
	public Behavior(ArrayList<Command> commandList) {
		try {
			assert(commandList != null);
			Behavior.COMMAND_LIST = commandList;
		}
		catch(NullPointerException exception) {
			logger.error("CommandList object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}

	/**
	 * getter of CommandList
	 * @return COMMAND_LIST return the menu
	 */
	public static ArrayList<Command> getCommandList() {
		try {
			assert(COMMAND_LIST != null);
		}
		catch(NullPointerException exception) {
			logger.error("COMMAND_LIST object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return COMMAND_LIST;
	}

	/**
	 * setter of CommandList
	 * @param commandList
	 */
	public static void setCommandList(ArrayList<Command> commandList) {
		try {
			assert(commandList != null);
			Behavior.COMMAND_LIST = commandList;
		}
		catch(NullPointerException exception) {
			logger.error("CommandList object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}
}