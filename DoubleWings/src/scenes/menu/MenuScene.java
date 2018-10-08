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
		assert(Keyboard.UP_KEY == 38):("Returned unexpected value");
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
		background = new GameImage(BACKGROUNG_PATH);
		assert(background != null):("This object returned null");
		assert(background instanceof GameImage):("Error instantiating GameImage class");
		
		//Realize the build for have title image in screen.
		final String TITLE_PATH = "src/assets/img/menu/title.png";
		title = new Sprite(TITLE_PATH);
		assert(title != null):("This object returned null");
		assert(title instanceof Sprite):("Error instantiating Sprite class");
		
		title.x = WindowConstants.WIDTH/2 - title.width/2;
		title.y = WindowConstants.HEIGHT/3 - title.height/2;

		//Define buttons position
		appendButtons();
		
		//Realize the build for have arrow image in screen.
		final String ARROW_PATH = "src/assets/img/menu/arrow.png";
		arrow = new Sprite(ARROW_PATH);
		assert(arrow != null):("This object returned null");
		assert(arrow instanceof Sprite):("Error instantiating Sprite class");
		
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
		Sprite startButton = null;
		startButton = new Sprite(START_BUTTON_PATH);
		assert(startButton instanceof Sprite):("Error instantiating Sprite class");
		
		final String RANKING_PATH = "src/assets/img/menu/ranking.png";
		Sprite rankingButton = null;
		rankingButton = new Sprite(RANKING_PATH);		
		assert(rankingButton instanceof Sprite):("Error instantiating Sprite class");
		
		final String SETTINGS_PATH = "src/assets/img/menu/settings.png";
		Sprite settingsButton = null;
		settingsButton = new Sprite(SETTINGS_PATH);
		assert(settingsButton instanceof Sprite):("Error instantiating Sprite class");
		
		final String QUIT_PATH = "src/assets/img/menu/quit.png";
		Sprite quitButton = null;
		quitButton = new Sprite(QUIT_PATH);
		assert(quitButton instanceof Sprite):("Error instantiating Sprite class");

		
		//Add this images in buttons really
		assert(startButton != null):("This object returned null");
		buttons.add(startButton);
		assert(rankingButton != null):("This object returned null");
		buttons.add(rankingButton);
		assert(settingsButton != null):("This object returned null");
		buttons.add(settingsButton);
		assert(quitButton != null):("This object returned null");
		buttons.add(quitButton);

		for(OptionsMenu option : OptionsMenu.values()) {
			//Integer value of variable option
			int currentButtonIndex = option.ordinal(); 
			//Define the position of the first element according to the title
			if(currentButtonIndex == 0) {
				buttons.get(currentButtonIndex).x = WindowConstants.WIDTH/2 - startButton.width/2;
				final int DISTANCE_TITLE_BUTTON = WindowConstants.HEIGHT/24;
				
				assert(currentButtonIndex >= 0):("The index of button dont should be negative");
				assert(currentButtonIndex <= 1000):("The index of button dont should be 1000 or more");
				buttons.get(currentButtonIndex).y = title.y + title.height + DISTANCE_TITLE_BUTTON;
			} else { 
				//Define the position of the element according to the last element
				assert(currentButtonIndex >= 0):("The index of button dont should be negative");
				assert(currentButtonIndex <= 1000):("The index of button dont should be 1000 or more");
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
		assert(Keyboard.DOWN_KEY == 40):("Returned unexpected value");
		if (keyboard.keyDown(Keyboard.DOWN_KEY)) {
			String MSG_DOWN = "down";
			System.out.println(MSG_DOWN);
			
			//Change current menu option
			assert(selectedMenuOption != null):("This object returned null");
			selectedMenuOption = selectedMenuOption.next();
			System.out.println(selectedMenuOption);
		}

		//Up selection		
		assert(Keyboard.UP_KEY == 38):("Returned unexpected value");
		if (keyboard.keyDown(Keyboard.UP_KEY)) {
			String MSG_UP = "up";
			System.out.println(MSG_UP);
			//Change current menu option
			assert(selectedMenuOption != null):("This object returned null");
			selectedMenuOption = selectedMenuOption.back();
			System.out.println(selectedMenuOption);
		}
	}

	/**
	 * This method build the objects necessaries for define 
	 * the current arrow of keyboard.
	 */
	private void currentArrow() {
		//Value of current button index. 0 = start button;1 = ranking button;2 = settings button;3 = quit button
		int currentButtonIndex = 0;
		assert(selectedMenuOption != null):("This object returned null");
		currentButtonIndex = this.selectedMenuOption.ordinal();

		Sprite currentButton = null;
		assert(currentButtonIndex >= 0):("The index of button dont should be negative");
		currentButton = this.buttons.get(currentButtonIndex);

		assert(currentButtonIndex >= 0):("The current of button dont should be negative");
		this.arrow.x = currentButton.x - arrow.width - DISTANCE_BETWEEN_BUTTONS;
		this.arrow.y = currentButton.y;
	}

	/**
	 * Build design of menu with the union of elements for show on screen.
	 */
	private void drawScenes() {
		
		assert(background != null):("This object returned null");
		background.draw();
		
		assert(title != null):("This object returned null");
		title.draw();
		
		assert(arrow != null):("This object returned null");
		arrow.draw();

		assert(buttons != null):("This object returned null");
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
			
			assert(firstLevel != null):("This object returned null");
			return firstLevel;
		} 	
		else{
			
			assert(firstLevel != null):("This object returned null");
			return firstLevel;
		}
	}

	/**
	 * Check keyboard enter for dispatch new scene.
	 * Depends of selected menu option the scene it's transited.
	 */
	private void checkButtonSelection() {
		assert(Keyboard.ENTER_KEY == 10):("Returned unexpected value");
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