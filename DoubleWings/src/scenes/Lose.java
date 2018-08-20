package scenes;

import java.util.Timer;
import constants.WindowConstants;
import jplay.GameImage;
import jplay.Parallax;
import jplay.Sprite;
import util.CountDownTimer;
import util.CountDownTimerEnds;

public class Lose extends GameScene implements CountDownTimerEnds {
		
	// Sprite on scene
	private GameImage background;
	private Sprite lose;
	private Sprite lifeRemaining;
	private int lifePlayer;                      //Variable to check how many lives the player has remain
	private Parallax parallax;                   //Creation variable to instance a new parallax
	
	//Thread counter
	//static Thread thread = new Thread(); 
	
	public void initialSetup() {
		
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
		lose = new Sprite("src/assets/img/continue/YOU-LOSE.png");
		lose.x = WindowConstants.WIDTH/2 - lose.width/2;
		lose.y = WindowConstants.HEIGHT/500 - lose.height/20;
		
		//Check how many lives the player has to instantiate the specific sprite.
		if(getLifePlayer() == 1){
			lifeRemaining = new Sprite("src/assets/img/continue/1life.png");	
		}
		
		else if(getLifePlayer() == 2){
			lifeRemaining = new Sprite("src/assets/img/continue/2life.png");
		}
		
		else if(getLifePlayer() == 3){
			lifeRemaining = new Sprite("src/assets/img/continue/3life.png");
		}
		
		//Define position lifeRemaining on the Screen
		lifeRemaining.x = WindowConstants.WIDTH/2 - lifeRemaining.width/2;
		lifeRemaining.y = WindowConstants.HEIGHT/2 - lifeRemaining.height/2;
		
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
		 return lifePlayer;
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
		parallax.drawLayers();
		
		//The method below is responsible for maintaining infinite repetition of the layers.
		parallax.repeatLayers(800, 600, false);
		
		//Move the parallax orientation vertically
		parallax.moveLayersStandardY(false);
		
		lose.draw();
		
		lifeRemaining.draw();
		
	}
	
	//Method that after the end of the time transit to the screen of Classic Continue.
	@Override
	public void terminate() {
		if (game != null){
			System.out.println("Timer Ended");
			ClassicContinue classicContinue = new ClassicContinue();
			game.transitTo(classicContinue);
		}
	}
	
	@Override
	public void updateImageForIndex(int index) {
		//Nothing to do
	}

}
