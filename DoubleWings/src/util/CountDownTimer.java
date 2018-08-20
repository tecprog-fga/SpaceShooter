package util;

import java.util.TimerTask;
import util.CountDownTimerEnds;

public class CountDownTimer extends TimerTask {
	
	int count = 9;
	public CountDownTimerEnds delegate;

	@Override
	public void run() {
		
		count -= 1;
		delegate.updateImageForIndex(count);
		if(count < 0) {
			delegate.terminate();
			delegate = null;
			this.cancel();
		}
	}

}
