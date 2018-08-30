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

public class ClassicContinue extends GameScene implements CountDownTimerEnds {
		
	
	@Override
	protected void viewSetup() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Sprite for build first screen of game's continue
	 */
	private Sprite continueScreen; //T01 T05 T07
	/**
	 * This sprite build screen for countdown of game's continue
	 */
	private Sprite countdownScreen; //T01 T05 T07
	/**
	 * This sprite build enter screen after the limit game's continue is done
	 */
	private Sprite enterScreen; //T01 T05 T07
	
	/**
	 * This object represent the building delpth scene screen
	 */
	private Parallax delpthScene; //T01 T05 T07
	
	public void initialSetup() {
		
		//Configure enter key and escape
		this.keyboard.setBehavior(Keyboard.ENTER_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		this.keyboard.setBehavior(Keyboard.ESCAPE_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		
		//Creation a object to class Parallax
        this.delpthScene = new Parallax();
		
        //The first one added will be the last one to be painted.
		this.delpthScene.add("src/assets/img/temp_background.png"); //$NON-NLS-1$
		this.delpthScene.add("src/assets/img/universe1.png"); //$NON-NLS-1$
		this.delpthScene.add("src/assets/img/universe2.jpg"); //$NON-NLS-1$
		this.delpthScene.add("src/assets/img/universe3.jpg"); //$NON-NLS-1$
		//Since universe4.jpg was the last to be added to the list, it will be the main layer (mainLayer).
		this.delpthScene.add("src/assets/img/universe4.jpg"); //$NON-NLS-1$
		
		//Adjusts the speed of all layers from the main layer
		this.delpthScene.setVelAllLayers(0, 1);
		
		/*Define scenes elements position
		 *Continue sprite upper-center position*/
		this.continueScreen = new Sprite("src/assets/img/continue/continue.png"); //$NON-NLS-1$
		this.continueScreen.x = WindowConstants.WIDTH/2 - this.continueScreen.width/2;
		this.continueScreen.y = WindowConstants.HEIGHT/2 - this.continueScreen.height;
		
		//Number sprite positions
		this.countdownScreen = new Sprite("src/assets/img/continue/number_9.png"); //$NON-NLS-1$
		this.countdownScreen.x = WindowConstants.WIDTH/2 - this.countdownScreen.width/2;
		this.countdownScreen.y = WindowConstants.HEIGHT/1.5 - this.countdownScreen.height/2;
		
		//Enter Sprite displays Screen
		this.enterScreen = new Sprite("src/assets/img/continue/Enter-Download-PNG.png"); //$NON-NLS-1$
		this.enterScreen.x = WindowConstants.WIDTH/2 - this.enterScreen.width/2;
		this.enterScreen.y = WindowConstants.HEIGHT/500 - this.enterScreen.height/20;
		
		timeWait();
	}
	
	//Time wait to transit between scene
	public void timeWait(){
		
		Timer timer = new Timer();
		CountDownTimer countDownn = new CountDownTimer();
		countDownn.delegate = this;
		long delay = 1000;
		timer.scheduleAtFixedRate(countDownn, delay, delay);
	
	}
	
	//Update image Sprite on Screen and check button at keyboard
	public void update() {
		
		final int PIXELS_DOWN = 800; //T08
		final int PIXELS_UP = 600;
		
		//Print all layers that have been added
        this.delpthScene.drawLayers();
		
        //The method below is responsible for maintaining infinite repetition of the layers.
		this.delpthScene.repeatLayers(PIXELS_DOWN, PIXELS_UP, false);
		
		//Move the parallax orientation vertically
		this.delpthScene.moveLayersStandardY(false);
		
		this.continueScreen.draw();
		this.countdownScreen.draw();
		this.enterScreen.draw();
		
		checkButtonSelection();
		
	}
	
	//Method that after the end of the time transit to the screen of game over.
	@Override
	public void terminate() {
		if (this.game != null){
			System.out.println("Timer Ended"); //$NON-NLS-1$
			GameScene gameOver = new GameOver();
			this.game.transitTo(gameOver);
		}
	}
	
	//Update number continue on Screen
	@Override
	public void updateImageForIndex(int index) {
		this.countdownScreen.loadImage("src/assets/img/continue/number_" + String.valueOf(index) + ".png");  //$NON-NLS-1$//$NON-NLS-2$
	}
	
	//Method to catch click on keyboard
	private void checkButtonSelection () {
		
		if(this.game != null && this.keyboard != null){
			if (this.keyboard.keyDown(Keyboard.ENTER_KEY)) {
				//Transit to a continue state of the game
				MenuScene transitScene = new MenuScene();
				this.game.transitTo(transitScene.firstStage());
	
			} else if (this.keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				GameScene menu = new MenuScene();
				this.game.transitTo(menu);
			}
		}
	}
	
}
