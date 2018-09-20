package score;

public enum ScoreType {
	/**
	 * scores that can be made in the game to every downed enemy
	 */
	LOW(15), MEDIUM(50), HIGH(100);
	/**
	 * The score value gained by eliminating an enemy
	 */
	int scoreValue = 0;

	/**
	 * class constructor method, create the score that will be used in the game
	 * @param scoreValue score for each dead enemy
	 */
	ScoreType(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	/**
	 * Class that returns the score value gained by eliminating an enemy
	 * @return scoreValue
	 */
	public int getValue() {
		return this.scoreValue;
	}

}