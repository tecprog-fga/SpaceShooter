package behavior;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import behavior.GenerateBehavior;
import commands.Command;

public class ScriptHandler {

	/**
	 * Constructor.
	 * @param scriptPath full path of an existing, readable file.
	 */
	public ScriptHandler(String scriptPath) {
		ScriptHandler.SCRIPT_PATH = Paths.get(scriptPath);
	}

	public static ArrayList<Command> receiveInput(String scriptInput) throws IOException {
		GenerateBehavior parser = null;
		parser = new GenerateBehavior(SCRIPT_PATH);
		ArrayList<Command> commandList = parser.processBehavior();
		log("Done.");

		return commandList;
	}

	/**
	 *  PRIVATE 
	 */
	private static Path SCRIPT_PATH;

	/**
	 *  Arranjar uma classe pra deixar isso depois, tá repetida em generate Behavior.
	 * @param aObject
	 */
	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}
}