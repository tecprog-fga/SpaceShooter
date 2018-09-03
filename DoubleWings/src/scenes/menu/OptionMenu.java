package scenes.menu;

enum OptionsMenu {
	START_GAME(0), 
	RANKING(1), 
	SETTINGS(2), 
	QUIT(3);

	private final int currentOption; //Current option on menu

	OptionsMenu(int currentOption){
		this.currentOption = currentOption;
	}

	OptionsMenu next(){

		int chosenOption = currentOption;
		if (currentOption < 3){
			chosenOption += 1;
		}

		return OptionsMenu(chosenOption);
	}

	OptionsMenu back(){

		int chosenOption = currentOption;
		if (currentOption > 0){
			chosenOption -= 1;
		}

		return OptionsMenu(chosenOption);
	}

	private OptionsMenu OptionsMenu(int chosenOption) {

		switch(chosenOption){

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
