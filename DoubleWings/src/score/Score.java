/*********************************************************
  * File: Score.java
  * Purpose: Score class implementation
  ********************************************************/

package score;

import org.apache.log4j.Logger;

import hud.HudScore;

/**
 * This class represent the increment of score for each destruction meteor. 
 * It's necessary because the feature needs count the final score.
 */
public class Score {
	
	boolean errorOcurred = false;
	final static Logger logger = Logger.getLogger(Score.class);
	/**
	 * This variable store the value of counter.
	 * It have unlimited.
	 */
	private int currentScore = 0; {
	assert(currentScore == 0):("Pontuação inicial deve ser zero!");
	}

	/**
	 * This method realize the increment of score for to count all score types
	 * @param score value of player score
	 */
	public void increaseScore(ScoreType score) {
		try {
			assert(score != null);
			this.currentScore += score.getValue();
		}
		catch(NullPointerException exception) {
			logger.error("Score object can't be null!", exception);
			exception.printStackTrace();
			errorOcurred = true;
		}
	}

	/**
	 * This method is used for get the result of count of scores
	 * @return currentScore a integer with the amount count of scores
	 */
	public int getCount() {
		return this.currentScore;
	}
}
