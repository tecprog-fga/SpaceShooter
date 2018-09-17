package behavior;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import commands.Command;
import commands.CommandCreator;
import commands.CommandType;
import commands.MoveCommand;
import java.util.ArrayList;

public class GenerateBehavior {
	
	private final Path SCRIPT_PATH;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  

	public GenerateBehavior(Path scriptPath) {
		this.SCRIPT_PATH = scriptPath;
	}

	public final ArrayList<Command> processBehavior() throws IOException {
		ArrayList<Command> commandsList = null;
		commandsList = new ArrayList<Command>();
		try (Scanner commands = new Scanner(SCRIPT_PATH, ENCODING.name())) {
			while (commands.hasNextLine()) {
				commandsList.add(processLine(commands.nextLine()));
			}
		}

		return commandsList;
	}

	protected Command processLine(String commandLine) throws IOException {
		Scanner commandInput = null;
		commandInput = new Scanner(commandLine);
		
		if (commandInput.hasNext()) {
			String commandOutput = commandInput.next();
			log("Command received: " + quote(commandOutput.trim()));
			commandInput.close();
			Command command = CommandCreator.createPlayerCommand(CommandType.valueOf(commandOutput));

			return command;

		} else {
			log("Empty or invalid line. Unable to process.");
			commandInput.close();
			throw new IOException();
		}
	}

	private String quote(String aText) {
		String QUOTE = "'";
		return QUOTE + aText + QUOTE;
	}

	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}
}