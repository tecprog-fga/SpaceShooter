/*********************************************************
  * File: Behavior.java
  * Purpose: Behavior class implementation
  ********************************************************/

package behavior;

import java.util.ArrayList;
import commands.Command;

/**
 * class responsible for behavior
 */
public class Behavior {

	private static ArrayList<Command> COMMAND_LIST; {
	assert(COMMAND_LIST != null):("Objeto COMMAND_LIST não foi recebido!");
	}
	/**
	 * Constructor of Behavior class
	 * @param commandList
	 */
	public Behavior(ArrayList<Command> commandList) {
		assert(commandList != null):("Objeto commandList não foi recebido!");
		Behavior.COMMAND_LIST = commandList;
	}

	/**
	 * getter of CommandList
	 * @return COMMAND_LIST return the menu
	 */
	public static ArrayList<Command> getCommandList() {
		assert(COMMAND_LIST != null):("COMMAND_LIST não pode ser nulo!");
		return COMMAND_LIST;
	}

	/**
	 * setter of CommandList
	 * @param commandList
	 */
	public static void setCommandList(ArrayList<Command> commandList) {
		assert(commandList != null):("Objeto commandList não foi recebido!");
		Behavior.COMMAND_LIST = commandList;
	}
}