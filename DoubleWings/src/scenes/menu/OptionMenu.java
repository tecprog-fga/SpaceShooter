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
	private OptionsMenu(int currentOption) {
		this.currentOption = currentOption;
	}

	/**
	 * This algorithm build the next option when the current option is less than 3
	 * @return next option defined
	 */
	protected OptionsMenu next() {
		int chosenOption = currentOption;
		OptionsMenu nextOption;
		
		assert(this.currentOption >= 0):("The currentOption dont should be negative");
		assert(currentOption <= 3):("The currentOption dont should be bigger then 3");
		if (currentOption <= 3) {
			chosenOption += 1;
		}
		else {
			//Nothing to do
		}
		nextOption = defineOption(chosenOption);
		
		return nextOption;
	}

	/**
	 * This algorithm build the back option when the current option is greater than 0
	 * @return back option defined
	 */
	protected OptionsMenu back() {

		int chosenOption = currentOption;
		assert(this.currentOption >= 0):("The currentOption dont should be negative");
		assert(this.currentOption <= 3):("The currentOption dont should be bigger then 3");
		if (currentOption >= 0) {
			chosenOption -= 1;
		}
		else {
			//Nothing to do
		}
		
		return defineOption(chosenOption);
	}

	/**
	 * This method define the chosen option for return the enumerator related  
	 * @param chosenOption
	 * @return Enumerator related
	 */
	private OptionsMenu defineOption(int chosenOption) {
		
		OptionsMenu selected;
		
		//Depending of chosen option realize action related
		switch(chosenOption) {
			case 0:
				selected = OptionsMenu.START_GAME;
				break;
			case 1:
				selected = OptionsMenu.RANKING;
				break;
			case 2:
				selected = OptionsMenu.SETTINGS;
				break;
			case 3:
				selected = OptionsMenu.QUIT;
				break;
			default:
				selected = OptionsMenu.START_GAME;
				break;
		}
		return selected;
	}

}