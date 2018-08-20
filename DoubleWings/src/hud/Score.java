package hud;

import java.awt.Color;
import java.awt.Font;

import jplay.Time;

public class Score extends Time{
	private int score = 0;
	
	public Score(int x, int y) {
		super(10, 10, 10, x, y, new Font("Arial",Font.TRUETYPE_FONT, 20),Color.YELLOW, true );
	}
	
	public String toString() {
//		System.out.println("Score log: Transform score value to srting " + this.score);
		super.toString();
		return Integer.toString(this.score);
	}
	
//	public void draw() {
//		super.draw(message);
//	}
	
	//Score getter and setter
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
