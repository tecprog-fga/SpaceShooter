/*********************************************************
  * File: GameController.java
  * Purpose: GameController class implementation
  *********************************************************/

package game;

import scenes.GameScene;
import scenes.stages.stage1.FirstStage;
import jplay.Keyboard;

/**
* This class initializes game control
*/
public class GameController {

	/**
	 * transit to another scene
	 * @param scene
	 */
	public void transitTo(GameScene scene) {	
		
		/**
		 * Leave transition if scene is null
		 */
		if (scene == null || keyboard == null) { 
			return;
		} 
		
		else if (currentScene != null) {
			currentScene.destroyScene();
		}

		//Run initial setup
		scene.configureGameScene(this);
		
		//Update current scene variable
		currentScene = scene;
	}
	
	/**
	 * initialize the game 
	 */
	private boolean isRunning = true;
	/**
	 * updates current scene and control running status
	 * @return isRunning
	 */
	public boolean update() {
		
		/**
		 * updates current scene
		 */
		assert(currentScene != null): ("Error currentScene");
		currentScene.updateScene();
		return isRunning; 
	}
	
	/**
	 * quit game ending process
	 */
	public void quit() {
		isRunning = false;
	}
	
	public GameScene currentScene = null;
	public Keyboard keyboard = null;
}