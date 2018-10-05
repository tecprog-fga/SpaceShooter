/*********************************************************
  * File: Score.java
  * Purpose: Score class implementation
  ********************************************************/

package score;

/**
 * This class represent the increment of score for each destruction meteor. 
 * It's necessary because the feature needs count the final score.
 */
public class Score {
	/**
	 * This variable store the value of counter.
	 * It have unlimited.
	 */
	private int currentScore = 0;

	/**
	 * This method realize the increment of score for to count all score types
	 * @param score value of player score
	 */
	public void increaseScore(ScoreType score) {
		this.currentScore += score.getValue();
	}

	/**
	 * This method is used for get the result of count of scores
	 * @return currentScore a integer with the amount count of scores
	 */
	public int getCount() {
		return this.currentScore;
	}
}
