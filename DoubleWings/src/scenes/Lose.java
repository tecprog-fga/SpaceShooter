/**
 * File: Lose.java
 * Purpose: Build the lose game scene
 */

package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.Parallax;
import jplay.Sprite;
import util.CountDownTimer;
import util.CountDownTimerEnds;

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
	
	//This void method was declarated in GameScene abstract class, it used for configurated the scene
	public void buildInitialScene() {
		
		//Creation a object to class Parallax
        this.delpthScene = new Parallax();
        
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
		
		
		//Adjusts the speed of all layers from the main layer
		this.delpthScene.setVelAllLayers(0, 1);
		
		//Define scenes elements position for lose sprite upper-center position
		final String LOSE_PATH = "src/assets/img/continue/YOU-LOSE.png";
		this.loseScreen = new Sprite(LOSE_PATH);
		this.loseScreen.x = WindowConstants.WIDTH/2 - this.loseScreen.width/2;
		this.loseScreen.y = WindowConstants.HEIGHT/500 - this.loseScreen.height/20;
		
		
		//Check how many lives the player has to instantiate the specific sprite.
		//Depends of lives is show the sprite related
		if(getLifePlayer() == 1){
			final String ONE_LIFE_PATH = "src/assets/img/continue/1life.png";
			this.lifeRemaining = new Sprite(ONE_LIFE_PATH);
		}
		
		else if(getLifePlayer() == 2){
			final String TWO_LIFE_PATH = "src/assets/img/continue/2life.png";
			this.lifeRemaining = new Sprite(TWO_LIFE_PATH);
		}
		
		else if(getLifePlayer() == 3){
			final String THREE_LIFE_PATH = "src/assets/img/continue/3life.png";
			this.lifeRemaining = new Sprite(THREE_LIFE_PATH);
		}
		
		
		//Define position lifeRemaining on the Screen
		this.lifeRemaining.x = WindowConstants.WIDTH/2 - this.lifeRemaining.width/2;
		this.lifeRemaining.y = WindowConstants.HEIGHT/2 - this.lifeRemaining.height/2;
		
		//Finally, build scene of count down
		buildWaitScene();
	}
	
	//This void method build all sprites for show scenes of class 
	protected void viewSetup() {
		//Nothing to do
	}
	
	/**
	 * Method set created for using in other class to pass remaining life of the player
	 * @param lifePlayer
	 */
	public void setLifePlayer(int lifePlayer) {
		 this.lifePlayer = lifePlayer;
	}

	/**
	 * Get the value of player's life
	 * @return  value of player's life as number
	 */
	public int getLifePlayer() {
		 return this.lifePlayer;
	}
	
	/**
	 * This method build the scene of waiting countdown
	 */
	public void buildWaitScene() {
		//This object is necessary for schedule at fixed rate.
		Timer timer = new Timer();
		
		//This object is necessary for delegate action for count down.
		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		countDown.delegateAction = this;
		
		//This constant is value of time for delay on schedule. Unit of measure: Miliseconds
		long DELAY = 1000;
		
		timer.scheduleAtFixedRate(countDown, DELAY, DELAY);
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
		
		this.loseScreen.draw();		
		this.lifeRemaining.draw();
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
			
			this.game.transitTo(classicContinue);
		}
	}
	
	//Nothing to do in this class
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}