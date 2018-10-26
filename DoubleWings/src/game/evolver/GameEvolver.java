/*********************************************************
  * File: GameEvolver.java
  * Purpose: GameEvolver class implementation
  *********************************************************/

package game.evolver;

import java.util.ArrayList;


/**
 * This class implements interactions with the game interface
 */
public class GameEvolver {

	private boolean isPaused = true;
	
	/**
	 * game initializer 
	 */
	public void start() {
		isPaused = false;
	}
	
	private ArrayList<GameEvent> events = new ArrayList<GameEvent>();
	
	/**
	 * add event to scene
	 * @param event
	 */
	public void add(GameEvent event) {
		assert(event != null): ("Adding new event");
		events.add(event);	
	}
	
	private int iterator = 0;
	public int getCurrentIteration() {
		return iterator;
	}
	
	/**
	 * remove event to scene
	 * @param event
	 */
	public void remove(GameEvent event) {
		if (event != null) {
			events.remove(event);
		}
		else {
			//Nothing to do
		}
	}
	
	public void update() {
		if (isPaused == false) {
			iterator += 1;
			checkEvents();
		}
		else {
			//Nothing to do
		}
	}
	
	public void pause() {
		isPaused = true;
	}
	
	private ArrayList<GameEvent> history = new ArrayList<GameEvent>();
	private ArrayList<GameEvent> aux = new ArrayList<GameEvent>();
	
	private void checkEvents() {
		
		/**
		 * calling events
		 */
		for (int i = 0; i < events.size(); i++) {
			GameEvent e = events.get(i);
			
			if (e.time == iterator) {
				history.add(e);
				aux.add(e);				
				System.out.println("GameEvent Callback: " + e.name);
				e.run();
			}
			else {
				//Nothing to do
			}
		}
		
		/**
		 * removing called events
		 */
		for (int k = 0; k < aux.size(); k++) {
			GameEvent e = aux.get(k);
			events.remove(e);
		}	
		
		aux.clear();
	}
}