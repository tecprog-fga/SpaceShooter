/**
 * File: OptionsMenu.java
 * Purpose: Generate enumerators based on actions of game
 */

package scenes.menu;

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
	 * @param currentOption
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
		if (currentOption < 3) {
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
		if (currentOption > 0) {
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
		switch(chosenOption) {
			case 0:
				return OptionsMenu.START_GAME;
	
			case 1:
				return OptionsMenu.RANKING;
	
			case 2:
				return OptionsMenu.SETTINGS;
	
			case 3:
				return OptionsMenu.QUIT;
		}

		return OptionsMenu.START_GAME;
	}

}