/*********************************************************
  * File: Axis.java
  * Purpose: Entity axis for define calculate and validate of position of sprite
  ********************************************************/

package util;

import org.apache.log4j.Logger;

import constants.WindowConstants;
import jplay.Sprite;
import scenes.ErrorScene;

/**
 * This class represent the axis of any sprite object
 */
public class SpritePosition {

	boolean errorOccurred = false;
	final static Logger logger = Logger.getLogger(SpritePosition.class);
	
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
			pos = checkDimension(window, windowDividend, sprite, screenDividend);
		}
		else {
			try {
				assert(validatePosition(window, windowDividend, screenDividend));				
			}
			catch(IllegalArgumentException exception) {
				logger.error("Error, an exception occurred:", exception);
				exception.printStackTrace();
				errorOccurred = true;
			}
			catch(ArithmeticException exception) {
				logger.error("Error, an exception occurred:", exception);
				exception.printStackTrace();
				errorOccurred = true;
			}
		}
		return pos;
	}

	private double checkDimension(int window, double windowDividend, Sprite sprite, double screenDividend) {
		double pos;
		if (window == WindowConstants.WIDTH) {
			pos = window/windowDividend - sprite.width/screenDividend;
		}
		else {
			pos = window/windowDividend - sprite.height/screenDividend;
		}
		return pos;
	}
	
	/**
	 * This method valid position params
	 * @param window
	 * @param windowDividend
	 * @param screenDividend
	 * @return position valid
	 */
	public boolean validatePosition(int window, double windowDividend, double screenDividend) {
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
