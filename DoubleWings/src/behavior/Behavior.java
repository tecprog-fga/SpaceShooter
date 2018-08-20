package behavior;

import java.util.ArrayList;

import commands.Command;

public class Behavior {
	
	/**
	 * Constructor.
	 * @param commandList list of commands to be executed for an specific behavior.
	 */
	public Behavior(ArrayList<Command> commandList) {
		Behavior.commandList = commandList;
	}
	
	public static ArrayList<Command> getCommandList() {
		return commandList;
	}

	public static void setCommandList(ArrayList<Command> commandList) {
		Behavior.commandList = commandList;
	}
	
	// PRIVATE
	private static ArrayList<Command> commandList;

}
