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

	/*
	 * Transit to another scene
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

		//run initial setup
		scene.configureGameScene(this);
		
		/*
		 * Update current scene variable
		 */
		currentScene = scene;
	}
	
	private boolean isRunning = true;
	
	/*
	 *  Updates current scene and control running status
	 */
	public boolean update() {
		
		//Updates current scene
		if (currentScene != null){
			currentScene.updateScene();
		}
		return isRunning; 
	}
	
	/*
	 *  Quit game Ending process
	 */
	public void quit() {
		isRunning = false;
	}
	
	public GameScene currentScene = null;
	public Keyboard keyboard = null;

}