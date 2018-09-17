import jplay.Window; 
import java.awt.Color;
import constants.WindowConstants;
import scenes.GameScene;
import scenes.menu.MenuScene;
import game.GameController;

public class Main {
	/**
	 * variable that indicates how long the game is going to be in sleep mode
	 */
	public final static int KEEP_FRAMERATE = 16;
	
	public static void main(String[] args) {

		System.out.println("It's running!");

		Window gameScreen = null;
		gameScreen = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);

		GameController game = null;
		game = new GameController();
		game.keyboard = gameScreen.getKeyboard();

		GameScene scene = null;
		scene = new MenuScene();
		game.transitTo(scene);

		boolean gameIsRunning = true;
		
		while(gameIsRunning) {
			gameScreen.delay(KEEP_FRAMERATE);

			gameScreen.clear(Color.black);

			gameIsRunning = game.update();

			gameScreen.update();
		}

		gameScreen.exit();
	}
}