/*********************************************************
  * File: ScoreType.java
  * Purpose: ScoreType class implementation
  ********************************************************/

package score;

/**
 * This enumerate represent the types of score. 
 * Depending of size meteor destroyer there are a specific representation.
 */
public enum ScoreType {
	LOW(15), 
	MEDIUM(50), 
	HIGH(100);

	/**
	 * This variable store the value of score.
	 * 15 = Low
	 * 50 = Medium
	 * 100 = High
	 */
	private int scoreValue = 0;

	/**
	 * This constructor method, create the score that will be used in the game
	 * @param scoreValue score for each dead enemy
	 */
	ScoreType(int scoreValue) {
		assert(scoreValue >= 0):("Pontuação não pode ser negativa!");
		this.scoreValue = scoreValue;
	}

	/**
	 * This method that returns the score value gained by eliminating an enemy
	 * @return scoreValue
	 */
	public int getValue() {
		return this.scoreValue;
	}

}