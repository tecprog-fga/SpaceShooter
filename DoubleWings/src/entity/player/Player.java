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
	
	// The initial lives of the player, in the beginning of the game
	private static final int INITIAL_CHANCES = 3;
	
	
	// Reset the player chances to 3
	private int chances = INITIAL_CHANCES;

	/**
	 * The player chances (lives) manager
	 * @param chances is the number of chances the player has
	 */
	public void setChances(int chances){
		this.chances = chances;
		
		// Notifying HUD to update the screen if it isn't null
		assert(this.observer != null) : "Player log: HUD is null";		 //$NON-NLS-1$
		this.observer.notifyObserver(this);	

	}

	/**
	 * Gets the number of chances
	 * @return
	 */
	public int getChances() {
		return this.chances;
	}

	/**
	 * Get the player's score
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Increase the player's score after each enemy destroyed
	 * @param score1
	 */
	public void increaseScore(int score1) {

		this.score += score1;
		
		// Notifying HUD to update the screen if it isn't null
		assert(this.observer != null) : "Player log: HUD is null"; //$NON-NLS-1$
		this.observer.notifyObserver(this);	
	}

	/**
	 * Constructs the spaceship and in it's initial position
	 * @return
	 */
	public PlayerSpaceship getSpaceship() {
		
		if (this.spaceship == null){
			return this.spaceship = new PlayerSpaceship(this, this.initialPositionY, this.initialPositionY, true);
		}
		return this.spaceship;
		
	}
	
	final static Logger logger = Logger.getLogger(Player.class);

	/**
	 * Lose one life. Handle losing life and game over scenarios. 
	 */
	public void loseLife() {
		
		setChances(this.chances - 1);
		
		logger.debug("Player's lives: " + this.chances);

		if (this.chances < 0) {
			loseGame();
		} else {
			resetSpaceship();
		}
	}

	/**
	 * Reset the spaceship to it's initial position and reset the initial chances of the player
	 */
	public void resetLife() {
		
		setChances(INITIAL_CHANCES);
		resetSpaceship();
		logger.debug("Player's initial lives reset to: " + this.chances);
	}
	
	
	// Decides if the the player can continue playing after losing all lives
	private boolean canContinue = true;
	
	/**
	 * Delegates game events on loseGame, and continue screens
	 */
	public PlayerSceneDelegate delegate = null;
	
	
	// Delegate the player to game over or continue screen 
	private void loseGame() {
		
		if (this.canContinue) {
			useContinue();
		} else {
			this.delegate.transitToGameOver();
		}
	}

	/**
	 * Sets the initial position of the spaceship in the XY axis
	 */
	public double initialPositionX = 0;
	public double initialPositionY = 0;
	
	
	// Reset the spaceship's position after each death
	private void resetSpaceship() {
		this.spaceship.reborn();
		this.spaceship.x = this.initialPositionX;
		this.spaceship.y = this.initialPositionY;
	}
	
	// Delegate the player to continue screen
	private void useContinue() {
		this.canContinue = false;
		resetLife();
		this.delegate.transitToContinue();
	}

	
}