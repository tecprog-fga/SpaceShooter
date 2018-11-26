/*********************************************************
  * File: GameController.java
  * Purpose: GameController class implementation
  *********************************************************/

package game;

import scenes.GameScene;
import scenes.stages.stage1.FirstStage;

import org.apache.log4j.Logger;

import entity.Enemy;
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
	
	private static Logger logger = Logger.getLogger(GameController.class);

	//Initialize the game 
	private boolean isRunning = true;
	/**
	 * updates current scene and control running status
	 * @return isRunning
	 */
	public boolean update() {
		
		try {
			//Updates current scene
			assert(this.currentScene != null) : ("GameController is receiveing null");
			this.currentScene.updateScene();				
		}catch(NullPointerException e) {
			e.printStackTrace();
			logger.error("GameController is receiveing null", e);
		}
		return isRunning;
	
	}
	
	/**
	 * quit game ending process
	 */
	public void quit() {
		this.isRunning = false;
	}
}