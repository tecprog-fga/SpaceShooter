package scenes.menu;

enum OptionMenu {  
	Start_Game(0), Ranking(1), Settings(2), Quit(3);
	
	private final int value; //Current option on menu
	
	OptionMenu(int option){
		value = option;
	}
	
	OptionMenu next(){
		
		int option = value;
		if (value < 3){
			option += 1;
		}
		
		return OptionMenu(option);
	}

	OptionMenu back(){
		
		int option = value;
		if (value > 0){
			option -= 1;
		}
		
		return OptionMenu(option);
	}

	private OptionMenu OptionMenu(int option) {

		switch(option){

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
