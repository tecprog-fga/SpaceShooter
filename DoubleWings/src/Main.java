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
import org.apache.log4j.Logger;

/**
 * This class initialize the game
 */
public class Main {
	
	static boolean errorOcurred = false;
	final static Logger logger = Logger.getLogger(Main.class);
		
	/**
	 * Main method for execute the game
	 * @param args
	 */
	public static void main(String[] args) {

		logger.info("Game is running!");
		Window gameScreen = initializeGameWindow();
		GameController game = initializeGameController(gameScreen);
		GameScene scene = initializeGameScene(game);
		
		boolean gameIsRunning = true;
		
		//while the game is open, the screen must be loaded and updated constantly
		while(gameIsRunning) {
			/**
			 * variable that indicates how long the game is going to be in sleep mode
			 */
			final int KEEP_FRAMERATE = 16;
			
			gameScreen.delay(KEEP_FRAMERATE);

			gameScreen.clear(Color.black);
			
			gameIsRunning = game.update();

			gameScreen.update();
		}

		gameScreen.exit();
	}

	/**
	 * method responsible for creating the game screen
	 * @return Window
	 */
	private static Window initializeGameWindow() {
		/**
		 * creates the game screen with the default dimensions
		 */
		Window gameScreen = null;
		try {
			gameScreen = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);
			assert(gameScreen != null);
		}
		catch(NullPointerException exception) {
			logger.error("GameScreen object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return gameScreen;
	}
	
	/**
	 * method responsible for creating the game controls
	 * @param gameScreen
	 * @return GameController
	 */
	private static GameController initializeGameController(Window gameScreen) {
		/**
		 * controls changes in game states
		 */
		GameController game = null;
		game = new GameController();
		try {
			game.keyboard = gameScreen.getKeyboard();
			assert(game != null);
		}
		catch(NullPointerException exception) {
			logger.error("Game object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return game;
	}
	
	/**
	 * method responsible for creating the game scene
	 * @param game
	 * @return GameScene
	 */
	private static GameScene initializeGameScene(GameController game) {
		/**
		 * controls the scene changes, the stages of the game
		 */
		GameScene scene = null;
		scene = new MenuScene();
		try {
			game.transitTo(scene);
			assert(scene != null):("Scene Object can't be null");
		}
		catch(NullPointerException exception) {
			logger.error("Scene object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return scene;
	}
}