/**
 * File: ScoreType.java
 * Purpose: Generate enumerators what define types of score
 */

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
	int scoreValue = 0;

	/**
	 * This constructor initialize the score value
	 * @param scoreValue
	 */
	ScoreType(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	/**
	 * This method is used for get the type of scores
	 * @return a integer with the value of type of scores
	 */
	public int getValue() {
		return this.scoreValue;
	}

}