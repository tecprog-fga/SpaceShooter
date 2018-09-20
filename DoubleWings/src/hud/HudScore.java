package hud;

import java.awt.Color;
import java.awt.Font;
import jplay.Time;

public class HudScore extends Time {
	/**
	 * Variable used to save the score information that will be displayed in hud
	 */
	private int screenScore = 0;

	/**
	 * Constructor method of class HudScore
	 * @param x
	 * @param y
	 */
	public HudScore(int x, int y) {
		super(10, 10, 10, x, y, new Font("Arial",Font.TRUETYPE_FONT, 20),Color.YELLOW, true );
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
		return screenScore;
	}

	/**
	 * Setter method of attribute score
	 * @param score current player score
	 */
	public void setScreenScore(int score) {
		this.screenScore = score;
	}
}