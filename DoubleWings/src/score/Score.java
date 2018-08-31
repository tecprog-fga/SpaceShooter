package score;

public class Score {
	private int currentScore = 0;

	public void increaseScore(ScoreType score) {
		this.currentScore += score.getValue();
	}

	public int getCurrentScore() {
		return this.currentScore;
	}
}