package commands;

public class CommandCreator {
	
	public static Command createPlayerCommand(CommandType type) {
		switch(type) {
			case SHOOT:
			return new MoveCommand(CommandType.UP);
			
			default:
			return new MoveCommand(type);
		}
	}
}