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

/** Assumes UTF-8 encoding. JDK 7+. */
public class GenerateBehavior {

	
  /**
   Constructor.
   @param scriptPath path of an existing, readable file.
  */
  public GenerateBehavior(Path scriptPath){
    this.scriptPath = scriptPath;
  }
  
  
	/** Template method that calls {@link #processLine(String)}.  */
	public final ArrayList<Command> processBehavior() throws IOException {
		ArrayList<Command> commandsList = new ArrayList<Command>();
		try (Scanner commands =  new Scanner(scriptPath, ENCODING.name())){
			while (commands.hasNextLine()){
				commandsList.add(processLine(commands.nextLine()));
			}
		}
		
		return commandsList;
	}
  
  
  /** 
   Overridable method for processing lines in different ways.
    
   <P>This simple default implementation expects simple commands, one per line 
   Examples of valid input: 
   <tt>SHOOT</tt>
   <tt>LEFT</tt>
   <tt>DOWN</tt>
   <tt>RIHT</tt>
   <tt>UP</tt>
  */
  protected Command processLine(String commandLine) throws IOException{
    //use a second Scanner to parse the content of each line 
    Scanner commandInput = new Scanner(commandLine);
    if (commandInput.hasNext()){
      //assumes the line has a certain structure
      String commandOutput = commandInput.next();
      log("Command received: " + quote(commandOutput.trim()));
      commandInput.close();
      
     Command command = CommandCreator.createCommand(CommandType.valueOf(commandOutput));
      
      return command;
      
    }
    else {
      log("Empty or invalid line. Unable to process.");
      commandInput.close();
      throw new IOException();
    }
  }
  
  
  // PRIVATE 
  private final Path scriptPath;
  private final static Charset ENCODING = StandardCharsets.UTF_8;  
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }

  //Arranjar uma classe pra deixar isso depois, tá repetida em generate Behavior.
  private static void log(Object aObject){
	System.out.println(String.valueOf(aObject));
  }
  
}
