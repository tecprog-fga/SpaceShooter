package game.evolver;

import java.util.ArrayList;

public class GameEvolver {

	private ArrayList<GameEvent> history = new ArrayList<GameEvent>();
	private ArrayList<GameEvent> aux = new ArrayList<GameEvent>();
	private ArrayList<GameEvent> events = new ArrayList<GameEvent>();
	private int iterator = 0; 
	private boolean isPaused = true;
	
	public void start(){
		isPaused = false;
	}
	
	
	public void add(GameEvent event){
		if (event != null){
			System.out.println("Adding new event: " + event.name);
			events.add(event);
		}
	}
	
	public int getCurrentIteration(){
		return iterator;
	}
	
	public void remove(GameEvent event){
		if (event != null){
			events.remove(event);
		}
	}
	
	public void update(){
		if (isPaused == false){
			//System.out.println("GameIteration: " + iterator);
			iterator += 1;
			checkEvents();
		}
	}
	
	public void pause(){
		isPaused = true;
	}
	
	private void checkEvents(){
		
		//Calling events
		for (int i = 0; i < events.size(); i++){
			GameEvent e = events.get(i);
			
			//System.out.println("e.name: " + e.time + " " + iterator);
			if(e.time == iterator){
				history.add(e);
				aux.add(e);
				
				System.out.println("GameEvent Callback: " + e.name);
				e.run();
			}
		}
		
		//Removing called events
		for (int k = 0; k < aux.size(); k++){
			GameEvent e = aux.get(k);
			events.remove(e);
		}
		
		aux.clear();
	}
	
}
