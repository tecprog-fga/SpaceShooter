/*********************************************************
  * File: Axis.java
  * Purpose: Entity axis for define calculate and validate of position of sprite
  ********************************************************/

package util;

import constants.WindowConstants;
import jplay.Sprite;

/**
 * This class represent the axis of any sprite object
 */
public class SpritePosition {

	//constructor empty
	public SpritePosition() {
	}
	
	/**
	 * This method realize calculation of axis position
	 * @param window
	 * @param windowDividend
	 * @param sprite
	 * @param screenDividend
	 * @return pos - position calculated
	 */
	public double calculatePosition(int window, double windowDividend, Sprite sprite, double screenDividend) {
		double pos;
		pos = 0;
		
		if(validatePosition(window, windowDividend, screenDividend)) {
			if (window == WindowConstants.WIDTH) {
				pos = window/windowDividend - sprite.width/screenDividend;
			}
			else {
				pos = window/windowDividend - sprite.height/screenDividend;
			}
		}
		else {
			assert(validatePosition(window, windowDividend, screenDividend)):("Position is not valid");
		}
		return pos;
	}
	
	private boolean validatePosition(int window, double windowDividend, double screenDividend) {
		boolean posValid;
		posValid = true;
		
		if (window < 0 || windowDividend < 0 || screenDividend < 0) {
			posValid = false;
		}
		else {
			posValid = true;
		}
		return posValid;
	}
}
