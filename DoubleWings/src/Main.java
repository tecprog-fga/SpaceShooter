import jplay.Window; 
import java.awt.Color;
import constants.WindowConstants;
import scenes.GameScene;
import scenes.menu.MenuScene;
import game.GameController;

public class Main {
	
	public final static int KEEP_FRAMERATE = 16;
	
	public static void main(String[] args) {

		System.out.println("It's running!");

		/**
		 * It creates an windows with 800 pixels of width and 600 pixels of height   
		 */
		Window gameScreen = null;
		gameScreen = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);

		/**
		 * Game controller handles game states, screen changes, stages...
		 */
		GameController game = null;
		game = new GameController();
		game.keyboard = gameScreen.getKeyboard();

		/**
		 * Should transit first to menu... but for development purposes...
		 */
		GameScene scene = null;
		scene = new MenuScene();
		game.transitTo(scene);

		boolean gameIsRunning = true;
		
		/**
		 * Game main loop
		 */
		while(gameIsRunning) {
			/**
			 * Delay to keep 60 FPS
			 */
			gameScreen.delay(KEEP_FRAMERATE);

			/**
			 * Clear screen
			 */
			gameScreen.clear(Color.black);

			/**
			 *  update game
			 */
			gameIsRunning = game.update();

			/**
			 * Refresh the screen
			 */
			gameScreen.update();
		}

		/**
		 * Leaving the game
		 */
		gameScreen.exit();
	}
}