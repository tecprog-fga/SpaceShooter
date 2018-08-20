package commands;

public class CommandCreator {
	public static Command createCommand(CommandType type) {
		switch(type) {
		case SHOOT:
			//TODO mudar para ShootCommand quando este passar a existir
			return new MoveCommand(CommandType.UP);
		default:
			return new MoveCommand(type);
		}
	}
}
