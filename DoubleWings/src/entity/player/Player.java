/*****************************************************************
 * File: Player.java
 * Purpose: Player class implementation
 *****************************************************************/


package entity.player;

import org.apache.log4j.Logger;

import observer.GameEntityObserver;

/**
 * This class contains builds the player,
 * it manages the player score and lives
 */
public class Player {
	
	// The score variable to be increased as the player scores
	private int score = 0;
	
	/**
	 * Constructor method to set the player scores to zero
	 */
	public Player() {
		super();
		this.score = 0;
	}

	// The Observer object receives the game live updates	
	private GameEntityObserver observer = null;
	
	/**
	 * Game HUD observer get the events
	 * @return observer
	 */
	public GameEntityObserver getObserver() { // HUD observer getter and setter 
		return this.observer;
	}
	
	// This object builds the spaceship of the game
	private PlayerSpaceship spaceship = null;

	/**
	 * Updates the HUD observer to get the state of the shield
	 * @param observer to update the game HUD
	 */
	public void setObserver(GameEntityObserver observer) {
		this.spaceship.getShield().setObserver(observer);
		this.observer = observer;
	}
	
	//Logger debbuger declaration
	final static Logger logger = Logger.getLogger(Player.class);
	
	// The initial lives of the player, in the beginning of the game
	private static final int INITIAL_CHANCES = 3;
	
	boolean errorOccured = false;
	
	// Reset the player chances to 3
	private int chances = INITIAL_CHANCES;

	/**
	 * The player chances (lives) manager
	 * @param chances is the number of chances the player has
	 */
	public void setChances(int chances){
		this.chances = chances;
		try {
			// Notifying HUD to update the screen if it isn't null
			assert(this.observer != null) : "Player log: HUD is null";
			this.observer.notifyObserver(this);				
		}catch(NullPointerException e) {
			e.printStackTrace();
			logger.error("HUD is receiving Null", e);
			this.errorOccured = true;
		}

	}

	/**
	 * Gets the number of chances
	 * @return
	 */
	public int getPlayerChances() {
		return this.chances;
	}
	
	public int getScore() {
		return this.score;
	}

	public void increaseScore(int score1) {
		this.score += score1;
		logger.debug("Incriased score");
		
		try {			
			this.observer.notifyObserver(this);		
		} catch(NullPointerException e) {
			e.printStackTrace();
			logger.error("HUD is receiving Null", e);
			this.errorOccured = true;
		}
	}

	/**
	 * Constructs the spaceship and in it's initial position
	 * @return
	 */
	public PlayerSpaceship getSpaceship() {
		
		if (this.spaceship == null){
			return this.spaceship = new PlayerSpaceship(this, this.spaceshipInitialPositionY, this.spaceshipInitialPositionY, true);
		}
		return this.spaceship;
	}
	
	/*
	 * Handle player's life, gameover and continue screen
	 */
	public void loseLife() {
		setChances(this.chances - 1);
		
		logger.debug("Player's lives: " + this.chances);

		if (this.chances < 0) {
			loseGame();
		} else {
			resetSpaceshipPosition();
		}
	}
	
	public double spaceshipInitialPositionX = 0;
	public double spaceshipInitialPositionY = 0;
	
	private void resetSpaceshipPosition() {
		logger.debug("Reseting spaceship's position!");
		this.spaceship.reborn();
		this.spaceship.x = this.spaceshipInitialPositionX;
		this.spaceship.y = this.spaceshipInitialPositionY;
	}

	public void resetLife() {
		setChances(INITIAL_CHANCES);			
		logger.debug("Player's initial lives reset to: " + this.chances);
		resetSpaceshipPosition();
	}
	
	public PlayerSceneDelegate delegate = null;
	
	private void useContinue() {
		logger.debug("Using continue game!");
		this.canContinue = false;
		resetLife();
		this.delegate.transitToContinue();
	}
	
	private boolean canContinue = true;
	
	private void loseGame() {
		logger.debug("Lost the game!");
		if (this.canContinue) {
			useContinue();
		} else {
			this.delegate.transitToGameOver();
		}
	}


	


	
}