/*********************************************************
  * File: ScriptHandler.java
  * Purpose: ScriptHandler class implementation
  ********************************************************/

package behavior;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import behavior.GenerateBehavior;
import commands.Command;

/**
 * class that handles scripts
 */
public class ScriptHandler {

	private static Path SCRIPT_PATH;
	/**
	 * Constructor of ScriptHandler class
	 * @param scriptPath
	 */
	public ScriptHandler(String scriptPath) {
		assert(scriptPath != " "):("String scriptPath não é válida!");
		ScriptHandler.SCRIPT_PATH = Paths.get(scriptPath);
	}

	/**
	 * receives the input to the commandList
	 * @param scriptInput 
	 * @return commandList
	 * @throws IOException
	 */
	public static ArrayList<Command> receiveInput(String scriptInput) throws IOException {
		assert(scriptInput != null):("String scriptInput não é válida!");
		GenerateBehavior parser = null;
		parser = new GenerateBehavior(SCRIPT_PATH);
		assert(parser != null):("Objeto parser não é válido!");
		ArrayList<Command> commandList = parser.processBehavior();
		assert(commandList != null):("Objeto commandList não é válido!");
		log("Done.");

		return commandList;
	}

	private static void log(Object aObject) {
		assert(aObject != null):("Objeto aObject não é válido!");
		System.out.println(String.valueOf(aObject));
	}
}