/*********************************************************
  * File: Main.java
  * Purpose: Main class implementation
  ********************************************************/

import jplay.Window; 
import java.awt.Color;
import constants.WindowConstants;
import scenes.GameScene;
import scenes.menu.MenuScene;
import game.GameController;

/**
 * This class initialize the game
 */
public class Main {
		
	/**
	 * Main method for execute the game
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("It's running!");
		
		/**
		 * creates the game screen with the default dimensions
		 */
		Window gameScreen = null;
		gameScreen = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);
		assert(gameScreen != null):("Objeto gameScreen não pode ser nulo");
		/**
		 * controls changes in game states
		 */
		GameController game = null;
		game = new GameController();
		game.keyboard = gameScreen.getKeyboard();
		assert(game != null):("Objeto game não pode ser nulo");

		/**
		 * controls the scene changes, the stages of the game
		 */
		GameScene scene = null;
		scene = new MenuScene();
		game.transitTo(scene);
		assert(scene != null):("Objeto scene não pode ser nulo");

		boolean gameIsRunning = true;
		
		//while the game is open, the screen must be loaded and updated constantly
		while(gameIsRunning) {
			/**
			 * variable that indicates how long the game is going to be in sleep mode
			 */
			final int KEEP_FRAMERATE = 16;
			
			gameScreen.delay(KEEP_FRAMERATE);

			gameScreen.clear(Color.black);
			assert(Color.black != null):("Objeto não foi recebido!");

			gameIsRunning = game.update();

			gameScreen.update();
		}

		gameScreen.exit();
	}
}