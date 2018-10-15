/**
 * File: CountDownTimerEnds.java
 * Purpose: Responsible for delegate the action of finish the scene.
 */

package util;

/**
 * This interface is responsible for delegate the action of finish the scene.
 */

public interface CountDownTimerEnds {
	// This method is necessary for finish scene of counter
	public void finishScene();
	// This method is necessary for update index of image 
	public void updateImageForIndex(int index);
}
