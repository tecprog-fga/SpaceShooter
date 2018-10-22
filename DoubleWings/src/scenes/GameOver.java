/**
 * File: GameOver.java
 * Purpose: Build the game over game scene
 */
package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.GameImage;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;

/**
 * This class build scene for game over. It's necessary because 
 * this feature needs to show the movement scene in the screen.
 */
public class GameOver extends GameScene implements CountDownTimerEnds {
	
	//This object is necessary for build the temporary background image
	private GameImage background = null;
	//This object is necessary for build the game over image in screen
	private Sprite gameOver = null;
	private Object menu;
	
	//This void method was declarated in GameScene abstract class, it used for configurated the scene
	public void buildInitialScene() {

		//Set game controller elements
		assert(this.keyboard != null):("Null returned, keyboard cant set value");
		this.keyboard = this.game.keyboard;
		
		//Creation image background
		final String BACKGROUND_PATH = "src/assets/img/temp_background.png";
		this.background = new GameImage(BACKGROUND_PATH);
		assert(this.background != null):("Null returned, background dont should be null");

		//Creation image Game Over
		final String GAME_OVER_PATH = "src/assets/img/continue/3540295891_logo.jpg";
		this.gameOver = new Sprite(GAME_OVER_PATH);
		assert(this.gameOver != null):("NNull returned, gameOver dont should be null");

		//Game over sprite center position
		this.gameOver.x = WindowConstants.WIDTH/2 - this.gameOver.width/2;
		this.gameOver.y = WindowConstants.HEIGHT/2 - this.gameOver.height/2;

		buildWaitScene();
	}

	//This void method build all sprites for show scenes of class 
	protected void viewSetup() {
		// TODO Auto-generated method stub

	}

	/**
	 * This method build the scene of waiting countdown
	 */
	private void buildWaitScene(){
		
		//This object is necessary for schedule at fixed rate.
		Timer timer = null;
		timer = new Timer();
		assert(timer != null):("Null returned, timer dont should be null");

		//This object is necessary for delegate action for count down.
		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		assert(countDown != null):("Null returned, countDown dont should be null");
		
		//Delegate action for count down to execute
		countDown.delegateAction = this;

		//This constant is value of time for delay on schedule. Unit of measure: Miliseconds
		final int DELAY = 1000;
		
		timer.scheduleAtFixedRate(countDown, DELAY, DELAY);
	}

	//Build the update image of sprite on screen
	public void updateScene() {
		assert(this.background != null):("Null returned, background dont should be null");
		assert(this.gameOver != null):("Null returned, gameOver dont should be null");
		
		this.background.draw();
		this.gameOver.draw();
	}

	//This method return to main Menu
	public void finishScene() {
		//Show on console the message when the time is ended
		final String MSG_TIMER = "Timer Ended";
		System.out.println(MSG_TIMER);

		//This object build a menu scene for transit to menu
		GameScene menu = null;
		menu = new MenuScene();
		assert(this.menu != null):("Null returned, menu dont should be null");
		this.game.transitTo(menu);
	}

	//Given index update the image and build scene for this
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}