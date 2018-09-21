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

	public abstract void updateScene();
	
	protected GameController game = null;
	protected Keyboard keyboard = null;

	public void configureGameScene(GameController game) {
		
		this.game = game;
		this.keyboard = game.keyboard;
		final String MSG_KEYBOARD = "keyboard: "; //$NON-NLS-1$
		System.out.println(MSG_KEYBOARD + this.keyboard); 

		buildInitialScene();
		viewSetup();
	}
	protected abstract void buildInitialScene();
	protected abstract void viewSetup();

	public void destroyScene() {
		
		this.game = null;
		this.keyboard = null;
	}
}