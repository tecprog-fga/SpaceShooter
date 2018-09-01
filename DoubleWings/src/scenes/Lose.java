package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.Parallax;
import jplay.Sprite;
import util.CountDownTimer;
import util.CountDownTimerEnds;

public class Lose extends GameScene implements CountDownTimerEnds {
	
	/**
	 * Initialize paths
	 */
	private final static String DELPTH_BACKGROUND_PATH = "src/assets/img/temp_background.png"; //$NON-NLS-1$
	private final static String DELPTH_UNIVERSE1_PATH = "src/assets/img/universe1.png"; //$NON-NLS-1$
	private final static String DELPTH_UNIVERSE2_PATH = "src/assets/img/universe2.jpg"; //$NON-NLS-1$
	private final static String DELPTH_UNIVERSE3_PATH = "src/assets/img/universe3.jpg"; //$NON-NLS-1$
	private final static String DELPTH_UNIVERSE4_PATH = "src/assets/img/universe4.jpg"; //$NON-NLS-1$
	private final static String LOSE_PATH = "src/assets/img/continue/YOU-LOSE.png"; //$NON-NLS-1$
	private final static String ONE_LIFE_PATH = "src/assets/img/continue/1life.png"; //$NON-NLS-1$
	private final static String TWO_LIFE_PATH = "src/assets/img/continue/2life.png"; //$NON-NLS-1$
	private final static String THREE_LIFE_PATH = "src/assets/img/continue/3life.png"; //$NON-NLS-1$
	
	/**
	 * This object represent the building delpth scene screen
	 */
	private Parallax delpthScene = null; //T01 T05 T07 T06
	
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
		
        /* The first one added will be the last one to be painted.*/
		this.delpthScene.add(DELPTH_BACKGROUND_PATH);
		this.delpthScene.add(DELPTH_BACKGROUND_PATH);
		this.delpthScene.add(DELPTH_UNIVERSE1_PATH);
		this.delpthScene.add(DELPTH_UNIVERSE2_PATH);
		this.delpthScene.add(DELPTH_UNIVERSE3_PATH);
		
		/* Since universe4.jpg was the last to be added to the list, 
		 * it will be the main layer (mainLayer).
		 */
		this.delpthScene.add(DELPTH_UNIVERSE4_PATH);
		
		//Adjusts the speed of all layers from the main layer
		this.delpthScene.setVelAllLayers(0, 1);
		
		/*Define scenes elements position
		 *Continue sprite upper-center position*/
		this.loseScreen = new Sprite(LOSE_PATH);
		this.loseScreen.x = WindowConstants.WIDTH/2 - this.loseScreen.width/2;
		this.loseScreen.y = WindowConstants.HEIGHT/500 - this.loseScreen.height/20;
		
		/* Check how many lives the player has to instantiate the specific sprite.*/
		if(getLifePlayer() == 1){
			this.lifeRemaining = new Sprite(ONE_LIFE_PATH);
		}
		
		else if(getLifePlayer() == 2){
			this.lifeRemaining = new Sprite(TWO_LIFE_PATH);
		}
		
		else if(getLifePlayer() == 3){
			this.lifeRemaining = new Sprite(THREE_LIFE_PATH);
		}
		
		/* Define position lifeRemaining on the Screen*/
		this.lifeRemaining.x = WindowConstants.WIDTH/2 - this.lifeRemaining.width/2;
		this.lifeRemaining.y = WindowConstants.HEIGHT/2 - this.lifeRemaining.height/2;
		
		timeWait();
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
	public void timeWait(){
		
		Timer timer = new Timer();
		CountDownTimer countDownn = new CountDownTimer();
		countDownn.delegate = this;
		long delay = 1000;
		timer.scheduleAtFixedRate(countDownn, delay, delay);
	
	}
	
	/* Update Sprite on Screen */
	public void updateScene() {
		
		//Print all layers that have been added
		this.delpthScene.drawLayers();
		
		//The method below is responsible for maintaining infinite repetition of the layers.
		this.delpthScene.repeatLayers(800, 600, false);
		
		//Move the parallax orientation vertically
		this.delpthScene.moveLayersStandardY(false);
		
		this.loseScreen.draw();
		
		this.lifeRemaining.draw();
		
	}
	
	/* Method that after the end of the time transit to the screen of Classic 
	 * Continue.
	 */
	@Override
	public void terminate() {
		if (this.game != null){
			final String END_TIMER = "Timer Ended"; //$NON-NLS-1$
			System.out.println(END_TIMER);
			
			ClassicContinue classicContinue = null;
			classicContinue = new ClassicContinue();
			
			this.game.transitTo(classicContinue);
		}
	}
	
	@Override
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}
