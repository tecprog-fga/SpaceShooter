package scenes.menu;

enum OptionMenu {
	Start_Game(0), 
	Ranking(1), 
	Settings(2), 
	Quit(3);

	private final int currentOption; //Current option on menu

	OptionMenu(int currentOption){
		this.currentOption = currentOption;
	}

	OptionMenu next(){

		int chosenOption = currentOption;
		
		if (currentOption < 3){
			chosenOption += 1;
		}

		return OptionMenu(chosenOption);
	}

	OptionMenu back(){

		int chosenOption = currentOption;
		if (currentOption > 0){
			chosenOption -= 1;
		}

		return OptionMenu(chosenOption);
	}

	private OptionMenu OptionMenu(int chosenOption) {

		switch(chosenOption){

		case 0:
			return OptionMenu.Start_Game;

		case 1:
			return OptionMenu.Ranking;

		case 2:
			return OptionMenu.Settings;

		case 3:
			return OptionMenu.Quit;
		}

		return OptionMenu.Start_Game;
	}

}
