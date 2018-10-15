/*********************************************************
  * File: GameEvent.java
  * Purpose: GameEvent class implementation
  *********************************************************/

package game.evolver;


/**
 * This class defines an interface that receives by return the GameEventCallback
 */
public class GameEvent {
	
	public String name = null;
	public int type = 0;
	public int time = 0;
	private GameEventCallback callback;
	
	public void setCallback(GameEventCallback callback) {
		this.callback = callback;
	}
	
	public void run() {
		assert(callback != null): ("callback cannot be null");
		callback.eventCallback(this);
	}
}