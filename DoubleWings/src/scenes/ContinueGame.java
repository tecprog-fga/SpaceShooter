/**
 * File: ContinueGame.java
 * Purpose: Build the continue game scene
 */

package scenes;
import java.util.Timer;

import constants.WindowConstants;
import jplay.InputBase;
import jplay.Keyboard;
import jplay.Parallax;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;

/**
 * This class build scene for continue games. It's necessary because 
 * this feature needs to show the movement scene in the screen.
 */
public class ContinueGame extends GameScene implements CountDownTimerEnds {
	
	//This void method was declarated in GameScene abstract class, it used for configurate the scene
	protected void viewSetup() {
		// TODO Auto-generated method stub
	}

	/**
	 * Sprite for build first screen of game's continue
	 */
	private Sprite continueScreen = null;
	
	/**
	 * This sprite build screen for countdown of game's continue
	 */
	private Sprite countdownScreen = null;
	
	/**
	 * This sprite display screen for "click enter"
	 */
	private Sprite enterScreen = null;

	/**
	 * This object display delpth scene
	 */
	private Parallax delpthScene = null;

	/**
	 * This object its necessary for transit the scene
	 */
	private Object transitScene;

	/**
	 * This object its necessary for transit to menu scene
	 */
	private Object menu;	
	
