package hud;

import java.awt.Color;
import java.awt.Font;
import jplay.Time;

public class HudScore extends Time {
	private int screenScore = 0;

	public HudScore(int x, int y) {
		super(10, 10, 10, x, y, new Font("Arial",Font.TRUETYPE_FONT, 20),Color.YELLOW, true );
	}

	public String toString() {
		/**
		 * 	System.out.println("Score log: Transform score value to srting " + this.score);
		 */
		super.toString();
		return Integer.toString(this.screenScore);
	}

	/**	
	 *  public void draw() {
	 *		super.draw(message);
	 *	}
	 */

	/**
	 * Score getter and setter
	 * @return
	 */
	public int getScreenScore() {
		return screenScore;
	}

	public void setScreenScore(int score) {
		this.screenScore = score;
	}
}