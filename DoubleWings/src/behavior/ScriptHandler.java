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
		ScriptHandler.scriptPath = Paths.get(scriptPath);
	}
		
	public static ArrayList<Command> receiveInput(String scriptInput) throws IOException {
		GenerateBehavior parser = new GenerateBehavior(scriptPath);
		ArrayList<Command> commandList = parser.processBehavior();
		log("Done.");
		
		return commandList;
	}
	
	// PRIVATE 
	private static Path scriptPath;
	
	
	// Arranjar uma classe pra deixar isso depois, tá repetida em generate Behavior.
	private static void log(Object aObject){
		System.out.println(String.valueOf(aObject));
	}

}
