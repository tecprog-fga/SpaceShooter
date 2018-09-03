package scenes.menu;

import scenes.ContinueGame;
import scenes.GameScene;
import scenes.stages.stage1.FirstStage;

import jplay.GameImage;
import jplay.Keyboard;

import java.util.ArrayList;

import constants.WindowConstants;
import jplay.Sprite;

// MenuScene 
public class MenuScene extends GameScene {
	private static final int DISTANCE_BETWEEN_BUTTONS = WindowConstants.HEIGHT/48;

	private OptionsMenu selectedMenuOption = OptionsMenu.START_GAME;//Define initial menu option

	protected void buildInitialScene() {

		//Reset option menu
		selectedMenuOption = OptionsMenu.START_GAME;

		//Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}

	private GameImage background = null;
	private Sprite title = null;
	private Sprite arrow = null;
	
	protected void viewSetup() {
		//Define Scene elements
		final String BACKGROUNG_PATH = "src/assets/img/menu/background.png";
		background = new GameImage(BACKGROUNG_PATH);

		final String TITLE_PATH = "src/assets/img/menu/title.png";
		title = new Sprite(TITLE_PATH);
		title.x = WindowConstants.WIDTH/2 - title.width/2;
		title.y = WindowConstants.HEIGHT/3 - title.height/2;

		//Define buttons position
		appendButtons();
		final String ARROW_PATH = "src/assets/img/menu/arrow.png";
		arrow = new Sprite(ARROW_PATH);
		arrow.x = 10;
		arrow.y = 10;
	}

	private ArrayList<Sprite> buttons = new ArrayList<Sprite>();

	//Add buttons to array
	private void appendButtons() {
		final String START_BUTTON_PATH = "src/assets/img/menu/start_button.png";
		Sprite startButton = null;
		startButton = new Sprite(START_BUTTON_PATH);
		final String RANKING_PATH = "src/assets/img/menu/ranking.png";
		Sprite rankingButton = null;
		rankingButton = new Sprite(RANKING_PATH);		
		final String SETTINGS_PATH = "src/assets/img/menu/settings.png";
		Sprite settingsButton = null;
		settingsButton = new Sprite(SETTINGS_PATH);
		final String QUIT_PATH = "src/assets/img/menu/quit.png";
		Sprite quitButton = null;
		quitButton = new Sprite(QUIT_PATH);

		buttons.add(startButton);
		buttons.add(rankingButton);
		buttons.add(settingsButton);
		buttons.add(quitButton);

		for(OptionsMenu option : OptionsMenu.values()) {
			int currentButtonIndex = option.ordinal(); //Integer value of variable option

			//Define the position of the first element according to the title
			if(currentButtonIndex == 0) {
				buttons.get(currentButtonIndex).x = WindowConstants.WIDTH/2 - startButton.width/2;
				final int DISTANCE_TITLE_BUTTON = WindowConstants.HEIGHT/24;
				buttons.get(currentButtonIndex).y = title.y + title.height + DISTANCE_TITLE_BUTTON;
				//Define the position of the element according to the last element
			} else {
				buttons.get(currentButtonIndex).x = buttons.get(currentButtonIndex - 1).x;
				buttons.get(currentButtonIndex).y = buttons.get(currentButtonIndex - 1).y + buttons.get(currentButtonIndex - 1).height + DISTANCE_BETWEEN_BUTTONS;
			}
		}
	}

	//Check keyboard and update Menu option
	private void checkMenuOption() {
		// Down selection
		if (keyboard.keyDown(Keyboard.DOWN_KEY)) {
			String MSG_DOWN = "down";
			System.out.println(MSG_DOWN);
			//Change current menu option
			selectedMenuOption = selectedMenuOption.next();
			System.out.println(selectedMenuOption);
		}

		// Up selection		
		if (keyboard.keyDown(Keyboard.UP_KEY)) {
			String MSG_UP = "up";
			System.out.println(MSG_UP);
			//Change current menu option
			selectedMenuOption = selectedMenuOption.back();
			System.out.println(selectedMenuOption);
		}
	}

	private void currentArrow() {
		int currentButtonIndex = 0;
		currentButtonIndex = this.selectedMenuOption.ordinal();

		Sprite currentButton = null;
		currentButton = this.buttons.get(currentButtonIndex);

		this.arrow.x = currentButton.x - arrow.width - DISTANCE_BETWEEN_BUTTONS;
		this.arrow.y = currentButton.y;

	}

	//Draw scene elements
	private void drawScenes() {
		background.draw();
		title.draw();
		arrow.draw();

		for(Sprite button: this.buttons) {
			button.draw();
		}
	}

	private static GameScene firstLevel = null;

	public GameScene firstStageScene() {
		if(firstLevel == null){
			firstLevel = new FirstStage();
			return firstLevel;
		} 	
		else{
			return firstLevel;
		}

	}

	//Check keyboard enter and dispatch new scene
	private void checkButtonSelection() {
		if (keyboard.keyDown(Keyboard.ENTER_KEY)){

			switch(selectedMenuOption){

			case START_GAME:
				//transit to game
				game.transitTo(firstStageScene());
				break;
			case RANKING:
				//transit to ranking
				break;
			case SETTINGS:
				//transit to settings
				break;
			case QUIT:
				//quit game
				game.quit();
				break;
			}
		}
	}

	public void updateScene() {

		// Control menu option selection
		checkMenuOption();

		//Define current arrow position
		currentArrow();

		checkButtonSelection();

		// Draw menu elements
		drawScenes();
	}
}
