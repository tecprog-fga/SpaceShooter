/*********************************************************
  * File: HUD.java
  * Purpose: HUD class implementation
  ********************************************************/

package hud;

import jplay.Sprite;
import observer.GameEntityObserver;
import java.awt.Color;
import java.awt.Font;
import constants.WindowConstants;
import entity.player.*;

/**
 * Class for all information displayed on the game screen for the player (HUD)
 */
public class HUD implements GameEntityObserver {
	/**
	 * creates the life bar with the maximum screen width
	 */
	private float barSizeMax = WindowConstants.WIDTH; {
	}
	
	/**
	 * creation of the objects that hold the images displayed on the game screen
	 */
	private Sprite shieldLifeBar = null;
	private Sprite shieldLifeBarOrnament = null;
	private Sprite numberOfLivesImage = null;
	private HudScore scoreText = null;
	
	// path of shield, shield ornament and lives images
	final String ENERGY = "src/assets/img/hud/energy.png"; { //$NON-NLS-1$
	assert(ENERGY != null):("Sprite de barra de vida não foi achada corretamente!");
	}
	final String SHIELD_BAR_ORNAMENT = "src/assets/img/hud/shield_bar_ornament.png"; { //$NON-NLS-1$
	assert(SHIELD_BAR_ORNAMENT != null):("Sprite de ornamento não foi achada corretamente!");
	}
	final String LIVES = "src/assets/img/hud/chances.png"; { //$NON-NLS-1$
	assert(LIVES != null):("Sprite de quantidade de vidas não foi achada corretamente!");
	}
	final int SCORE_FONT_SIZE = 40; {
	assert(SCORE_FONT_SIZE >= 20):("A fonte do score está muito pequena!");
	assert(SCORE_FONT_SIZE <= 80):("A fonte do score está muito grande!");
	}
	
	/**
	 * Constructor method of class HUD
	 */
	public HUD() {
		// positions the life bar on the screen
		shieldLifeBar = new Sprite(ENERGY);
		assert(shieldLifeBar != null):("Objeto shieldLifeBar não pode ser nulo");
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
		this.shieldLifeBar.y = WindowConstants.HEIGHT - this.shieldLifeBar.height;
		
		// positions the life bar ornament on the screen
		shieldLifeBarOrnament = new Sprite(SHIELD_BAR_ORNAMENT);
		assert(shieldLifeBarOrnament != null):("Objeto shieldLifeBarOrnament não pode ser nulo");
		this.shieldLifeBarOrnament.x = 0;
		this.shieldLifeBarOrnament.y = WindowConstants.HEIGHT - this.shieldLifeBarOrnament.height;
		
		// positions the number of lives on the screen
		numberOfLivesImage = new Sprite(LIVES, 4);
		assert(numberOfLivesImage != null):("Objeto numberOfLivesImage não pode ser nulo");
		this.numberOfLivesImage.setCurrFrame(3);
		this.numberOfLivesImage.x = WindowConstants.WIDTH - numberOfLivesImage.width;
		this.numberOfLivesImage.y = 0;
		

		// formats the score and positions it on the screen
		scoreText = new HudScore(10, 40);
		assert(scoreText != null):("Objeto scoreText não pode ser nulo");
		scoreText.setColor(Color.WHITE);
		assert(Color.WHITE != null):("Objeto referente a cor não foi recebido!");
		scoreText.setFont(new Font("Arial",Font.TRUETYPE_FONT, SCORE_FONT_SIZE));
		scoreText.setScreenScore(0);
	}

	public void update() {

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
	 * Method that updates the shield life bar
	 * @param shield object of class shield
	 */
	public void updateShieldLifeBar(Shield shield) {
		float shieldLifeBarProportion = ((float)shield.getLife()/(float)shield.maxLife);
		assert(shieldLifeBarProportion >= 0 && shieldLifeBarProportion <= 1):("Proporção não está sendo calculada corretamente!");
		float newLifeBarWidth = shieldLifeBarProportion * barSizeMax;
		this.shieldLifeBar.width = (int) newLifeBarWidth;
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
		assert(shield != null):("Objeto shield não foi passado corretamente!");
	}
	
	/**
	 * number of player lives can not be negative
	 */
	final int MAX_NUMBER_OF_LIVES = 3;
	final int MIN_NUMBER_OF_LIVES = 0;
	
	/**
	 * Method that updates the number of lives
	 * @param playerNumberOfLives goes from zero to three, can be changed up
	 */
	public void updateNumberOfLivesOnScreen(int playerNumberOfLives) {
		// the number of lives must be within the limit established above
		System.out.println("VIDAS: " + playerNumberOfLives);
		assert(playerNumberOfLives >= -1):("Número de vidas do jogador deve ser positivo!");
		if (playerNumberOfLives <= MAX_NUMBER_OF_LIVES && playerNumberOfLives >= MIN_NUMBER_OF_LIVES) {
			this.numberOfLivesImage.setCurrFrame(playerNumberOfLives);
		} else {
			System.out.println("HUD log: Player chances number is out of range.");
			this.numberOfLivesImage.setCurrFrame(0);
		}
	}

	/**
	 * method that tells the player their current score
	 * @param score the player score at match
	 */
	public void updateScoreOnScreen(int score) {
		scoreText.setScreenScore(score);
		assert(score >= 0):("Pontuação não pode ser negativa!");
	}

	/**
	 * Method that shows the updated information of shield on the console
	 */
	public void notifyObserver(Object entity) {

		/* should display the log on the console to make it easier
		 to verify that the shield is working as it should */
		assert(entity != null):("Objeto não foi recebido corretamente!");
		if (entity instanceof Shield) {
			Shield shield = (Shield) entity;
			assert(shield != null):("Objeto escudo não pode ser nulo!");
			System.out.println("HUD log: Shield class identified." + shield.getLife() + " " + shield.maxLife);
			updateShieldLifeBar(shield);
		} else if (entity instanceof Player) {
			Player player = (Player) entity;
			assert(player != null):("Objeto jogador não pode ser nulo!");
			updateNumberOfLivesOnScreen(player.getChances());
			updateScoreOnScreen(player.getScore());
		} else {
			System.out.println("HUD log: No class identified.");
		}	
	}

}