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
		
	private Sprite wantToContinue;
	private Sprite counter;
	private Sprite enter;
	private Parallax parallax;       //Creation variable to instance a new parallax
	
	//Thread counter
	//static Thread thread = new Thread(); 

	@Override
	protected void viewSetup() {
		// TODO Auto-generated method stub
	}
	
	public void initialSetup() {
		
		//Configure enter key and escape
		this.keyboard.setBehavior(Keyboard.ENTER_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		this.keyboard.setBehavior(Keyboard.ESCAPE_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		
		//Create image background
		//background = new GameImage("src/assets/img/temp_background.png");
		
		//Creation a object to class Parallax
        this.parallax = new Parallax();
		
        //The first one added will be the last one to be painted.
		this.parallax.add("src/assets/img/temp_background.png"); //$NON-NLS-1$
		this.parallax.add("src/assets/img/universe1.png"); //$NON-NLS-1$
		this.parallax.add("src/assets/img/universe2.jpg"); //$NON-NLS-1$
		this.parallax.add("src/assets/img/universe3.jpg"); //$NON-NLS-1$
		//Since universe4.jpg was the last to be added to the list, it will be the main layer (mainLayer).
		this.parallax.add("src/assets/img/universe4.jpg"); //$NON-NLS-1$
		
		//Adjusts the speed of all layers from the main layer
		this.parallax.setVelAllLayers(0, 1);
		
		/*Define scenes elements position
		 *Continue sprite upper-center position*/
		this.wantToContinue = new Sprite("src/assets/img/continue/continue.png"); //$NON-NLS-1$
		this.wantToContinue.x = WindowConstants.WIDTH/2 - this.wantToContinue.width/2;
		this.wantToContinue.y = WindowConstants.HEIGHT/2 - this.wantToContinue.height;
		
		//Number sprite positions
		this.counter = new Sprite("src/assets/img/continue/number_9.png"); //$NON-NLS-1$
		this.counter.x = WindowConstants.WIDTH/2 - this.counter.width/2;
		this.counter.y = WindowConstants.HEIGHT/1.5 - this.counter.height/2;
		
		//Enter Sprite displays Screen
		this.enter = new Sprite("src/assets/img/continue/Enter-Download-PNG.png"); //$NON-NLS-1$
		this.enter.x = WindowConstants.WIDTH/2 - this.enter.width/2;
		this.enter.y = WindowConstants.HEIGHT/500 - this.enter.height/20;
		
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
		
		//background.draw();
		
		//Print all layers that have been added
        this.parallax.drawLayers();
		
        //The method below is responsible for maintaining infinite repetition of the layers.
		this.parallax.repeatLayers(800, 600, false);
		
		//Move the parallax orientation vertically
		this.parallax.moveLayersStandardY(false);
		
		this.wantToContinue.draw();
		this.counter.draw();
		this.enter.draw();
		
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
		this.counter.loadImage("src/assets/img/continue/number_" + String.valueOf(index) + ".png");  //$NON-NLS-1$//$NON-NLS-2$
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
