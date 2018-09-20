/*****************************************************************
 * File: Player.java
 * Purpose: Player class implementation
 *****************************************************************/


package entity.player;

import entity.Bullet;
import observer.GameEntityObserver;
import util.DelayTimer;

/**
 * This class contains builds the player,
 * it manages the player score and lives
 */
public class Player {
	
	/**
	 * The initial lives of the player, in the beginning of the game
	 */
	private static final int INITIAL_CHANCES = 3;
	
	/**
	 * The score variable to be increased as the player scores
	 */
	private int score = 0;

	
	public Player() {
		super();
		this.score = 0;
	}

	/**
	 * The Observer object receives the game live updates
	 */
	private GameEntityObserver observer = null;
	
	public GameEntityObserver getObserver() { // HUD observer getter and setter 
		return this.observer;
	}
	
	
	/**
	 * This object builds the spaceship of the game
	 */
	private PlayerSpaceship spaceship = null;

	public void setObserver(GameEntityObserver observer) {
		//Adding HUD observer to the shield
		spaceship.getShield().setObserver(observer);
		this.observer = observer;
	}
	
	/**
	 * Reset the player chances to 3
	 */
	private int chances = INITIAL_CHANCES;

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
	
	/**
	 * Sets the initial position of the spaceship in the XY axis
	 */
	public double initialPositionX = 0;
	public double initialPositionY = 0;

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
	
	/**
	 * Decides if the the player can continue playing after losing all lives
	 */
	private boolean canContinue = true;
	
	/**
	 * Delegates game events on loseGame, and continue screens
	 */
	public PlayerSceneDelegate delegate = null;
	
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