package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.GameImage;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;

/**
 * This class build scene for game over. It's necessary because 
 * this feature needs to show the movement scene in the screen.
 */
public class GameOver extends GameScene implements CountDownTimerEnds {
	
	private GameImage background = null;
	private Sprite gameOver = null;
	
	/*
	 * This void method was declarated in GameScene abstract class, it used for configurated the scene
	 * (non-Javadoc)
	 * @see scenes.GameScene#buildInitialScene()
	 */
	public void buildInitialScene() {

		//Set game controller elements
		this.keyboard = this.game.keyboard;

		//Creation image background
		final String BACKGROUND_PATH = "src/assets/img/temp_background.png"; //$NON-NLS-1$
		this.background = new GameImage(BACKGROUND_PATH);

		//Creation image Game Over
		final String GAME_OVER_PATH = "src/assets/img/continue/3540295891_logo.jpg"; //$NON-NLS-1$
		this.gameOver = new Sprite(GAME_OVER_PATH);

		//Game over sprite center position
		this.gameOver.x = WindowConstants.WIDTH/2 - this.gameOver.width/2;
		this.gameOver.y = WindowConstants.HEIGHT/2 - this.gameOver.height/2;

		buildWaitScene();
	}

	/* 
	 * This void method build all sprites for show scenes of class 
	 * (non-Javadoc)
	 * @see scenes.GameScene#viewSetup()
	 */
	protected void viewSetup() {
		// TODO Auto-generated method stub

	}

	/**
	 * This method build the scene of waiting countdown
	 */
	public void buildWaitScene(){

		Timer timer = null;
		timer = new Timer();

		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		countDown.delegateAction = this;

		final int DELAY = 1000;
		timer.scheduleAtFixedRate(countDown, DELAY, DELAY);
	}

	/* 
	 * Build the update image of sprite on screen
	 * (non-Javadoc)
	 * @see scenes.GameScene#updateScene()
	 */
	public void updateScene() {
		this.background.draw();
		this.gameOver.draw();
	}

	/* 
	 * This method return to main Menu
	 * (non-Javadoc)
	 * @see util.CountDownTimerEnds#finishScene()
	 */
	public void finishScene() {
		final String MSG_TIMER = "Timer Ended"; //$NON-NLS-1$
		System.out.println(MSG_TIMER);

		GameScene menu = null;
		menu = new MenuScene();

		this.game.transitTo(menu);
	}

	/* 
	 * Given index update the image and build scene for this
	 * (non-Javadoc)
	 * @see util.CountDownTimerEnds#updateImageForIndex(int)
	 */
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}
