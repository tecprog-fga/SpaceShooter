/**
 * File: OptionsMenu.java
 * Purpose: Generate enumerators based on actions of game
 */

package scenes.menu;

import jplay.Keyboard;

/**
 * This Enum create enumerations of options of menu in menu display. 
 * For each code id there is a option for choose.
 */
enum OptionsMenu {
	START_GAME(0), 
	RANKING(1), 
	SETTINGS(2), 
	QUIT(3);

	/**
	 * Current option on menu.
	 */
	private final int currentOption; 

	/**
	 * This constructor initialize the current option
	 * @param currentOption defined
	 */
	OptionsMenu(int currentOption) {
		this.currentOption = currentOption;
	}

	/**
	 * This algorithm build the next option when the current option is less than 3
	 * @return next option defined
	 */
	OptionsMenu next() {
		int chosenOption = currentOption;
		assert(currentOption <= 3):("Returned unexpected value");
		if (currentOption <= 3) {
			chosenOption += 1;
		}
		
		return defineOption(chosenOption);
	}

	/**
	 * This algorithm build the back option when the current option is greater than 0
	 * @return back option defined
	 */
	OptionsMenu back() {

		int chosenOption = currentOption;
		assert(currentOption >= 0):("Returned unexpected value");
		if (currentOption >= 0) {
			chosenOption -= 1;
		}

		return defineOption(chosenOption);
	}

	/**
	 * This method define the chosen option for return the enumerator related  
	 * @param chosenOption
	 * @return Enumerator related
	 */
	private OptionsMenu defineOption(int chosenOption) {
		
		//Depending of chosen option realize action related
		assert(chosenOption == 0 || chosenOption <= 3):("Returned unexpected value of chosen option");
		switch(chosenOption) {
			case 0:
				assert(chosenOption == 0):("Returned unexpected value of chosen option");
				return OptionsMenu.START_GAME;
			case 1:
				assert(chosenOption == 1):("Returned unexpected value of chosen option");
				return OptionsMenu.RANKING;
			case 2:
				assert(chosenOption == 2):("Returned unexpected value of chosen option");
				return OptionsMenu.SETTINGS;
			case 3:
				assert(chosenOption == 3):("Returned unexpected value of chosen option");
				return OptionsMenu.QUIT;
		}
		
		assert(chosenOption == 0):("Returned unexpected value of chosen option");
		return OptionsMenu.START_GAME;
	}

}