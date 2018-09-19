/*********************************************************
  * File: GameEvent.java
  * Purpose: GameEvent class implementation
  *********************************************************/

package game.evolver;

public class GameEvent {
	
	public String name = null;
	public int type = 0;
	public int time = 0;
	private GameEventCallback callback;
	
	public void setCallback(GameEventCallback callback) {
		this.callback = callback;
	}
	
	public void run() {
		
		if (callback != null) {
			callback.eventCallback(this);
		}
	}
}