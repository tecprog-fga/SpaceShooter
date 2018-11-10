/**
 * File: Lose.java
 * Purpose: Build the lose game scene
 */

package scenes;

import java.util.Timer;

import org.apache.log4j.Logger;

import constants.WindowConstants;
import jplay.Parallax;
import jplay.Sprite;
import util.CountDownTimer;
import util.CountDownTimerEnds;
import util.SpritePosition;

/**
 * This class build scene for lose player using sprites. It's necessary because 
 * this feature needs to show the movement scene in the screen.
 */
public class Lose extends GameScene implements CountDownTimerEnds {
	
	/**
	 * This object represent the building delpth scene screen
	 */
	private Parallax delpthScene = null;
	
	/**
	 * This object build sprite for life remaining
	 */
	private Sprite lifeRemaining = null;
	
	/**
	 * This object build sprite for lose screen
	 */
	private Sprite loseScreen = null;
	
	/**
	 * Variable to check how many lives the player has remain
	 */
	private int lifePlayer;
	
	/**
	 * This object its necessary for resolve position of sprites features
	 */
	private SpritePosition spos = new SpritePosition();
	boolean errorOccurred = false;
	final static Logger logger = Logger.getLogger(Lose.class);
	
	//This void method was declarated in GameScene abstract class, it used for configurated the scene
	public void buildInitialScene() {
		
		//Creation a object to class Parallax
        this.delpthScene = new Parallax();
        try {
        	assert(this.delpthScene != null);
        	//The first one added will be the last one to be painted and the last to be added to the list, it will be the main layer. 
        	final String DELPTH_BACKGROUND_PATH = "src/assets/img/temp_background.png";
        	this.delpthScene.add(DELPTH_BACKGROUND_PATH);
        	this.delpthScene.add(DELPTH_BACKGROUND_PATH);
        	final String DELPTH_UNIVERSE1_PATH = "src/assets/img/universe1.png";
        	this.delpthScene.add(DELPTH_UNIVERSE1_PATH);
        	final String DELPTH_UNIVERSE2_PATH = "src/assets/img/universe2.jpg";
        	this.delpthScene.add(DELPTH_UNIVERSE2_PATH);
        	final String DELPTH_UNIVERSE3_PATH = "src/assets/img/universe3.jpg";
        	this.delpthScene.add(DELPTH_UNIVERSE3_PATH);
        	final String DELPTH_UNIVERSE4_PATH = "src/assets/img/universe4.jpg";
        	this.delpthScene.add(DELPTH_UNIVERSE4_PATH);	
        }
        catch(NullPointerException exception) {
        	logger.error("Error instantiating Parallax class", exception);
			exception.printStackTrace();
			errorOccurred = true;
        }
		
		
		//Adjusts the speed of all layers from the main layer
		this.delpthScene.setVelAllLayers(0, 1);
		
		//Define scenes elements position for lose sprite upper-center position
		final String LOSE_PATH = "src/assets/img/continue/YOU-LOSE.png";
		this.loseScreen = new Sprite(LOSE_PATH);
		try {
			assert(this.loseScreen != null);
			this.loseScreen.x = spos.calculatePosition(WindowConstants.WIDTH, 2, this.loseScreen, 2);
			this.loseScreen.y = spos.calculatePosition(WindowConstants.HEIGHT, 500, this.loseScreen, 20);	
		}
		catch(NullPointerException exception) {
			logger.error("Error instantiating Sprite class", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		
		
		//Check how many lives the player has to instantiate the specific sprite.
		//Depends of lives is show the sprite related
		switch(getLifePlayer()){

			case 1:
				final String ONE_LIFE_PATH = "src/assets/img/continue/1life.png";
				try {
					assert(this.lifeRemaining != null):("Error instantiating Sprite class");
					this.lifeRemaining = new Sprite(ONE_LIFE_PATH);	
				}
				catch(NullPointerException exception) {
					logger.error("Error instantiating Sprite class", exception);
					exception.printStackTrace();
					errorOccurred = true;
				}				
				break;
			case 2:
				final String TWO_LIFE_PATH = "src/assets/img/continue/2life.png";
				try {
					assert(this.lifeRemaining != null):("Error instantiating Sprite class");
					this.lifeRemaining = new Sprite(TWO_LIFE_PATH);
				}
				catch(NullPointerException exception) {
					logger.error("Error instantiating Sprite class", exception);
					exception.printStackTrace();
					errorOccurred = true;
				}
				break;
			case 3:
				final String THREE_LIFE_PATH = "src/assets/img/continue/3life.png";
				try {
					assert(this.lifeRemaining != null):("Error instantiating Sprite class");
					this.lifeRemaining = new Sprite(THREE_LIFE_PATH);
				}
				catch(NullPointerException exception) {
					logger.error("Error instantiating Sprite class", exception);
					exception.printStackTrace();
					errorOccurred = true;
				}
				break;
			default:
				//Nothing to do
		}		
		
		
		
		try {
			assert(this.lifeRemaining != null);
			//Define position lifeRemaining on the Screen
			this.lifeRemaining.x = spos.calculatePosition(WindowConstants.WIDTH, 2, this.lifeRemaining, 2);
			this.lifeRemaining.y = spos.calculatePosition(WindowConstants.HEIGHT, 2, this.lifeRemaining, 2);
			
		}
		catch(NullPointerException exception) {
			logger.error("Error instantiating Sprite class", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		
		//Finally, build scene of count down
		buildWaitScene();
	}
	
	private void transitErrorScene() {
		GameScene errorScene = new ErrorScene();
		this.game.transitTo(errorScene);		
	}

	//This void method build all sprites for show scenes of class 
	protected void viewSetup() {
		//Nothing to do
	}
	
	/**
	 * Method set created for using in other class to pass remaining life of the player
	 * @param lifePlayer
	 */
	private void setLifePlayer(int lifePlayer) {
		 this.lifePlayer = lifePlayer;
	}

	/**
	 * Get the value of player's life
	 * @return  value of player's life as number
	 */
	private int getLifePlayer() {
		try {
			assert(this.lifePlayer >= 0);			
			assert(this.lifePlayer <= 3);
		}
		catch(IllegalArgumentException exception) {
			logger.error("The life player is invalid value", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		return this.lifePlayer;
	}
	
	/**
	 * This method build the scene of waiting countdown
	 */
	private void buildWaitScene() {
		//This object is necessary for schedule at fixed rate.
		Timer timer = new Timer();
		
		//This object is necessary for delegate action for count down.
		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		try {
			assert(countDown != null);
			countDown.delegateAction = this;
		}
		catch(NullPointerException exception) {
			logger.error("Error instantiating CountDownTimer class", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		//This constant is value of time for delay on schedule. Unit of measure: Miliseconds
		try {
			long DELAY = 1000; 
			timer.scheduleAtFixedRate(countDown, DELAY, DELAY);			
		}
		catch(NullPointerException exception) {
			logger.error("The life player is invalid value", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
	
	//Build the update image of sprite on screen
	public void updateScene() {		
		//Print all layers that have been added
		this.delpthScene.drawLayers();
		
		//This constants is responsible for store of pixels value of screen. Unit of measure: Pixels
		final int PIXELS_DOWN = 800; 
		final int PIXELS_SIDES = 600;
		
		//Responsible for maintaining infinite repetition of the layers
		this.delpthScene.repeatLayers(PIXELS_DOWN, PIXELS_SIDES, false);
		
		//Move the parallax orientation vertically
		this.delpthScene.moveLayersStandardY(false);
		if(!errorOccurred) {
			try {
				this.loseScreen.draw();		
				this.lifeRemaining.draw();
			}
			catch(NullPointerException exception) {
				logger.error("The sprite returned null", exception);
				exception.printStackTrace();
				errorOccurred = true;
			}
		}
		else {
			transitErrorScene();
		}
	}
	
	//Method that after the end of the time transit to the screen to ContinueGame class
	public void finishScene() {
		
		//Case the game is run
		if (this.game != null){
			//Show on console the message when the time is ended
			final String END_TIMER = "Timer Ended";
			System.out.println(END_TIMER);
			
			//This object build a classic continue screen for transit to this element
			ContinueGame classicContinue = null;
			classicContinue = new ContinueGame();
			try {
				this.game.transitTo(classicContinue);				
			}
			catch(NullPointerException exception) {
				logger.error("The sprite returned null", exception);
				exception.printStackTrace();
				errorOccurred = true;
			}
		}
		else {
			//Nothing to do
		}
		
	}
	
	//Nothing to do in this class
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}