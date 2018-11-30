/*********************************************************
  * File: HUD.java
  * Purpose: HUD class implementation
  ********************************************************/

package hud;

import jplay.Sprite;
import observer.GameEntityObserver;
import scenes.GameScene;

import java.awt.Color;
import java.awt.Font;

import org.apache.log4j.Logger;

import constants.WindowConstants;
import entity.player.*;

/**
 * Class for all information displayed on the game screen for the player (HUD)
 */
public class HUD implements GameEntityObserver {
	
	boolean errorOcurred = false;
	final static Logger logger = Logger.getLogger(HUD.class);
	
	/**
	 * creation of the objects that hold the images displayed on the game screen
	 */
	private Sprite shieldLifeBar = null;
	private Sprite shieldLifeBarOrnament = null;
	private Sprite numberOfLivesImage = null;
	private HudScore scoreText = null;
	
	/**
	 * number of player lives can not be negative
	 */
	private final int MAX_NUMBER_OF_LIVES = 3;
	private final int MIN_NUMBER_OF_LIVES = 0;
	
	/**
	 * Constructor method of class HUD
	 */
	public HUD() {
		
		Sprite shieldLifeBar = generateShieldLifeBar();
		Sprite shieldLifeBarOrnament = generateShieldLifeBarOrnament();
		Sprite numberOfLivesImage = generateNumberOfLivesImage();
		HudScore scoreText = generateHudScore();
	}
	
