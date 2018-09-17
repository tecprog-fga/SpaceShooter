package score;

/**
 * This enumerate represent the types of score. 
 * Depending of size meteor destroyer there are a specific representation.
 */
public enum ScoreType {
	LOW(15), 
	MEDIUM(50), 
	HIGH(100);

	int scoreValue = 0;

	ScoreType(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	public int getValue() {
		return this.scoreValue;
	}

}