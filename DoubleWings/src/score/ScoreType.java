/*********************************************************
  * File: ScoreType.java
  * Purpose: ScoreType class implementation
  ********************************************************/

package score;

import org.apache.log4j.Logger;

import hud.HudScore;

/**
 * This enumerate represent the types of score. 
 * Depending of size meteor destroyer there are a specific representation.
 */
public enum ScoreType {
	LOW(15), 
	MEDIUM(50), 
	HIGH(100);
	
	boolean errorOcurred = false;
	final Logger logger = Logger.getLogger(ScoreType.class);

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
		try {
			assert(scoreValue >= 0);
			this.scoreValue = scoreValue;
		}
		catch(IllegalArgumentException exception) {
			logger.error("Score must be positive!", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}

	/**
	 * This method that returns the score value gained by eliminating an enemy
	 * @return scoreValue
	 */
	public int getValue() {
		return this.scoreValue;
	}

}