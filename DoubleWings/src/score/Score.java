package score;

/**
 * This class represent the increment of score for each destruction meteor. 
 * It's necessary because the feature needs count the final score.
 */
public class Score {
	private int count = 0;

	/**
	 * This method realize the increment of score for to count all score types
	 * @param score
	 */
	public void increaseScore(ScoreType score) {
		this.count += score.getValue();
	}

	/**
	 * This method is used for get the result of count of scores
	 * @return a integer with the amount count of scores
	 */
	public int getCount() {
		return this.count;
	}
}
