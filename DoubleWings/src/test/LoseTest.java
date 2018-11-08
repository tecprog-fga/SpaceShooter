package test;

import static org.junit.Assert.*;

import org.junit.Test;

import jplay.Parallax;
import jplay.Sprite;

public class LoseTest {
	private Parallax delpthScene;
	private Sprite loseScreen;
	
	@Test
	public void buildInitialSceneTest() {
		this.delpthScene = new Parallax();
		final String LOSE_PATH = "path";
		this.loseScreen = new Sprite(LOSE_PATH);
		
		assertTrue(this.delpthScene != null);
		assertTrue(this.loseScreen != null);
	}

}
