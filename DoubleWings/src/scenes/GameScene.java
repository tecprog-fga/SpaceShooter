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
	
	/**
	 * This method Build the updated sprite image on the screen
	 */
	public abstract void updateScene();
	
	protected GameController game = null;
	protected Keyboard keyboard = null;
	
	/**
	 * This method Configure the controls in the keyboard and
	 * the scene to show on the screen
	 * @param game 
	 */
	public void configureGameScene(GameController game) {
		
		this.game = game;
		this.keyboard = game.keyboard;
		final String MSG_KEYBOARD = "keyboard: "; //$NON-NLS-1$
		System.out.println(MSG_KEYBOARD + this.keyboard); 

		buildInitialScene();
		viewSetup();
	}
	
	/**
	 * This method build all initial sprites for show scenes.
	 */
	protected abstract void buildInitialScene();
	
	/**
	 * This is a void method used for configure the scene.
	 */
	protected abstract void viewSetup();
	
	/**
	 * This method Destroy the current scene to show another scene 
	 */
	public void destroyScene() {
		
		this.game = null;
		this.keyboard = null;
	}
}