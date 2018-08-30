package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.Parallax;
import jplay.Sprite;
import util.CountDownTimer;
import util.CountDownTimerEnds;

public class Lose extends GameScene implements CountDownTimerEnds {
		
	private Sprite lose;
	private Sprite lifeRemaining;
	private int lifePlayer;                      //Variable to check how many lives the player has remain
	private Parallax parallax;                   //Creation variable to instance a new parallax
	
	public void initialSetup() {
		
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
		this.lose = new Sprite("src/assets/img/continue/YOU-LOSE.png"); //$NON-NLS-1$
		this.lose.x = WindowConstants.WIDTH/2 - this.lose.width/2;
		this.lose.y = WindowConstants.HEIGHT/500 - this.lose.height/20;
		
		//Check how many lives the player has to instantiate the specific sprite.
		if(getLifePlayer() == 1){
			this.lifeRemaining = new Sprite("src/assets/img/continue/1life.png");	 //$NON-NLS-1$
		}
		
		else if(getLifePlayer() == 2){
			this.lifeRemaining = new Sprite("src/assets/img/continue/2life.png"); //$NON-NLS-1$
		}
		
		else if(getLifePlayer() == 3){
			this.lifeRemaining = new Sprite("src/assets/img/continue/3life.png"); //$NON-NLS-1$
		}
		
		//Define position lifeRemaining on the Screen
		this.lifeRemaining.x = WindowConstants.WIDTH/2 - this.lifeRemaining.width/2;
		this.lifeRemaining.y = WindowConstants.HEIGHT/2 - this.lifeRemaining.height/2;
		
		timeWait();
	}
	
	@Override
	protected void viewSetup() {
		// TODO Auto-generated method stub
		
	}
	
	//Method created for the StageTest Class to be able to pass the remaining life of the player
	public void setLifePlayer(int lifePlayer){
		 this.lifePlayer = lifePlayer;
	}
	
	//Get life Player to create Sprite
	public int getLifePlayer(){
		 return this.lifePlayer;
	}
	
	//Time wait to transit between scene
	public void timeWait(){
		
		Timer timer = new Timer();
		CountDownTimer countDownn = new CountDownTimer();
		countDownn.delegate = this;
		long delay = 1000;
		timer.scheduleAtFixedRate(countDownn, delay, delay);
	
	}
	
	//Update Sprite on Screen
	public void update() {
		
		//background.draw();
        
		//Print all layers that have been added
		this.parallax.drawLayers();
		
		//The method below is responsible for maintaining infinite repetition of the layers.
		this.parallax.repeatLayers(800, 600, false);
		
		//Move the parallax orientation vertically
		this.parallax.moveLayersStandardY(false);
		
		this.lose.draw();
		
		this.lifeRemaining.draw();
		
	}
	
	//Method that after the end of the time transit to the screen of Classic Continue.
	@Override
	public void terminate() {
		if (this.game != null){
			System.out.println("Timer Ended"); //$NON-NLS-1$
			ClassicContinue classicContinue = new ClassicContinue();
			this.game.transitTo(classicContinue);
		}
	}
	
	@Override
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}
