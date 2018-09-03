package commands;

import jplay.Sprite;

public interface Command {
	public boolean executeDisplacement(Sprite actor);
}