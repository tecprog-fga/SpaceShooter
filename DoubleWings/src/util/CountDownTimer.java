/**
 * File: CountDownTimer.java
 * Purpose: Counter the time in the game
 */

package util;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import scenes.Lose;
import util.CountDownTimerEnds;

/**
 * This class is responsible to counter the time  and
 * delegate the action of finish the scene
 */

public class CountDownTimer extends TimerTask {

	int counterTime = 9; // initializing the variable with value 9
	public CountDownTimerEnds delegateAction = null;
	boolean errorOccurred = false;
	final static Logger logger = Logger.getLogger(Lose.class);
	
	// This method is extended of the library java.util.TimerTask, this method run the counter down 
	@Override
	public void run() {

		// Decrementing counterTime one by one to update the screen with a new image
		counterTime -= 1;
		try {
			assert(counterTime >= -1);
			assert(counterTime <= 9);
			delegateAction.updateImageForIndex(counterTime);
			logger.debug("It was delegated the index "+ counterTime + " in counter");
		}
		catch(IllegalArgumentException exception) {
			logger.error("Invalid counterTime value ", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		
		
		// This selection structure is used to show the screen game over if counterTime is less than zero
		if (counterTime < 0) {
			delegateAction.finishScene();
			logger.info("The scene was finished");
			delegateAction = null;
			this.cancel();
		}
		else {
			//Nothing to do
		}
	}
}
