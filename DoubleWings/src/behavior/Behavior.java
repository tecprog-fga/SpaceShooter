/*********************************************************
  * File: Behavior.java
  * Purpose: Behavior class implementation
  ********************************************************/

package behavior;

import java.util.ArrayList;
import commands.Command;

public class Behavior {

	private static ArrayList<Command> COMMAND_LIST;
	/**
	 * Constructor of Behavior class
	 * @param commandList
	 */
	public Behavior(ArrayList<Command> commandList) {
		Behavior.COMMAND_LIST = commandList;
	}

	/**
	 * getter of CommandList
	 * @return COMMAND_LIST return the menu
	 */
	public static ArrayList<Command> getCommandList() {
		return COMMAND_LIST;
	}

	/**
	 * setter of CommandList
	 * @param commandList
	 */
	public static void setCommandList(ArrayList<Command> commandList) {
		Behavior.COMMAND_LIST = commandList;
	}
}