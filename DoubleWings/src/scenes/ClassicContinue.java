package scenes;
import java.util.Timer;

import constants.WindowConstants;
import jplay.GameImage;
import jplay.InputBase;
import jplay.Keyboard;
import jplay.Parallax;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;

public class ClassicContinue extends GameScene implements CountDownTimerEnds {
		
	// Sprite on scene
	private GameImage background;
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
		keyboard.setBehavior(Keyboard.ENTER_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.ESCAPE_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		
		//Create image background
		//background = new GameImage("src/assets/img/temp_background.png");
		
		//Creation a object to class Parallax
        parallax = new Parallax();
		
        //The first one added will be the last one to be painted.
		parallax.add("src/assets/img/temp_background.png");
		parallax.add("src/assets/img/universe1.png");
		parallax.add("src/assets/img/universe2.jpg");
		parallax.add("src/assets/img/universe3.jpg");
		//Since universe4.jpg was the last to be added to the list, it will be the main layer (mainLayer).
		parallax.add("src/assets/img/universe4.jpg");
		
		//Adjusts the speed of all layers from the main layer
		parallax.setVelAllLayers(0, 1);
		
		/*Define scenes elements position
		 *Continue sprite upper-center position*/
		wantToContinue = new Sprite("src/assets/img/continue/continue.png");
		wantToContinue.x = WindowConstants.WIDTH/2 - wantToContinue.width/2;
		wantToContinue.y = WindowConstants.HEIGHT/2 - wantToContinue.height;
		
		//Number sprite positions
		counter = new Sprite("src/assets/img/continue/number_9.png");
		counter.x = WindowConstants.WIDTH/2 - counter.width/2;
		counter.y = WindowConstants.HEIGHT/1.5 - counter.height/2;
		
		//Enter Sprite displays Screen
		enter = new Sprite("src/assets/img/continue/Enter-Download-PNG.png");
		enter.x = WindowConstants.WIDTH/2 - enter.width/2;
		enter.y = WindowConstants.HEIGHT/500 - enter.height/20;
		
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
        parallax.drawLayers();
		
        //The method below is responsible for maintaining infinite repetition of the layers.
		parallax.repeatLayers(800, 600, false);
		
		//Move the parallax orientation vertically
		parallax.moveLayersStandardY(false);
		
		wantToContinue.draw();
		counter.draw();
		enter.draw();
		
		checkButtonSelection();
		
	}
	
	//Method that after the end of the time transit to the screen of game over.
	@Override
	public void terminate() {
		if (game != null){
			System.out.println("Timer Ended");
			GameScene gameOver = new GameOver();
			game.transitTo(gameOver);
		}
	}
	
	//Update number continue on Screen
	@Override
	public void updateImageForIndex(int index) {
		counter.loadImage("src/assets/img/continue/number_" + String.valueOf(index) + ".png");
	}
	
	//Method to catch click on keyboard
	private void checkButtonSelection () {
		
		if(game != null && keyboard != null){
			if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
				//Transit to a continue state of the game
				MenuScene transitScene = new MenuScene();
				game.transitTo(transitScene.firstStage());
	
			} else if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				GameScene menu = new MenuScene();
				game.transitTo(menu);
			}
		}
	}
	
}
