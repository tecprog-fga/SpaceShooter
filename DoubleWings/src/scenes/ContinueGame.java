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

public class ContinueGame extends GameScene implements CountDownTimerEnds {

	@Override
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
	 * This sprite build enter screen after the limit game's continue is done
	 */
	private Sprite enterScreen = null;

	/**
	 * This object represent the building delpth scene screen
	 */
	private Parallax delpthScene = null;	
	
	public void buildInitialScene() {

		//Configure enter key and escape
		this.keyboard.setBehavior(Keyboard.ENTER_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		this.keyboard.setBehavior(Keyboard.ESCAPE_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);

		//Creation a object to class Parallax
		this.delpthScene = new Parallax();
		final String DELPTH_BACKGROUND_PATH = "src/assets/img/temp_background.png";
		//The first one added will be the last one to be painted.
		this.delpthScene.add(DELPTH_BACKGROUND_PATH); 
		final String DELPTH_UNIVERSE1_PATH = "src/assets/img/universe1.png";
		this.delpthScene.add(DELPTH_UNIVERSE1_PATH); 
		final String DELPTH_UNIVERSE2_PATH = "src/assets/img/universe2.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE2_PATH); 
	    final String DELPTH_UNIVERSE3_PATH = "src/assets/img/universe3.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE3_PATH); 
		//Since universe4.jpg was the last to be added to the list, it will be the main layer (mainLayer).
	    final String DELPTH_UNIVERSE4_PATH = "src/assets/img/universe4.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE4_PATH);
		
		//Adjusts the speed of all layers from the main layer
		this.delpthScene.setVelAllLayers(0, 1);

		/*Define scenes elements position
		 *Continue sprite upper-center position*/
	    final String CONTINUE_PATH = "src/assets/img/continue/continue.png";
		this.continueScreen = new Sprite(CONTINUE_PATH);
		this.continueScreen.x = WindowConstants.WIDTH/2 - this.continueScreen.width/2;
		this.continueScreen.y = WindowConstants.HEIGHT/2 - this.continueScreen.height;

		//Number sprite positions
		final String COUNTDOWN_PATH = "src/assets/img/continue/number_9.png";
		this.countdownScreen = new Sprite(COUNTDOWN_PATH);
		this.countdownScreen.x = WindowConstants.WIDTH/2 - this.countdownScreen.width/2;
		this.countdownScreen.y = WindowConstants.HEIGHT/1.5 - this.countdownScreen.height/2;

		//Enter Sprite displays Screen
	    final String ENTER_PATH = "src/assets/img/continue/Enter-Download-PNG.png";	 //$NON-NLS-1$
		this.enterScreen = new Sprite(ENTER_PATH);
		this.enterScreen.x = WindowConstants.WIDTH/2 - this.enterScreen.width/2;
		this.enterScreen.y = WindowConstants.HEIGHT/500 - this.enterScreen.height/20;

		buildWaitScene();
	}

	// Time wait to transit between scene
	public void buildWaitScene(){

		Timer timer = null; 
		timer = new Timer();

		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		countDown.delegate = this;

		final int DELAY = 1000; 
		timer.scheduleAtFixedRate(countDown, DELAY, DELAY);

	}

	// Update image Sprite on Screen and check button at keyboard
	public void updateScene() {

		final int PIXELS_DOWN = 800; 
		final int PIXELS_SIDES = 600;

		//Print all layers that have been added
		this.delpthScene.drawLayers();

		//The method below is responsible for maintaining infinite repetition of the layers.
		this.delpthScene.repeatLayers(PIXELS_DOWN, PIXELS_SIDES, false);

		//Move the parallax orientation vertically
		this.delpthScene.moveLayersStandardY(false);

		this.continueScreen.draw();
		this.countdownScreen.draw();
		this.enterScreen.draw();

		checkButtonSelection();

	}

	// Method that after the end of the time transit to the screen of game over.
	@Override
	public void finishScene() {
		if (this.game != null){

			final String MSG_TIMER = "Timer Ended"; //$NON-NLS-1$
			System.out.println(MSG_TIMER);

			GameScene gameOver = null;
			gameOver = new GameOver();
			this.game.transitTo(gameOver);
		}
	}

	// Update number continue on Screen
	@Override
	public void updateImageForIndex(int index) {
				
		final String LOAD_IMAGE_PATH = "src/assets/img/continue/number_";
		final String PNG_EXTENSION = ".png"; //$NON-NLS-1$
		this.countdownScreen.loadImage(LOAD_IMAGE_PATH + String.valueOf(index) + PNG_EXTENSION); 
	}

	// Method to catch click on keyboard
	private void checkButtonSelection () {

		if(this.game != null && this.keyboard != null){
			if (this.keyboard.keyDown(Keyboard.ENTER_KEY)) {
				//Transit to a continue state of the game
				MenuScene transitScene = null;
				transitScene = new MenuScene();
				this.game.transitTo(transitScene.firstStageScene());

			} else if (this.keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				GameScene menu = null;
				menu = new MenuScene();
				this.game.transitTo(menu);
			}
		}
	}

}
