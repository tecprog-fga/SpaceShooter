package util;

import java.util.TimerTask;
import util.CountDownTimerEnds;

public class CountDownTimer extends TimerTask {

	int counterTime = 9;
	public CountDownTimerEnds delegateAction = null;

	@Override
	public void run() {

		counterTime -= 1;
		delegateAction.updateImageForIndex(counterTime);
		
		if (counterTime < 0) {
			
			delegateAction.finishScene();
			delegateAction = null;
			this.cancel();
		}
	}
}
