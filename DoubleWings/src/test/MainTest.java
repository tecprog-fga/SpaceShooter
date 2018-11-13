package test;

import static org.junit.Assert.*;

import org.junit.Test;

import constants.WindowConstants;
import game.GameController;
import jplay.Window;
import scenes.GameScene;
import scenes.menu.MenuScene;

public class MainTest {

	Window gameScreen = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);
	
	@Test
	public void testCreateGameScreen() {
		assertNotNull(this.gameScreen);
	}
	
	GameController game = new GameController();
	
	@Test
	public void testCreateGame() {
		assertNotNull(this.game);
	}
	
	GameScene scene = new MenuScene();
	
	@Test
	public void testCreateScene() {
		assertNotNull(this.scene);
	}
}
