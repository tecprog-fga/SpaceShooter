/**
 * File: GameOver.java
 * Purpose: Build the game over game scene
 */
package scenes;

import java.util.Timer;

import org.apache.log4j.Logger;

import constants.WindowConstants;
import jplay.GameImage;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;
import util.SpritePosition;

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
	
	/**
	 * This object its necessary for resolve position of sprites features
	 */
	private SpritePosition spos = new SpritePosition();
	boolean errorOccurred = false;
	final static Logger logger = Logger.getLogger(GameOver.class);
	
	//This void method was declarated in GameScene abstract class, it used for configurated the scene
	public void buildInitialScene() {

		//Set game controller elements
		try {
			assert(this.keyboard != null):("Null returned, keyboard cant set value");
			this.keyboard = this.game.keyboard;
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, keyboard can not set value", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		
		//Creation image background
		final String BACKGROUND_PATH = "src/assets/img/temp_background.png";
		this.background = new GameImage(BACKGROUND_PATH);
		logger.debug("The sprite was created with path: " + BACKGROUND_PATH);
		
		//Creation image Game Over
		final String GAME_OVER_PATH = "src/assets/img/continue/3540295891_logo.jpg";
		this.gameOver = new Sprite(GAME_OVER_PATH);
		logger.debug("The sprite was created with path: " + GAME_OVER_PATH);
		logger.info("The game is over");
		
		try {
			assert(this.gameOver != null);
			//Game over sprite center position
			this.gameOver.x = spos.calculatePosition(WindowConstants.WIDTH, 2, this.gameOver, 2);
			this.gameOver.y = spos.calculatePosition(WindowConstants.HEIGHT, 2, this.gameOver, 2);	
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, gameOver dont should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		buildWaitScene();
	}

	//Build the update image of sprite on screen
	public void updateScene() {
		if(!errorOccurred) {
			try {
				this.background.draw();
				this.gameOver.draw();
			}
			catch(NullPointerException exception) {
				logger.error("Null returned, sprite dont should be null", exception);
				exception.printStackTrace();
				errorOccurred = true;
			}
		}
		else {
			transitErrorScene();
		}
	}

	//This method return to main Menu
	public void finishScene() {
		//Show on console the message when the time is ended
		final String MSG_TIMER = "Timer Ended";
		logger.info(MSG_TIMER);

		//This object build a menu scene for transit to menu
		GameScene menu = null;
		menu = new MenuScene();
		try {
			assert(this.menu != null);
			this.game.transitTo(menu);	
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, menu dont should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
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
		
		//This object is necessary for schedule at fixed rate.
		Timer timer = null;
		timer = new Timer();

		//This object is necessary for delegate action for count down.
		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		//This constant is value of time for delay on schedule. Unit of measure: Miliseconds
		final int DELAY = 1000;
		try {
			assert(countDown != null);
			//Delegate action for count down to execute
			countDown.delegateAction = this;
			timer.scheduleAtFixedRate(countDown, DELAY, DELAY);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, countDown dont should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
	
	private void transitErrorScene() {
		GameScene errorScene = new ErrorScene();
		this.game.transitTo(errorScene);
	}
}