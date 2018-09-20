package behavior;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import behavior.GenerateBehavior;
import commands.Command;

public class ScriptHandler {

	private static Path SCRIPT_PATH;
	/**
	 * Constructor of ScriptHandler class
	 * @param scriptPath
	 */
	public ScriptHandler(String scriptPath) {
		ScriptHandler.SCRIPT_PATH = Paths.get(scriptPath);
	}

	/**
	 * receives the input to the commandList
	 * @param scriptInput 
	 * @return commandList
	 * @throws IOException
	 */
	public static ArrayList<Command> receiveInput(String scriptInput) throws IOException {
		GenerateBehavior parser = null;
		parser = new GenerateBehavior(SCRIPT_PATH);
		ArrayList<Command> commandList = parser.processBehavior();
		log("Done.");

		return commandList;
	}

	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}
}