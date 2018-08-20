package Score;

public enum ScoreType {
	LOW(15), MEDIUM(50), HIGH(100);
	
	int scoreValue;
	
	ScoreType(int scoreValue) {
		this.scoreValue = scoreValue;
	}
	
	public int getValue() {
		return this.scoreValue;
	}
	
}
