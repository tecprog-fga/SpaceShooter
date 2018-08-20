package scenes.menu;

import scenes.ClassicContinue;
import scenes.GameScene;
import scenes.stages.stage1.StageTest;

import jplay.GameImage;
import jplay.Keyboard;

import java.util.ArrayList;

import constants.WindowConstants;
import jplay.Sprite;

// MenuScene 
public class MenuScene extends GameScene {
	//GameScene constants
	private static final int DISTANCE_TITLE_BUTTON = WindowConstants.HEIGHT/24;
	private static final int DISTANCE_BETWEEN_BUTTONS = WindowConstants.HEIGHT/48;
	private static GameScene firstLevel;
	
	private OptionMenu selectedMenuOption = OptionMenu.Start_Game;//Define initial menu option
	
	// Sprites on scene
	private GameImage background;
	private Sprite title;
	private Sprite arrow;
	private ArrayList<Sprite> buttons = new ArrayList<Sprite>();
	
	protected void initialSetup(){
		
		//Reset option menu
		selectedMenuOption = OptionMenu.Start_Game;
		
		//Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}
	
	protected void viewSetup(){
		//Define Scene elements
		background = new GameImage("src/assets/img/menu/background.png");

		title = new Sprite("src/assets/img/menu/title.png");
		title.x = WindowConstants.WIDTH/2 - title.width/2;
		title.y = WindowConstants.HEIGHT/3 - title.height/2;

		//Define buttons position
		appendButtons();

		arrow = new Sprite("src/assets/img/menu/arrow.png");
		arrow.x = 10;
		arrow.y = 10;
	}
	
	//Add buttons to array
	private void appendButtons() {
		Sprite startButton = new Sprite("src/assets/img/menu/start_button.png");
		Sprite rankingButton = new Sprite("src/assets/img/menu/ranking.png");
		Sprite settingsButton = new Sprite("src/assets/img/menu/settings.png");
		Sprite quitButton = new Sprite("src/assets/img/menu/quit.png");
		
		buttons.add(startButton);
		buttons.add(rankingButton);
		buttons.add(settingsButton);
		buttons.add(quitButton);
		
		for(OptionMenu option : OptionMenu.values()) {
			int currentButtonIndex = option.ordinal(); //Integer value of variable option
			
			//Define the position of the first element according to the title
			if(currentButtonIndex == 0) {
				buttons.get(currentButtonIndex).x = WindowConstants.WIDTH/2 - startButton.width/2;
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
				if (keyboard.keyDown(Keyboard.DOWN_KEY)){
					System.out.println("down");
					//Change current menu option
					selectedMenuOption = selectedMenuOption.next();
					System.out.println(selectedMenuOption);
				}
				
				// Up selection		
				if (keyboard.keyDown(Keyboard.UP_KEY)){
					System.out.println("up");
					//Change current menu option
					selectedMenuOption = selectedMenuOption.back();
					System.out.println(selectedMenuOption);
				}
	}
	
	private void moveArrow() {
		int currentButtonIndex = this.selectedMenuOption.ordinal();
		Sprite currentButton = this.buttons.get(currentButtonIndex);
		
		this.arrow.x = currentButton.x - arrow.width - DISTANCE_BETWEEN_BUTTONS;
		this.arrow.y = currentButton.y;
		
	}
	
	//Draw scene elements
	private void draw() {
		background.draw();
		title.draw();
		arrow.draw();
		
		for(Sprite button: this.buttons) {
			button.draw();
		}
	}
	
	public GameScene firstStage(){
		if(firstLevel == null){
	      firstLevel = new StageTest();
		  return firstLevel;
		} 	
		else{
			return firstLevel;
		}
		  
	}
	
	//Check keyboard enter and dispatch new scene
	private void checkButtonSelection(){
		if (keyboard.keyDown(Keyboard.ENTER_KEY)){
			
			switch(selectedMenuOption){

			case Start_Game:
				//transit to game
				game.transitTo(firstStage());
				break;
			case Ranking:
				//transit to ranking
				break;
			case Settings:
				//transit to settings
				break;
			case Quit:
				//quit game
				game.quit();
				break;
			}
		}
	}
	
	public void update(){
		
		// Control menu option selection
		checkMenuOption();
		
		//Define current arrow position
		moveArrow();
		
		checkButtonSelection();
		
		// Draw menu elements
		draw();
	}
}
