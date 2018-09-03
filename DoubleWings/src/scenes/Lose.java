package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.Parallax;
import jplay.Sprite;
import util.CountDownTimer;
import util.CountDownTimerEnds;

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
	
	public void buildInitialScene() {
		
        this.delpthScene = new Parallax();
        final String DELPTH_BACKGROUND_PATH = "src/assets/img/temp_background.png";
        /* The first one added will be the last one to be painted.*/
		this.delpthScene.add(DELPTH_BACKGROUND_PATH);
		this.delpthScene.add(DELPTH_BACKGROUND_PATH);
		final String DELPTH_UNIVERSE1_PATH = "src/assets/img/universe1.png";
		this.delpthScene.add(DELPTH_UNIVERSE1_PATH);
		final String DELPTH_UNIVERSE2_PATH = "src/assets/img/universe2.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE2_PATH);
		final String DELPTH_UNIVERSE3_PATH = "src/assets/img/universe3.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE3_PATH);
		
		/* Since universe4.jpg was the last to be added to the list, 
		 * it will be the main layer (mainLayer).
		 */
		final String DELPTH_UNIVERSE4_PATH = "src/assets/img/universe4.jpg";
		this.delpthScene.add(DELPTH_UNIVERSE4_PATH);
		
		//Adjusts the speed of all layers from the main layer
		this.delpthScene.setVelAllLayers(0, 1);
		
		/*Define scenes elements position
		 *Continue sprite upper-center position*/
		final String LOSE_PATH = "src/assets/img/continue/YOU-LOSE.png";
		this.loseScreen = new Sprite(LOSE_PATH);
		this.loseScreen.x = WindowConstants.WIDTH/2 - this.loseScreen.width/2;
		this.loseScreen.y = WindowConstants.HEIGHT/500 - this.loseScreen.height/20;
		
		/* Check how many lives the player has to instantiate the specific sprite.*/
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
		
		/* Define position lifeRemaining on the Screen*/
		this.lifeRemaining.x = WindowConstants.WIDTH/2 - this.lifeRemaining.width/2;
		this.lifeRemaining.y = WindowConstants.HEIGHT/2 - this.lifeRemaining.height/2;
		
		buildWaitScene();
	}
	
	@Override
	protected void viewSetup() {
		// TODO Auto-generated method stub
		
	}
	
	/* 
	 * Method created for the StageTest Class to be able to pass the remaining life of the player
	 */
	public void setLifePlayer(int lifePlayer){
		 this.lifePlayer = lifePlayer;
	}
	
	/* Get life Player to create Sprite */
	public int getLifePlayer(){
		 return this.lifePlayer;
	}
	
	/* Time wait to transit between scene */
	public void buildWaitScene(){
		
		Timer timer = new Timer();
		CountDownTimer countDown = null;
		countDown = new CountDownTimer();
		countDown.delegate = this;
		long DELAY = 1000;
		timer.scheduleAtFixedRate(countDown, DELAY, DELAY);
	
	}
	
	/* Update Sprite on Screen */
	public void updateScene() {
		
		
		//Print all layers that have been added
		this.delpthScene.drawLayers();
		
		//The method below is responsible for maintaining infinite repetition of the layers.
		final int PIXELS_DOWN = 800; 
		final int PIXELS_SIDES = 600;
		this.delpthScene.repeatLayers(PIXELS_DOWN, PIXELS_SIDES, false);
		
		//Move the parallax orientation vertically
		this.delpthScene.moveLayersStandardY(false);
		
		this.loseScreen.draw();
		
		this.lifeRemaining.draw();
		
	}
	
	/* Method that after the end of the time transit to the screen of Classic 
	 * Continue.
	 */
	@Override
	public void finishScene() {
		if (this.game != null){
			final String END_TIMER = "Timer Ended"; //$NON-NLS-1$
			System.out.println(END_TIMER);
			
			ContinueGame classicContinue = null;
			classicContinue = new ContinueGame();
			
			this.game.transitTo(classicContinue);
		}
	}
	
	@Override
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}