	//This void method build sprites for show scenes of class ContinueGame. 
	//It's necessary for build the initial sprites for universe, continue, count down and enter images.
	public void buildInitialScene() {

		assert(this.keyboard != null):("Null returned, keyboard cant set value");
		//It's necessary set behavior this buttons for to go initial scene
		this.keyboard.setBehavior(Keyboard.ENTER_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		this.keyboard.setBehavior(Keyboard.ESCAPE_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);

		
		//Creation a object to class Parallax
		this.delpthScene = new Parallax();
		
		//The first one added will be the last one to be painted and the last to be added to the list, it will be the main layer. 
		final String DELPTH_BACKGROUND_PATH = "src/assets/img/temp_background.png";
		
		assert(this.delpthScene != null):("Null returned, delpthScene cant add value");
		this.delpthScene.add(DELPTH_BACKGROUND_PATH);
		final String DELPTH_UNIVERSE1_PATH = "src/assets/img/universe1.png";
		this.delpthScene.add(DELPTH_UNIVERSE1_PATH);
		final String DELPTH_UNIVERSE2_PATH = "src/assets/img/universe2.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE2_PATH);
	    final String DELPTH_UNIVERSE3_PATH = "src/assets/img/universe3.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE3_PATH);
	    final String DELPTH_UNIVERSE4_PATH = "src/assets/img/universe4.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE4_PATH);
		
		//...Adjusts the speed of all layers from the main layer
		this.delpthScene.setVelAllLayers(0, 1);
		
		
		//Realize the position of images in continue screen, continue sprite upper-center position.
	    final String CONTINUE_PATH = "src/assets/img/continue/continue.png";
		this.continueScreen = new Sprite(CONTINUE_PATH);
		
		assert(this.continueScreen != null):("Null returned, continueScreen cant marked Window constants");
		this.continueScreen.x = defineAxis(WindowConstants.WIDTH, 2, this.continueScreen, 2);
		this.continueScreen.y = defineAxis(WindowConstants.HEIGHT, 2, this.continueScreen, 1);
		
		//Realize the position of images in countDown screen.
		final String COUNTDOWN_PATH = "src/assets/img/continue/number_9.png";
		this.countdownScreen = new Sprite(COUNTDOWN_PATH);

		assert(this.countdownScreen != null):("Null returned, countdownScreen cant marked Window constants");
	
		this.countdownScreen.x = defineAxis(WindowConstants.WIDTH, 2, this.countdownScreen, 2);
		this.countdownScreen.y = defineAxis(WindowConstants.HEIGHT, 1.5, this.countdownScreen, 2);
		
		//Realize the position of enter sprite displays screen.
	    final String ENTER_PATH = "src/assets/img/continue/Enter-Download-PNG.png";	 
		this.enterScreen = new Sprite(ENTER_PATH);
		
		assert(this.enterScreen != null):("Null returned, enterScreen cant marked Window constants");
		this.enterScreen.x = defineAxis(WindowConstants.WIDTH, 2, this.enterScreen, 2);
		this.enterScreen.y = defineAxis(WindowConstants.HEIGHT, 500, this.enterScreen, 20);

		//Finally, build scene of count down
		buildWaitScene();
	}
	
	private static double defineAxis(int window, double windowDividend, Sprite sprite, double screenDividend) {
		double axis;
		axis = 0;
		if (window == WindowConstants.WIDTH) {
			axis = window/windowDividend - sprite.width/screenDividend;
		}
		else {
			axis = window/windowDividend - sprite.height/screenDividend;
		}
		return axis;
	}

	/**
	 * This method build the scene of waiting countdown.
	 */
	private void buildWaitScene(){

		//This object is instance of Timer class, this object work with configuration of time of game.
		Timer timer = null; 
		timer = new Timer();
		assert(timer != null):("Null returned, timer dont should be null");
		
		//This object is instance of CountDown class, realize count down instruments in timer configuration.
		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		assert(countDown != null):("Null returned, countDown dont should be null");
		
		//Delegate action for count down to execute
		countDown.delegateAction = this;

		//Define the time in milliseconds.
		final int DELAY = 1000; 
		
		//Realize count with count down and delay of 1000 milliseconds
		timer.scheduleAtFixedRate(countDown, DELAY, DELAY);
	}

	//Build the update image of sprite on screen for repeat layers
	public void updateScene() {

		assert(this.delpthScene != null):("Null returned, delpthScene dont should be null");
		
		//Print all layers that have been added
		this.delpthScene.drawLayers();
		
		//This constant define the value of pixels most bellow of screen. Unid: Pixels
		final int PIXELS_DOWN = 800; 
		
		// This constant define the value of pixels side of screen. Unid: Pixels
		final int PIXELS_SIDES = 600;
		
		// The method below is responsible for maintaining infinite repetition of the layers.
		this.delpthScene.repeatLayers(PIXELS_DOWN, PIXELS_SIDES, false);

		//Move the parallax orientation vertically
		this.delpthScene.moveLayersStandardY(false);

		//Draw on screen with the continue, count down and enter images.
		this.continueScreen.draw();
		this.countdownScreen.draw();
		this.enterScreen.draw();

		//Check button for decision of transit screen
		checkButtonSelection();
	}
	
	//Build final scene for transit on Game over
	public void finishScene() {
		
		if (this.game != null) {
			
			assert(this.game != null):("Null returned, delpthScene dont should be null");
			
			//Message for show witch time ended.
			final String MSG_TIMER = "Timer Ended";
			System.out.println(MSG_TIMER);

			GameScene gameOver = null;
			gameOver = new GameOver();
			assert(gameOver != null):("Null returned, gameOver cant transit to new scene");
			
			this.game.transitTo(gameOver);
		}
		else {
			//Nothing to do
		}
	}

	//Given index update the image and build scene.
	public void updateImageForIndex(int index) {
		
		//This constant define the value of path where is the load image for continue
		final String LOAD_IMAGE_PATH = "src/assets/img/continue/number_";
		
		//This constant define the final path of LOAD_IMAGE_PATH
		final String PNG_EXTENSION = ".png";
		
		assert(this.countdownScreen != null):("Null returned, countdownScreen cant loadImage");
		this.countdownScreen.loadImage(LOAD_IMAGE_PATH + String.valueOf(index) + PNG_EXTENSION); 
	}
	
	/**
	 * This method verify the click of keyboard.
	 */
	private void checkButtonSelection () {

		//This decision structure is necessary for check the button selected. 
		//If the game execute and keyboard is clicked then execute the algorithm
		if(this.game != null && this.keyboard != null){

			assert(this.game != null):("Null returned, game cant check button selection");
			assert(this.keyboard != null):("Null returned, keyboard cant check button selection");
			
			//If selected enter key then transit to a continue state of  the game,
			//Else, but if selected escape key then transit to a menu scene
			if (this.keyboard.keyDown(Keyboard.ENTER_KEY)) {
				MenuScene transitScene = null;
				transitScene = new MenuScene();
				assert(this.transitScene == null):("This object should be null");
				this.game.transitTo(transitScene.firstStageScene());

			} 
			else if (this.keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				GameScene menu = null;
				menu = new MenuScene();
				assert(this.menu == null):("This object should be null");
				this.game.transitTo(menu);
			}
			else {
				//Nothing to do
			}
		}
		else {
			//Nothing to do
		}
	}

}