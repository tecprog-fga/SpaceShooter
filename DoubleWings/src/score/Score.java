/*********************************************************
  * File: Score.java
  * Purpose: Score class implementation
  ********************************************************/

package score;

public class Score {
	/**
	 * Variable that saves the game's current score
	 */
	private int currentScore = 0;

	/**
	 * Method use to calculate game score
	 * @param score value of player score
	 */
	public void increaseScore(ScoreType score) {
		this.currentScore += score.getValue();
	}

	/**
	 * method that returns the game score
	 * @return currentScore current score value
	 */
	public int getCurrentScore() {
		return this.currentScore;
	}
}
