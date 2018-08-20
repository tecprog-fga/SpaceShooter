package Score;

public class Score {
	private int count = 0;
	
	public void increaseScore(ScoreType score) {
		this.count += score.getValue();
	}
	
	public int getCount() {
		return this.count;
	}
}
