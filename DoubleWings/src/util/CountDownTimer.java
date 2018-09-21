/**
 * File: CountDownTimer.java
 * Purpose: Counter the time in the game
 */

package util;

import java.util.TimerTask;
import util.CountDownTimerEnds;

/**
 * This class is responsible to counter the time  and
 * delegate the action of finish the scene
 */

public class CountDownTimer extends TimerTask {

	int counterTime = 9;
	public CountDownTimerEnds delegateAction = null;

	// This method is extended of the library java.util.TimerTask. This method cannot be renamed
	@Override
	public void run() {

		counterTime -= 1;
		delegateAction.updateImageForIndex(counterTime);
		
		// This selection structure is used to show the screen game over if counterTime is less than zero
		if (counterTime < 0) {
			
			delegateAction.finishScene();
			delegateAction = null;
			this.cancel();
		}
	}
}
