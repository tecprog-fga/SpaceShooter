package entity.player;

import entity.Bullet;
import observer.GameEntityObserver;
import util.DelayTimer;

public class Player {

	private static final int INITIAL_CHANCES = 3; // Initially the player will have three lifes

	private boolean canContinue = true;
	private int chances = INITIAL_CHANCES;
	private PlayerSpaceship spaceship = null;
	public PlayerSceneDelegate delegate = null;
	private int score = 0;
	private GameEntityObserver observer = null; //Temp solution to the observer
	public double initialPositionX = 0; //Respawn
	public double initialPositionY = 0;

	public Player() {
		super();
		this.score = 0;
	}

	public GameEntityObserver getObserver() { // HUD observer getter and setter 
		return this.observer;
	}

	public void setObserver(GameEntityObserver observer) {
		//Adding HUD observer to the shield
		spaceship.getShield().setObserver(observer);
		this.observer = observer;
	}

	public void setChances(int chances){ //Chances setters and getters
		this.chances = chances;
		
		//Notifying HUD to update chances shower
		if (observer != null) {
			observer.notifyObserver(this);	
		} else {
			System.out.println("Player log: HUD is null :(");
		}
	}

	public int getChances() {
		return this.chances;
	}

	public int getScore() { 	//Score setters and getters
		return score;
	}

	public void increaseScore(int score) {
		//this.score.increaseScore(score);
		this.score += score;
		
		//Notifying HUD to update score shower
		if (observer != null) {
			observer.notifyObserver(this);	
		} else {
			System.out.println("Player log: HUD is null :(");
		}
	}

	private void resetSpaceship() {
		this.spaceship.reborn();
		this.spaceship.x = initialPositionX;
		this.spaceship.y = initialPositionY;
	}

	/**
	 * Lose one life. Handle losing life and game over scenarios. 
	 * 
	 * */
	public void loseLife() {
		//System.out.println("entered here in loseLife ");
		setChances(this.chances - 1);
		//System.out.println("LOST A LIIIIIIIIIFE");
		System.out.println("lifes on player: " + this.chances);

		if (this.chances < 0) {
			loseGame();
		} else {
			resetSpaceship();
		}
	}

	public void resetLife() {
		setChances(INITIAL_CHANCES);
		resetSpaceship();
		System.out.println("Player log: life reset to: " + this.chances);
	}

	public void loseGame() {
		
		if (this.canContinue) {
			useContinue();
		} else {
			this.delegate.transitToGameOver();
		}
	}

	public void useContinue() {
		this.canContinue = false;
		resetLife();
		this.delegate.transitToContinue();
	}

	public PlayerSpaceship getSpaceship() {
		
		if (spaceship == null){
			spaceship = new PlayerSpaceship(this, this.initialPositionY, this.initialPositionY, true);
		}
		return spaceship;
	}
}