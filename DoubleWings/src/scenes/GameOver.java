package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.GameImage;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;

public class GameOver extends GameScene implements CountDownTimerEnds {

	// Sprite on scene
	private GameImage background;
	private Sprite gameOver;

	public void initialSetup() {

		//Set game controller elements
		this.keyboard = this.game.keyboard;

		//Creation image background
		this.background = new GameImage("src/assets/img/temp_background.png"); //$NON-NLS-1$

		//Creation image Game Over
		this.gameOver = new Sprite("src/assets/img/continue/3540295891_logo.jpg"); //$NON-NLS-1$
		//Game over sprite center position
		this.gameOver.x = WindowConstants.WIDTH/2 - this.gameOver.width/2;
		this.gameOver.y = WindowConstants.HEIGHT/2 - this.gameOver.height/2;

		timeWait();
	}

	@Override
	protected void viewSetup() {
		// TODO Auto-generated method stub

	}

	//Time wait to transit between scene
	public void timeWait(){
		Timer timer = new Timer();
		CountDownTimer countDownn = new CountDownTimer();
		countDownn.delegate = this;
		long delay = 1000;
		timer.scheduleAtFixedRate(countDownn, delay, delay);
	}

	//Update image Sprite on Screen
	public void update() {

		this.background.draw();
		this.gameOver.draw();

	}

	//Method to return to main Menu
	@Override
	public void terminate() {
		System.out.println("Timer Ended"); //$NON-NLS-1$
		GameScene menu = new MenuScene();
		this.game.transitTo(menu);
	}

	@Override
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}
