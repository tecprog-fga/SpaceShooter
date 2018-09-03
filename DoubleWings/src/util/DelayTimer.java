package util;

import java.util.Timer;
import java.util.TimerTask;

public class DelayTimer extends Timer implements DelayDelegate {

	private DelayDelegate delegate = null;
	private int type = 0;

	public DelayTimer(DelayDelegate delegate, int type) {
		
		this.type = type;
		this.delegate = delegate;
	}

	public void schedule(long delay) {
		
		Task task = null;
		task = new Task(this);
		this.schedule(task, delay);
	}

	@Override
	public void delayEnded(DelayTimer timer) {
		
		delegate.delayEnded(this);
	}

	public int getType() {
		
		return type;
	}

	private class Task extends TimerTask {

		private DelayDelegate delegate = null;

		public Task(DelayDelegate delegate) {
			this.delegate = delegate;
		}

		public void run() {
			
			delegate.delayEnded(null);
		}
	}
}
