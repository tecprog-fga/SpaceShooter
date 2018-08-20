import jplay.Window; 

import java.awt.Color;

import constants.WindowConstants;
import scenes.GameScene;
import scenes.menu.MenuScene;
import game.GameController;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("It's running!");

		//It creates an windows with 800 pixels of width and 600 pixels of height   
		Window window = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);
		
		//Game controller handles game states, screen changes, stages...
		GameController game = new GameController();
		game.keyboard = window.getKeyboard();
		
		//Should transit first to menu... but for development purposes...
		GameScene scene = new MenuScene();
		game.transitTo(scene);
		
		boolean isRunning = true;
		
		//Game main loop
		while(isRunning) {
			
			//Delay to keep 60 FPS
			window.delay(16);
			
			//Clear screen
			window.clear(Color.black);
			
			// update game
			isRunning = game.update();
			
			//Refresh the screen
			window.update();
		
		}
		
		//Leaving the game
		window.exit();
	}
}