	/**
	 * Method that updates the number of lives
	 * @param playerNumberOfLives goes from zero to three, can be changed up
	 */
	public void updateNumberOfLivesOnScreen(int playerNumberOfLives) {
		try {
			// the number of lives must be within the limit established above
			assert(playerNumberOfLives >= -1);
			if (playerNumberOfLives <= MAX_NUMBER_OF_LIVES && playerNumberOfLives >= MIN_NUMBER_OF_LIVES) {
				this.numberOfLivesImage.setCurrFrame(playerNumberOfLives);
			} else {
				logger.debug("Player chances number is out of range.");
				this.numberOfLivesImage.setCurrFrame(0);
			}
		}
		catch(IllegalArgumentException exception) {
			logger.error("Number of player lives must be positive!", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}

	/**
	 * Method that updates the shield life bar
	 * @param shield object of class shield
	 */
	public void updateShieldLifeBar(Shield shield) {
		float shieldLifeBarProportion = ((float)shield.getLife()/(float)shield.maxLife);
		try {
			assert(shieldLifeBarProportion >= 0 && shieldLifeBarProportion <= 1);
		}
		catch(IllegalArgumentException exception) {
			logger.error("Error in proportion calculate!", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		/**
		 * creates the life bar with the maximum screen width
		 */
		final float barSizeMax = WindowConstants.WIDTH;
		float newLifeBarWidth = shieldLifeBarProportion * barSizeMax;
		this.shieldLifeBar.width = (int) newLifeBarWidth;
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
		assert(shield != null):("Objeto shield não foi passado corretamente!");
	}

	/**
	 * method that tells the player their current score
	 * @param score the player score at match
	 */
	public void updateScoreOnScreen(int score) {
		try {
			scoreText.setScreenScore(score);
			assert(score >= 0);
		}
		catch(IllegalArgumentException exception) {
			logger.error("Score can't be negative!", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}

	/**
	 * Method that shows the updated information of shield on the console
	 */
	public void notifyObserver(Object entity) {
		try {
			/* should display the log on the console to make it easier
			 to verify that the shield is working as it should */
			assert(entity != null);
			if (entity instanceof Shield) {
				Shield shield = (Shield) entity;
				assert(shield != null):("Shield object can't be null!");
				logger.debug("Shield class identified." + shield.getLife() 
					+ " " + shield.maxLife);
				updateShieldLifeBar(shield);
			} else if (entity instanceof Player) {
				Player player = (Player) entity;
				assert(player != null):("Player object can't be null!");
				updateNumberOfLivesOnScreen(player.getPlayerChances());
				updateScoreOnScreen(player.getScore());
			} else {
				logger.debug("No class identified.");
			}
		}
		catch(NullPointerException exception) {
			logger.error("Entity object can't be null!", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}	
	}
	
	/**
	 * Method that draws the HUD information on the screen
	 */
	public void draw() {
		shieldLifeBar.draw();
		shieldLifeBarOrnament.draw();
		numberOfLivesImage.draw();
		scoreText.draw();
	}
	
	/**
	 * method responsible for generating the HUD score
	 * @return HudScore
	 */
	private HudScore generateHudScore() {
		final int SCORE_FONT_SIZE = 40;
		assert(SCORE_FONT_SIZE >= 20):("A fonte do score está muito pequena!");
		assert(SCORE_FONT_SIZE <= 80):("A fonte do score está muito grande!");
			
		// formats the score and positions it on the screen
		scoreText = new HudScore(10, 40);
		try {
			assert(scoreText != null);
			scoreText.setColor(Color.WHITE);
			Font scoreTextFont = new Font("Arial",Font.TRUETYPE_FONT, SCORE_FONT_SIZE);
			scoreText.setFont(scoreTextFont);
			scoreText.setScreenScore(0);
		}
		catch(NullPointerException exception) {
			logger.error("ScoreText object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return scoreText;
	}
	
	/**
	 * method responsible for generating the number of lives image
	 * @return numberOfLivesImage
	 */
	private Sprite generateNumberOfLivesImage() {
		final String LIVES = "src/assets/img/hud/chances.png";
		assert(LIVES != null):("Sprite de quantidade de vidas não foi achada corretamente!");
			
		// positions the number of lives on the screen
		numberOfLivesImage = new Sprite(LIVES, 4);
		try {
			assert(numberOfLivesImage != null);
			this.numberOfLivesImage.setCurrFrame(3);
			this.numberOfLivesImage.x = WindowConstants.WIDTH - numberOfLivesImage.width;
			this.numberOfLivesImage.y = 0;
		}
		catch(NullPointerException exception) {
			logger.error("NumberOfLivesImage object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return numberOfLivesImage;
	}
	
	/**
	 * method responsible for generating the shield life bar ornament
	 * @return shieldLifeBarOrnament
	 */
	private Sprite generateShieldLifeBarOrnament() {
		final String SHIELD_BAR_ORNAMENT = "src/assets/img/hud/shield_bar_ornament.png";
		assert(SHIELD_BAR_ORNAMENT != null):("Sprite de ornamento não foi achada corretamente!");
			
		// positions the life bar ornament on the screen
		shieldLifeBarOrnament = new Sprite(SHIELD_BAR_ORNAMENT);
		try {
			assert(shieldLifeBarOrnament != null);
			this.shieldLifeBarOrnament.x = 0;
			this.shieldLifeBarOrnament.y = WindowConstants.HEIGHT - this.shieldLifeBarOrnament.height;
		}
		catch(NullPointerException exception) {
			logger.error("ShieldLifebarOrnament object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return shieldLifeBarOrnament;
	}
	
	/**
	 * method responsible for generating the shield life bar
	 * @return shieldLifeBar
	 */
	private Sprite generateShieldLifeBar() {
		final String ENERGY = "src/assets/img/hud/energy.png";
		assert(ENERGY != null):("Sprite de barra de vida não foi achada corretamente!");
			
		// positions the life bar on the screen
		shieldLifeBar = new Sprite(ENERGY);
		try {
			assert(shieldLifeBar != null);
			this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
			this.shieldLifeBar.y = WindowConstants.HEIGHT - this.shieldLifeBar.height;
		}
		catch(NullPointerException exception) {
			logger.error("ShieldLifebar object can't be null", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
		return shieldLifeBar;
	}
}