package test;

import static org.junit.Assert.*;

import org.junit.Test;

import jplay.Keyboard;
import scenes.GameScene;

public class GameControllerTest {
	
	public GameScene currentScene = null;
	
	@Test
	public void testCurrentScene() {
		assertNull(this.currentScene);
	}
	
	public Keyboard keyboard = null;
	
	@Test
	public void testKeyboard() {
		assertNull(this.keyboard);
	}
	
	private boolean isRunning = true;
	
	@Test
	public void testIsRun() {
		this.isRunning = true;
		assertTrue(this.isRunning);
	}
	
	@Test
	public void testQuit() {
		this.isRunning = false;
		assertFalse(this.isRunning);
	}
	
}
