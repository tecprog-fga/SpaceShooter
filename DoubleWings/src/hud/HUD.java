package hud;

import jplay.Sprite;
import observer.GameEntityObserver;
import java.awt.Color;
import java.awt.Font;
import constants.WindowConstants;
import entity.player.*;

public class HUD implements GameEntityObserver {
	/**
	 * creates the life bar with the maximum screen width
	 */
	private float barSizeMax = WindowConstants.WIDTH;
	
	/**
	 * creation of the objects that hold the images displayed on the game screen
	 */
	private Sprite shieldLifeBar = null;
	private Sprite shieldLifeBarOrnament = null;
	private Sprite numberOfLivesImage = null;
	private HudScore scoreText = null;
	
	/**
	 * path of shield, shield ornament and lives images
	 */
	final String ENERGY = "src/assets/img/hud/energy.png"; //$NON-NLS-1$
	final String SHIELD_BAR_ORNAMENT = "src/assets/img/hud/shield_bar_ornament.png"; //$NON-NLS-1$
	final String LIVES = "src/assets/img/hud/chances.png"; //$NON-NLS-1$
	final int SCORE_FONT_SIZE = 40;
	
	public HUD() {
		/**
		 * positions the life bar on the screen
		 */
		shieldLifeBar = new Sprite(ENERGY);
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
		this.shieldLifeBar.y = WindowConstants.HEIGHT - this.shieldLifeBar.height;

		/**
		 * positions the life bar ornament on the screen
		 */
		shieldLifeBarOrnament = new Sprite(SHIELD_BAR_ORNAMENT);
		this.shieldLifeBarOrnament.x = 0;
		this.shieldLifeBarOrnament.y = WindowConstants.HEIGHT - this.shieldLifeBarOrnament.height;

		/**
		 * positions the number of lives on the screen
		 */
		numberOfLivesImage = new Sprite(LIVES, 4);
		this.numberOfLivesImage.setCurrFrame(3);
		this.numberOfLivesImage.x = WindowConstants.WIDTH - numberOfLivesImage.width;
		this.numberOfLivesImage.y = 0;

		/**
		 * formats the score and positions it on the screen
		 */
		scoreText = new HudScore(10, 40);
		scoreText.setColor(Color.WHITE);
		scoreText.setFont(new Font("Arial",Font.TRUETYPE_FONT, SCORE_FONT_SIZE));
		scoreText.setScreenScore(0);
	}

	public void update() {

	}

	public void draw() {
		shieldLifeBar.draw();
		shieldLifeBarOrnament.draw();
		numberOfLivesImage.draw();
		scoreText.draw();
	}

	public void updateShieldLifeBar(Shield shield) {
		float shieldLifeBarProportion = ((float)shield.getLife()/(float)shield.maxLife);
		float newLifeBarWidth = shieldLifeBarProportion * barSizeMax;
		this.shieldLifeBar.width = (int) newLifeBarWidth;
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
	}
	
	/**
	 * number of player lives can not be negative
	 */
	final int MAX_NUMBER_OF_LIVES = 3;
	final int MIN_NUMBER_OF_LIVES = 0;

	public void updateNumberOfLivesOnScreen(int playerNumberOfLives) {
		/**
		 * the number of lives must be within the limit established above
		 */
		if (playerNumberOfLives <= MAX_NUMBER_OF_LIVES && playerNumberOfLives >= MIN_NUMBER_OF_LIVES) {
			this.numberOfLivesImage.setCurrFrame(playerNumberOfLives);
		} else {
			System.out.println("HUD log: Player chances number is out of range.");
			this.numberOfLivesImage.setCurrFrame(0);
		}
	}

	public void updateScoreOnScreen(int score) {
		scoreText.setScreenScore(score);
	}

	public void notifyObserver(Object entity) {

		/**
		 * should display the log on the console to make it easier
		 * to verify that the shield is working as it should
		 */
		if (entity instanceof Shield) {
			Shield shield = (Shield) entity;
			System.out.println("HUD log: Shield class identified." + shield.getLife() + " " + shield.maxLife);
			updateShieldLifeBar(shield);
		} else if (entity instanceof Player) {
			Player player = (Player) entity;
			updateNumberOfLivesOnScreen(player.getChances());
			updateScoreOnScreen(player.getScore());
		} else {
			System.out.println("HUD log: No class identified.");
		}	
	}

}