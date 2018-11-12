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

	public GameScene currentScene = null;
	public Keyboard keyboard = null;
	/**
	 * transit to another scene
	 * @param scene
	 */
	public void transitTo(GameScene scene) {	
		
		/**
		 * Leave transition if scene is null
		 */
		if (scene == null || this.keyboard == null) { 
			return;
		} 
		
		else if (this.currentScene != null) {
			this.currentScene.destroyScene();
		}
		
		else {
			//Nothing to do
		}

		//Run initial setup
		scene.configureGameScene(this);
		
		//Update current scene variable
		this.currentScene = scene;
	}
	
	//initialize the game 
	private boolean isRunning = true;
	/**
	 * updates current scene and control running status
	 * @return isRunning
	 */
	public boolean update() {
		
		/**
		 * updates current scene
		 */
		assert(this.currentScene != null): ("GameController.0"); //$NON-NLS-1$
		this.currentScene.updateScene();
		return this.isRunning; 
	}
	
	/**
	 * quit game ending process
	 */
	public void quit() {
		this.isRunning = false;
	}
}