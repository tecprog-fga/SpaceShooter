/*********************************************************
  * File: HudScore.java
  * Purpose: HudScore class implementation
  ********************************************************/

package hud;

import java.awt.Color;
import java.awt.Font;
import jplay.Time;

/**
 * class that controls the score that will be displayed on the HUD
 */
public class HudScore extends Time {
	/**
	 * Variable used to save the score information that will be displayed in hud
	 */
	private int screenScore = 0; {
	assert(screenScore == 0):("Pontuação inicial deve ser zero!");
	}
	private static Font hudScoreFont = new Font("Arial",Font.TRUETYPE_FONT, 20);

	/**
	 * Constructor method of class HudScore
	 * @param x
	 * @param y
	 */
	public HudScore(int x, int y) {
		super(10, 10, 10, x, y, hudScoreFont,Color.YELLOW, true );
		assert(x >= 0  && x <= 25):("Score deve estar no canto esquerdo da tela!");
		assert(y >= 35 && y <= 60):("Score deve estar no canto superior da tela!");
	}

	/**
	 * Method that transforms score value into String
	 */
	public String toString() {
		super.toString();
		return Integer.toString(this.screenScore);
	}

	/**
	 * Getter method of attribute score
	 * @return screenScore
	 */
	public int getScreenScore() {
		assert(screenScore >= 0):("Pontuação inválida!");
		return screenScore;
	}

	/**
	 * Setter method of attribute score
	 * @param score current player score
	 */
	public void setScreenScore(int score) {
		assert(score >= 0):("Pontuação inválida!");
		this.screenScore = score;
	}
}