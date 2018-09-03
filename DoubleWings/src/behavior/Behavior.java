package behavior;

import java.util.ArrayList;
import commands.Command;

public class Behavior {

	private static ArrayList<Command> COMMAND_LIST;
	/**
	 * Constructor.
	 * @param commandList list of commands to be executed for an specific behavior.
	 */
	public Behavior(ArrayList<Command> commandList) {
		Behavior.COMMAND_LIST = commandList;
	}

	public static ArrayList<Command> getCommandList() {
		return COMMAND_LIST;
	}

	public static void setCommandList(ArrayList<Command> commandList) {
		Behavior.COMMAND_LIST = commandList;
	}
}