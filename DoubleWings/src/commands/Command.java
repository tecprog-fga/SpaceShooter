/*********************************************************
  * File: Command.java
  * Purpose: Command interface implementation
  ********************************************************/

package commands;

import entity.Enemy;
import jplay.Sprite;

public interface Command {
	public boolean executeDisplacement(Sprite actor);
}