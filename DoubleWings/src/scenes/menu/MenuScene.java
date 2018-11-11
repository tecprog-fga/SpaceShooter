/**

 * File: MenuScene.java
 * Purpose: Build the menu scene
 */

package scenes.menu;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import constants.WindowConstants;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Sprite;
import scenes.ErrorScene;
import scenes.GameScene;
import scenes.stages.stage1.FirstStage;
import util.SpritePosition;

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
	
	boolean errorOccurred = false;
	
	final static Logger logger = Logger.getLogger(SpritePosition.class);
	
	//This void method was declarated in GameScene abstract class, it used for configurated the scene
	public void buildInitialScene() {

		//Reset option menu, when start game is ativated
		selectedMenuOption = OptionsMenu.START_GAME;

		//Configure up and down keys for detect press
		try {
			assert(Keyboard.DOWN_KEY == 40);
			keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
			assert(Keyboard.UP_KEY == 38);
			keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		}
		catch(IllegalArgumentException exception) {
			logger.error("Returned unexpected value", exception);
			exception.printStackTrace();
			errorOccurred = true;
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
	/**
	 * This method build the scene of first stage of game.
	 * @return object of scene builded of first stage
	 */
	public GameScene firstStageScene() {
		
		GameScene stage;
		
		if (firstLevel == null) {
			firstLevel = new FirstStage();
			stage = firstLevel;
		} 	
		else {
			stage = firstLevel;
		}
		
		return stage;
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
	
	/**
	 * This object its necessary for resolve position of sprites features
	 */
	private SpritePosition spos = new SpritePosition();
	
	//This void method build the sprites for show background and title 
	protected void viewSetup() {
		
		//Realize the build for have background image in screen.
		final String BACKGROUNG_PATH = "src/assets/img/menu/background.png";
		background = new GameImage(BACKGROUNG_PATH);
		logger.debug("The sprite was created with path: " + BACKGROUNG_PATH);
		
		//Realize the build for have title image in screen.
		final String TITLE_PATH = "src/assets/img/menu/title.png";
		title = new Sprite(TITLE_PATH);
		logger.debug("The sprite was created with path: " + TITLE_PATH);
		
		try {
			title.x = spos.calculatePosition(WindowConstants.WIDTH, 2, title, 2);
			title.y = spos.calculatePosition(WindowConstants.HEIGHT, 3, title, 2);
		}
		catch(IllegalArgumentException exception) {
			logger.error("Invalid number value ", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		//Define buttons position
		appendButtons();
		
		//Realize the build for have arrow image in screen.
		final String ARROW_PATH = "src/assets/img/menu/arrow.png";
		arrow = new Sprite(ARROW_PATH);
		logger.debug("The sprite was created with path: " + TITLE_PATH);
		
		arrow.x = 10;
		arrow.y = 10;
	}

	/**
	 * This object represent a array for build buttons
	 */
	private ArrayList<Sprite> buttons = new ArrayList<Sprite>();

	/**
	 * This method to check keyboard for control the menu selection.
	 * It's limited with Down and Up key 
	 */
	private void checkMenuOption() {
		//Down selection
		try {
			assert(Keyboard.DOWN_KEY == 40);
			if (keyboard.keyDown(Keyboard.DOWN_KEY)) {
				String MSG_DOWN = "down";
				System.out.println(MSG_DOWN);
				
				//Change current menu option
				assert(this.selectedMenuOption != null):("This object returned null");
				selectedMenuOption = selectedMenuOption.next();
				System.out.println(selectedMenuOption);
			}
			else {
				//Nothing to do
			}
	
			//Up selection		
			assert(Keyboard.UP_KEY == 38);
			if (keyboard.keyDown(Keyboard.UP_KEY)) {
				String MSG_UP = "up";
				System.out.println(MSG_UP);
				//Change current menu option
				assert(this.selectedMenuOption != null):("This object returned null");
				selectedMenuOption = selectedMenuOption.back();
				System.out.println(selectedMenuOption);
			}
			else {
				//Nothing to do
			}
		}
		catch(IllegalArgumentException exception) {
			logger.error("Returned unexpected value ", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
	
	/**
	 * This object represent the first level.
	 */
	private static GameScene firstLevel = null;

	/**
	 * Check keyboard enter for dispatch new scene.
	 * Depends of selected menu option the scene it's transited.
	 */
	private void checkButtonSelection() {
		
		if (keyboard.keyDown(Keyboard.ENTER_KEY)){

			switch(selectedMenuOption) {

				case START_GAME:
					//transit to game
					game.transitTo(firstStageScene());
					break;
				case RANKING:
					//Nothing to do
					break;
				case SETTINGS:
					//Nothing to do
					break;
				case QUIT:
					//quit game
					game.quit();
					break;
				default:
					//Nothing to do
			}
		}
		else {
			//Nothing to do
		}
	}
	
	/**
	 * This method create the buttons with the sprites of buttons for define 
	 * buttons positions.
	 */
	private void appendButtons() {
		
		//Realize the build for have buttons image in screen.
		final String START_BUTTON_PATH = "src/assets/img/menu/start_button.png";
		Sprite startButton = null;
		startButton = new Sprite(START_BUTTON_PATH);
		logger.debug("The sprite was created with path: " + START_BUTTON_PATH);
		
		final String RANKING_PATH = "src/assets/img/menu/ranking.png";
		Sprite rankingButton = null;
		rankingButton = new Sprite(RANKING_PATH);		
		logger.debug("The sprite was created with path: " + RANKING_PATH);
		
		final String SETTINGS_PATH = "src/assets/img/menu/settings.png";
		Sprite settingsButton = null;
		settingsButton = new Sprite(SETTINGS_PATH);
		logger.debug("The sprite was created with path: " + SETTINGS_PATH);
		
		final String QUIT_PATH = "src/assets/img/menu/quit.png";
		Sprite quitButton = null;
		quitButton = new Sprite(QUIT_PATH);
		logger.debug("The sprite was created with path: " + QUIT_PATH);
		
		//Add this images in buttons really
		try {
			this.buttons.add(startButton);
			this.buttons.add(rankingButton);
			this.buttons.add(settingsButton);
			this.buttons.add(quitButton);
		}
		catch(NullPointerException exception) {
			logger.error("This object returned null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}

		for(OptionsMenu option : OptionsMenu.values()) {
			//Integer value of variable option
			int currentButtonIndex = 0; 
			//Define the position of the first element according to the title
			try {
				currentButtonIndex = option.ordinal();
				assert(currentButtonIndex >= 0);
				assert(currentButtonIndex <= 1000);				
				if(currentButtonIndex == 0) {
					buttons.get(currentButtonIndex).x = WindowConstants.WIDTH/2 - startButton.width/2;
					final int DISTANCE_TITLE_BUTTON = WindowConstants.HEIGHT/24;
					buttons.get(currentButtonIndex).y = title.y + title.height + DISTANCE_TITLE_BUTTON;
				}
				else { 
					//Define the position of the element according to the last element
					buttons.get(currentButtonIndex).x = buttons.get(currentButtonIndex - 1).x;
					buttons.get(currentButtonIndex).y = buttons.get(currentButtonIndex - 1).y + buttons.get(currentButtonIndex - 1).height + DISTANCE_BETWEEN_BUTTONS;
				}
			}
			catch(IllegalArgumentException exception) {
				logger.error("The index of button is invalid ", exception);
				exception.printStackTrace();
				errorOccurred = true;
			}
			
		}
	}

	/**
	 * This method build the objects necessaries for define 
	 * the current arrow of keyboard.
	 */
	private void currentArrow() {
		//Value of current button index. 0 = start button;1 = ranking button;2 = settings button;3 = quit button
		int currentButtonIndex = 0;
		try {
			assert(this.selectedMenuOption != null);
			currentButtonIndex = this.selectedMenuOption.ordinal();
	
			Sprite currentButton = null;
			assert(currentButtonIndex >= 0);
			
			currentButton = this.buttons.get(currentButtonIndex);
			this.arrow.x = currentButton.x - arrow.width - DISTANCE_BETWEEN_BUTTONS;
			this.arrow.y = currentButton.y;
		}
		catch(NullPointerException exception) {
			logger.error("This object returned null", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
		catch(IllegalArgumentException exception) {
			logger.error("The current of button dont should be negative", exception);
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	/**
	 * Build design of menu with the union of elements for show on screen.
	 */
	private void drawScenes() {
		if(!errorOccurred) {
			background.draw();
			title.draw();
			arrow.draw();	
			for(Sprite button: this.buttons) {
				button.draw();
			}
		}
		else {
			transitErrorScene();
		}
	}

	private void transitErrorScene() {
		GameScene errorScene = new ErrorScene();
		this.game.transitTo(errorScene);		
	}
}