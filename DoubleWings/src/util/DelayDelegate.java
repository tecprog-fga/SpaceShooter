/**
 * File: DelayDelegate.java
 * Purpose: Responsible for delay the timer.
 */

package util;

import util.DelayTimer;

/**
 * This interface is responsible for delay the timer .
 */
public interface DelayDelegate {
	//method for delay time of counter
	public void delayEnded(DelayTimer timer);
}
