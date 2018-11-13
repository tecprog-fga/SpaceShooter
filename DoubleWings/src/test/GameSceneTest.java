package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.GameController;
import jplay.Keyboard;

public class GameSceneTest {
	
	private GameController game;
	private Keyboard keyboard;

	@Before
	public void setUp() throws Exception {
		
		game = new GameController();
		keyboard = new Keyboard();
	}

	@Test
	public void testConfigureGameScene() {
		assertNotNull(game);
		assertNotNull(keyboard);
	}
}