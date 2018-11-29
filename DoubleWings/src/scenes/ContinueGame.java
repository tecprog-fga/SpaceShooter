/**
 * File: ContinueGame.java
 * Purpose: Build the continue game scene
 */

package scenes;
import java.util.Timer;

import org.apache.log4j.Logger;

import constants.WindowConstants;
import jplay.InputBase;
import jplay.Keyboard;
import jplay.Parallax;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;
import util.SpritePosition;

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
	
	/**
	 * This object its necessary for resolve position of sprites features
	 */
	private SpritePosition spos = new SpritePosition();
	
	boolean errorOccurred = false;
	
	final static Logger logger = Logger.getLogger(ContinueGame.class);
	
	//This void method build sprites for show scenes of class ContinueGame. 
	//It's necessary for build the initial sprites for universe, continue, count down and enter images.
	public void buildInitialScene() {
		setBehaviorKeyboard();
		
		//Creation a object to class Parallax
		this.delpthScene = new Parallax();
		
		addUniverseParallax();
		
		//...Adjusts the speed of all layers from the main layer
		final double velMainLayerX = 0;
		final double velMainLayerY = 1;
		this.delpthScene.setVelAllLayers(velMainLayerX, velMainLayerY);
		logger.debug("The parallax was set with " + velMainLayerX + " in x axys and " + velMainLayerY+ " in y axys");
		
		//Realize the position of images in continue screen, continue sprite upper-center position.
	    final String CONTINUE_PATH = "src/assets/img/continue/continue.png";
		this.continueScreen = new Sprite(CONTINUE_PATH);
		logger.debug("The sprite was created with path: " + CONTINUE_PATH);
		
		calculateContinueScreenSpritePosition();
		
		//Realize the position of images in countDown screen.
		final String COUNTDOWN_PATH = "src/assets/img/continue/number_9.png";
		this.countdownScreen = new Sprite(COUNTDOWN_PATH);
		logger.debug("The sprite was created with path: " + COUNTDOWN_PATH);
		
		calculateCountDownScreenPosition();
		
		//Realize the position of enter sprite displays screen.
	    final String ENTER_PATH = "src/assets/img/continue/Enter-Download-PNG.png";	 
		this.enterScreen = new Sprite(ENTER_PATH);
		logger.debug("The sprite was created with path: " + ENTER_PATH);
		
		calculateEnterScreenPosition();

		//Finally, build scene of count down
		buildWaitScene();
	}

	private void calculateEnterScreenPosition() {
		try {			
			assert(this.enterScreen != null);
			this.enterScreen.x = spos.calculatePosition(WindowConstants.WIDTH, 2, this.enterScreen, 2);
			this.enterScreen.y = spos.calculatePosition(WindowConstants.HEIGHT, 500, this.enterScreen, 20);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, enterScreen cant marked Window constants", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	private void calculateCountDownScreenPosition() {
		try {
			assert(this.countdownScreen != null);
			this.countdownScreen.x = spos.calculatePosition(WindowConstants.WIDTH, 2, this.countdownScreen, 2);
			this.countdownScreen.y = spos.calculatePosition(WindowConstants.HEIGHT, 1.5, this.countdownScreen, 2);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, continueScreen cant marked Window constants", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	private void calculateContinueScreenSpritePosition() {
		try {
			assert(this.continueScreen != null);
			this.continueScreen.x = spos.calculatePosition(WindowConstants.WIDTH, 2, this.continueScreen, 2);
			this.continueScreen.y = spos.calculatePosition(WindowConstants.HEIGHT, 2, this.continueScreen, 1);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, continueScreen cant marked Window constants", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	//The first one added will be the last one to be painted and the last to be added to the list, it will be the main layer.
	private void addUniverseParallax() {
		final String DELPTH_BACKGROUND_PATH = "src/assets/img/temp_background.png";
		
		try {
			assert(this.delpthScene != null);
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
			logger.error("Null returned, delpthScene cant add value", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	private void setBehaviorKeyboard() {
		try{
			assert(this.keyboard != null);
			//It's necessary set behavior this buttons for to go initial scene
			this.keyboard.setBehavior(Keyboard.ENTER_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
			this.keyboard.setBehavior(Keyboard.ESCAPE_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		}
		catch(NullPointerException exception){
			logger.error("Null returned, keyboard can not set value", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	//Build the update image of sprite on screen for repeat layers
	public void updateScene() {

		try {
			assert(this.delpthScene != null);
			//Print all layers that have been added
			this.delpthScene.drawLayers();
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, delpthScene dont should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}	
		
		
		//This constant define the value of pixels most bellow of screen. Unid: Pixels
		final int PIXELS_DOWN = 800; 
		
		// This constant define the value of pixels side of screen. Unid: Pixels
		final int PIXELS_SIDES = 600;
		
		// The method below is responsible for maintaining infinite repetition of the layers.
		this.delpthScene.repeatLayers(PIXELS_DOWN, PIXELS_SIDES, false);
		logger.debug("The parallax was set with " + PIXELS_DOWN + "x" + PIXELS_SIDES + " pixels");

		//Move the parallax orientation vertically
		this.delpthScene.moveLayersStandardY(false);
		if(!errorOccurred) {
			//Draw on screen with the continue, count down and enter images.
			this.continueScreen.draw();
			this.countdownScreen.draw();
			this.enterScreen.draw();
		}
		else {
			transitErrorScene();
		}
		//Check button for decision of transit screen
		checkButtonSelection();
	}
	
	//Build final scene for transit on Game over
	public void finishScene() {
		
		if (this.game != null) {
			//Message for show witch time ended.
			final String MSG_TIMER = "Timer Ended";
			logger.debug(MSG_TIMER);

			GameScene gameOver = null;
			gameOver = new GameOver();
			try {
				assert(gameOver != null):("Null returned, gameOver cant transit to new scene");
				this.game.transitTo(gameOver);
			}
			catch(NullPointerException exception) {
				logger.error("Null returned, delpthScene dont should be null", exception);
				exception.printStackTrace();
				errorOccurred = true;
			}
			
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
		
		startCountDown(index, LOAD_IMAGE_PATH, PNG_EXTENSION);
	}

	private void startCountDown(int index, final String LOAD_IMAGE_PATH, final String PNG_EXTENSION) {
		try {
			assert(this.countdownScreen != null);
			this.countdownScreen.loadImage(LOAD_IMAGE_PATH + String.valueOf(index) + PNG_EXTENSION); 
			logger.info("The count down was started");
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, countdownScreen cant loadImage", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
	
	//Transit for error scene when fire an exception 
	public void transitErrorScene() {
		GameScene errorScene = new ErrorScene();
		this.game.transitTo(errorScene);
	}
	
	/**
	 * This method verify the click of keyboard.
	 */
	private void checkButtonSelection () {

		//This decision structure is necessary for check the button selected. 
		//If the game execute and keyboard is clicked then execute the algorithm
		if(this.game != null && this.keyboard != null){
					
			//If selected enter key then transit to a continue state of  the game,
			//Else, but if selected escape key then transit to a menu scene
			if (this.keyboard.keyDown(Keyboard.ENTER_KEY)) {
				logger.info("The state of the game and the keyboard is ok");
				logger.info("Enter key was pressed");
				
				MenuScene transitScene = null;
				transitScene = new MenuScene();
				
				transitSceneToFirstStage(transitScene);	
			} 
			else if (this.keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				
				logger.info("Scape key was pressed");
				
				GameScene menu = null;
				menu = new MenuScene();
				
				transitSceneToMenu(menu);	
			}
			else {
				//Nothing to do
			}
		}
		else {
			//Nothing to do
		}
	}

	private void transitSceneToMenu(GameScene menu) {
		try {
			assert(this.menu == null);
			this.game.transitTo(menu);
		}
		catch(NullPointerException exception) {
			logger.error("This object should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	private void transitSceneToFirstStage(MenuScene transitScene) {
		try {
			assert(this.transitScene == null):("This object should be null");
			this.game.transitTo(transitScene.firstStageScene());
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, delpthScene dont should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
	
	/**
	 * This method build the scene of waiting countdown.
	 */
	private void buildWaitScene(){

		//This object is instance of Timer class, this object work with configuration of time of game.
		Timer timer = null; 
		timer = new Timer();
		
		//This object is instance of CountDown class, realize count down instruments in timer configuration.
		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		
		delegateCountDownAction(countDown);

		//Define the time in milliseconds.
		final int DELAY = 1000; 
		
		applyDelay(timer, countDown, DELAY);		
	}

	private void applyDelay(Timer timer, CountDownTimer countDown, final int DELAY) {
		//Realize count with count down and delay of 1000 milliseconds
		try {
			assert(timer != null);
			timer.scheduleAtFixedRate(countDown, DELAY, DELAY);
			logger.debug("The timer was set with delay: " + DELAY);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, timer dont should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	private void delegateCountDownAction(CountDownTimer countDown) {
		try {			
			assert(countDown != null);
			//Delegate action for count down to execute
			countDown.delegateAction = this;
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, countDown dont should be null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
}