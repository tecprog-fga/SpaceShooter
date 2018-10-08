/**
 * File: MenuScene.java
 * Purpose: Build the menu scene
 */

package scenes.menu;

import scenes.ContinueGame;
import scenes.GameScene;
import scenes.stages.stage1.FirstStage;

import jplay.GameImage;
import jplay.Keyboard;

import java.util.ArrayList;

import constants.WindowConstants;
import jplay.Sprite;

/**
 * This class build scene for menu screen. It's necessary because 
 * this feature needs to show the movement scene in the screen.
 */
public class MenuScene extends GameScene {
	/**
	 * This constant store the result of calculation of distance between buttons
	 */
	private static final int DISTANCE_BETWEEN_BUTTONS = WindowConstants.HEIGHT/48;
	/**
	 * Define initial menu option of selection menu
	 */
	private OptionsMenu selectedMenuOption = OptionsMenu.START_GAME;
	
	//This void method was declarated in GameScene abstract class, it used for configurated the scene
	protected void buildInitialScene() {

		//Reset option menu, when start game is ativated
		selectedMenuOption = OptionsMenu.START_GAME;

		//Configure up and down keys for detect press
		assert(Keyboard.DOWN_KEY == 40):("Returned unexpected value");
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		assert(Keyboard.DOWN_KEY == 38):("Returned unexpected value");
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}
	/**
	 * Background object for construct background scenes
	 */
	private GameImage background = null;
	/**
	 * Title object for construct title sprite
	 */
	private Sprite title = null;
	/**
	 * Arrow object for construct arrow sprite
	 */
	private Sprite arrow = null;
	
	//This void method build the sprites for show background and title 
	protected void viewSetup() {
		
		//Realize the build for have background image in screen.
		final String BACKGROUNG_PATH = "src/assets/img/menu/background.png";
		assert(BACKGROUNG_PATH == "src/assets/img/menu/background.png"):("Invalid path returned");
		background = new GameImage(BACKGROUNG_PATH);

		//Realize the build for have title image in screen.
		final String TITLE_PATH = "src/assets/img/menu/title.png";
		assert(TITLE_PATH == "src/assets/img/menu/title.png"):("Invalid path returned");
		title = new Sprite(TITLE_PATH);
		title.x = WindowConstants.WIDTH/2 - title.width/2;
		title.y = WindowConstants.HEIGHT/3 - title.height/2;

		//Define buttons position
		appendButtons();
		
		//Realize the build for have arrow image in screen.
		final String ARROW_PATH = "src/assets/img/menu/arrow.png";
		assert(ARROW_PATH == "src/assets/img/menu/arrow.png"):("Invalid path returned");
		arrow = new Sprite(ARROW_PATH);
		arrow.x = 10;
		arrow.y = 10;
	}

	/**
	 * This object represent a array for build buttons
	 */
	private ArrayList<Sprite> buttons = new ArrayList<Sprite>();

	/**
	 * This method create the buttons with the sprites of buttons for define 
	 * buttons positions.
	 */
	private void appendButtons() {
		
		//Realize the build for have buttons image in screen.
		final String START_BUTTON_PATH = "src/assets/img/menu/start_button.png";
		assert(START_BUTTON_PATH == "src/assets/img/menu/start_button.png"):("Invalid path returned");
		Sprite startButton = null;
		startButton = new Sprite(START_BUTTON_PATH);
		
		final String RANKING_PATH = "src/assets/img/menu/ranking.png";
		assert(RANKING_PATH == "src/assets/img/menu/ranking.png"):("Invalid path returned");
		Sprite rankingButton = null;
		rankingButton = new Sprite(RANKING_PATH);
		
		final String SETTINGS_PATH = "src/assets/img/menu/settings.png";
		assert(SETTINGS_PATH == "src/assets/img/menu/settings.png"):("Invalid path returned");
		Sprite settingsButton = null;
		settingsButton = new Sprite(SETTINGS_PATH);
		
		final String QUIT_PATH = "src/assets/img/menu/quit.png";
		assert(QUIT_PATH == "src/assets/img/menu/quit.png"):("Invalid path returned");
		Sprite quitButton = null;
		quitButton = new Sprite(QUIT_PATH);

		
		//Add this images in buttons really
		assert(startButton == null):("Sprite null returned");
		buttons.add(startButton);
		assert(rankingButton == null):("Sprite null returned");
		buttons.add(rankingButton);
		assert(settingsButton == null):("Sprite null returned");
		buttons.add(settingsButton);
		assert(quitButton == null):("Sprite null returned");
		buttons.add(quitButton);

		for(OptionsMenu option : OptionsMenu.values()) {
			//Integer value of variable option
			int currentButtonIndex = option.ordinal(); 
			//Define the position of the first element according to the title
			if(currentButtonIndex == 0) {
				buttons.get(currentButtonIndex).x = WindowConstants.WIDTH/2 - startButton.width/2;
				final int DISTANCE_TITLE_BUTTON = WindowConstants.HEIGHT/24;
				buttons.get(currentButtonIndex).y = title.y + title.height + DISTANCE_TITLE_BUTTON;
			} else { 
				//Define the position of the element according to the last element
				buttons.get(currentButtonIndex).x = buttons.get(currentButtonIndex - 1).x;
				buttons.get(currentButtonIndex).y = buttons.get(currentButtonIndex - 1).y + buttons.get(currentButtonIndex - 1).height + DISTANCE_BETWEEN_BUTTONS;
			}
		}
	}

	/**
	 * This method to check keyboard for control the menu selection.
	 * It's limited with Down and Up key 
	 */
	private void checkMenuOption() {
		//Down selection
		if (keyboard.keyDown(Keyboard.DOWN_KEY)) {
			String MSG_DOWN = "down";
			System.out.println(MSG_DOWN);
			
			//Change current menu option
			selectedMenuOption = selectedMenuOption.next();
			System.out.println(selectedMenuOption);
		}

		//Up selection		
		if (keyboard.keyDown(Keyboard.UP_KEY)) {
			String MSG_UP = "up";
			System.out.println(MSG_UP);
			//Change current menu option
			selectedMenuOption = selectedMenuOption.back();
			System.out.println(selectedMenuOption);
		}
	}

	/**
	 * This method build the objects necessaries for define 
	 * the current arrow of keyboard.
	 */
	private void currentArrow() {
		//Value of current button index. 0 = start button;1 = ranking buton;2 = settings button;3 = quit button
		int currentButtonIndex = 0;
		currentButtonIndex = this.selectedMenuOption.ordinal();

		Sprite currentButton = null;
		currentButton = this.buttons.get(currentButtonIndex);

		this.arrow.x = currentButton.x - arrow.width - DISTANCE_BETWEEN_BUTTONS;
		this.arrow.y = currentButton.y;
	}

	/**
	 * Build design of menu with the union of elements for show on screen.
	 */
	private void drawScenes() {
		background.draw();
		title.draw();
		arrow.draw();

		for(Sprite button: this.buttons) {
			button.draw();
		}
	}

	/**
	 * This object represent the first level.
	 */
	private static GameScene firstLevel = null;

	/**
	 * This method build the scene of first stage of game.
	 * @return object of scene builded of first stage
	 */
	public GameScene firstStageScene() {
		if(firstLevel == null){
			firstLevel = new FirstStage();
			return firstLevel;
		} 	
		else{
			return firstLevel;
		}
	}

	/**
	 * Check keyboard enter for dispatch new scene.
	 * Depends of selected menu option the scene it's transited.
	 */
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

	//Realize the sequence of actions developed.
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