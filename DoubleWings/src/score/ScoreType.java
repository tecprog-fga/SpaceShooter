package score;

public enum ScoreType {
	LOW(15), MEDIUM(50), HIGH(100);
	/**
	 * The score value gained by eliminating an enemy
	 */
	int scoreValue = 0;

	ScoreType(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	public int getValue() {
		return this.scoreValue;
	}

}