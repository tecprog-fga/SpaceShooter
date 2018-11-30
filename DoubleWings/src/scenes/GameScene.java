/**
 * File: GameScene.java
 * Purpose: Build the game main scene
 */

package scenes;

import game.GameController;
import jplay.Keyboard;
import org.apache.log4j.Logger;

/**
 * This class build the game main scene and 
 * release the keyboard to control the game
 */
public abstract class GameScene {
	
	// This method Build the updated sprite image on the screen
	public abstract void updateScene();
	
	// Object for execute game
	protected GameController game = null;
		
	// Object for control keyboard game
	protected Keyboard keyboard = null;
	
	// Object to set the log for this class
	final static Logger logger = Logger.getLogger(GameScene.class);

	/**
	 * This method Configure the controls in the keyboard and
	 * the scene to show on the screen
	 * @param game - object for execute game
	 */
	public void configureGameScene(GameController game) {
		
		configureGame(game);
		configureKeyboard(game);
		configureMessage(); 
		buildInitialScene();
		viewSetup();
	}

	public void configureMessage() {
		
		final String MSG_KEYBOARD = "keyboard: ";
		System.out.println(MSG_KEYBOARD + this.keyboard);
	}

	public void configureKeyboard(GameController game) {
		
		try {
			this.keyboard = game.keyboard;
			assert(this.keyboard != null):("Keyboard cannot be null");
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, keyboard cannot set value", exception);		
			exception.printStackTrace();
		}
	}

	public void configureGame(GameController game) {
		
		try {
			this.game = game;
			assert(this.game != null):("Game cannot be null");
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, game cannot set value", exception);
			exception.printStackTrace();
		}
	}
	
	// This method build all initial sprites for show scenes. 
	protected abstract void buildInitialScene();
	
	// This is a void method used for configure the scene.	 
	protected abstract void viewSetup();
	
	public void destroyKeyboard() {
		this.keyboard = null;
		assert(this.keyboard == null):("Keyboard must be null to destory the scene");
	}

	public void destroyGame() {
		this.game = null;
		assert(this.game == null):("Game must be null to destroy the scene");
	}
	
	// This method Destroy the current scene to show another scene  
	public void destroyScene() {
		
		destroyGame();
		destroyKeyboard();
	}
}