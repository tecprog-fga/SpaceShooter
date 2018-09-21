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
		
		/*
		 * Leave transition if scene is null
		 */
		if (scene == null || keyboard == null) { 
		  return;
		} else if(currentScene != null){
			currentScene.destroyScene();
		}

		/*
		 * Run initial setup
		 */
		scene.configureGameScene(this);
		
		/*
		 * Update current scene variable
		 */
		currentScene = scene;
	}
	
	/**
	 * initialize the game 
	 */
	private boolean isRunning = true;
	
	/*
	 *  Updates current scene and control running status
	 */
	/**
	 * start the game
	 * @return isRunning
	 */
	public boolean update() {
		
		/*
		 * Updates current scene
		 */
		if (currentScene != null){
			currentScene.updateScene();
		}
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