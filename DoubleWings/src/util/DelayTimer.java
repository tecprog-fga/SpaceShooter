/**
 * File: DelayTimer.java
 * Purpose: Delay the timer
 */

package util;

import java.util.Timer;
import java.util.TimerTask;

// This class is responsible to to delay the timer if is necessary
public class DelayTimer extends Timer implements DelayDelegate {

	private DelayDelegate delegate = null;
	private int type = 0;

	/**
	 * This method is responsible to delegate the delay and the type
	 * @param delegate
	 * @param type
	 */
	public DelayTimer(DelayDelegate delegate, int type) {
		
		this.type = type;
		this.delegate = delegate;
	}

	/**
	 * This void method is responsible to take the task and delay
	 * @param delay
	 */
	public void schedule(long delay) {
		
		Task task = null;
		task = new Task(this);
		this.schedule(task, delay);
	}

	/**
	 * This void method is responsible to end the delay according to the time
	 * @param timer
	 */
	@Override
	public void delayEnded(DelayTimer timer) {
		
		delegate.delayEnded(this);
	}

	public int getType() {
		
		return type;
	}

	//This private class is responsible to take the task and delegate an action
	private class Task extends TimerTask {

		private DelayDelegate delegate = null;

		/**
		 * This method is responsible to delegate according to the delay
		 * @param delegate
		 */
		public Task(DelayDelegate delegate) {
			this.delegate = delegate;
		}

		// This method is extended of the library java.util.TimerTask. This method cannot be renamed
		public void run() {
			
			delegate.delayEnded(null);
		}
	}
}
