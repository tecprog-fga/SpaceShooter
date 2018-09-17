package score;

/**
 * This class represent the increment of score for each destruction meteor. 
 * It's necessary because the feature needs count the final score.
 */
public class Score {
	private int count = 0;

	public void increaseScore(ScoreType score) {
		this.count += score.getValue();
	}

	public int getCount() {
		return this.count;
	}
}
