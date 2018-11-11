/**
 * File: ErrorScene.java
 * Purpose: Build the error scene
 */
package scenes;

import constants.WindowConstants;
import jplay.GameImage;
import jplay.Sprite;
import util.SpritePosition;

/**
 * This class build scene for error scene. It's necessary because 
 * the game need the scene for show error message.
 */
public class ErrorScene extends GameScene{
	
	//This object is necessary for build the temporary background image
	private GameImage background = null;
	//This object is necessary for build the error image in screen
	private Sprite errorScene = null;
	
	/**
	 * This object its necessary for resolve position of sprites features
	 */
	private SpritePosition spos = new SpritePosition();
	
	//This void method was declarated in GameScene abstract class, it used for configurated the scene
	public void buildInitialScene() {
		
		//Creation image background
		final String BACKGROUND_PATH = "src/assets/img/temp_background.png";
		this.background = new GameImage(BACKGROUND_PATH);

		//Creation image Game Over
		final String GAME_OVER_PATH = "src/assets/img/erro.png";
		this.errorScene = new Sprite(GAME_OVER_PATH);

		//Game over sprite center position
		this.errorScene.x = spos.calculatePosition(WindowConstants.WIDTH, 2, this.errorScene, 2);
		this.errorScene.y = spos.calculatePosition(WindowConstants.HEIGHT, 2, this.errorScene, 2);
	}
	
	//Build the update image of sprite on screen
	public void updateScene() {
		this.background.draw();
		this.errorScene.draw();
	}
	
	//This method return to main Menu
	public void finishScene() {
		//Nothing to do
	}
	
	//Given index update the image and build scene for this
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

	//This void method build all sprites for show scenes of class 
	protected void viewSetup() {
		// TODO Auto-generated method stub

	}

	/**
	 * This method build the scene of waiting countdown
	 */
	private void buildWaitScene(){
		//Nothing to do
	}
}