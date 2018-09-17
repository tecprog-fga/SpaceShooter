package score;

public class Score {
	/**
	 * Variable that saves the game's current score
	 */
	private int currentScore = 0;

	public void increaseScore(ScoreType score) {
		this.currentScore += score.getValue();
	}

	public int getCurrentScore() {
		return this.currentScore;
	}
}
