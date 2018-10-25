/**
 * File: GameScene.java
 * Purpose: Build the game main scene
 */

package scenes;

import game.GameController;
import jplay.Keyboard;

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

	/**
	 * This method Configure the controls in the keyboard and
	 * the scene to show on the screen
	 * @param game - object for execute game
	 */
	public void configureGameScene(GameController game) {
		
		this.game = game;
		assert(this.game != null):("Game cannot be null");
		
		this.keyboard = game.keyboard;
		assert(this.keyboard != null):("Keyboard cannot be null");
		
		final String MSG_KEYBOARD = "keyboard: ";
		System.out.println(MSG_KEYBOARD + this.keyboard); 

		buildInitialScene();
		viewSetup();
	}
	
	// This method build all initial sprites for show scenes. 
	protected abstract void buildInitialScene();
	
	// This is a void method used for configure the scene.	 
	protected abstract void viewSetup();
	
	// This method Destroy the current scene to show another scene  
	public void destroyScene() {
		
		this.game = null;
		assert(this.game == null):("Game must be null to destroy the scene");
		
		this.keyboard = null;
		assert(this.keyboard == null):("Keyboard must be null to destory the scene");
	}
}