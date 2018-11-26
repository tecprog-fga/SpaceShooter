/*********************************************************
  * File: GenerateBehavior.java
  * Purpose: GenerateBehavior class implementation
  ********************************************************/

package behavior;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

import org.apache.log4j.Logger;

import commands.Command;
import commands.CommandCreator;
import commands.CommandType;
import commands.MoveCommand;
import java.util.ArrayList;

/**
 * class that generates the behavior of game commands
 */
public class GenerateBehavior {
	
	private Path SCRIPT_PATH;
	static boolean errorOcurred = false;
	final static Logger logger = Logger.getLogger(GenerateBehavior.class);
	
	public GenerateBehavior(Path scriptPath) {
		try {
			assert(scriptPath != null);
			this.SCRIPT_PATH = scriptPath;
		}
		catch(NullPointerException exception) {
			logger.error("ScriptPath object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}

	public final ArrayList<Command> processBehavior() throws IOException {
		ArrayList<Command> commandsList = null;
		commandsList = new ArrayList<Command>();
		assert(commandsList != null):("Objeto commandsList não pode ser nulo!");
		final Charset ENCODING = StandardCharsets.UTF_8;  
		try (Scanner commands = new Scanner(SCRIPT_PATH, ENCODING.name())) {
			while (commands.hasNextLine()) {
				commandsList.add(processLine(commands.nextLine()));
			}
		}

		assert(commandsList != null):("Objeto commandsList não pode ser nulo!");
		return commandsList;
	}

	protected Command processLine(String commandLine) throws IOException {
		assert(commandLine != " "):("String commandLine não é válida!");
		Scanner commandInput = null;
		commandInput = new Scanner(commandLine);
		assert(commandInput != null):("Objeto commandInput não foi recebido!");
		
		if (commandInput.hasNext()) {
			String commandOutput = commandInput.next();
			logger.debug("Command received: " + quote(commandOutput.trim()));
			commandInput.close();
			Command command = CommandCreator.createPlayerCommand(CommandType.valueOf(commandOutput));
			assert(command != null):("Objeto command não pode ser nulo!");
			
			return command;

		} else {
			logger.debug("Empty or invalid line. Unable to process.");
			commandInput.close();
			throw new IOException();
		}
	}

	private String quote(String aText) {
		assert(aText != " "):("String aText não é válida!");
		String QUOTE = "'";
		return QUOTE + aText + QUOTE;
	}

	private static void log(Object aObject) {
		try {
			assert(aObject != null);
		}
		catch(NullPointerException exception) {
			logger.error("aObject object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		logger.debug(String.valueOf(aObject));
	}
}