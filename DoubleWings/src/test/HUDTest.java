package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import jplay.Sprite;

public class HUDTest {
	private Sprite shieldLifeBar = new Sprite("path");
	
	@Test
	public void testSpriteShieldLifeBar() {
		assertNotNull(shieldLifeBar);
	}
			
	private Sprite shieldLifeBarOrnament = new Sprite("path");
	
	@Test
	public void testSpriteShieldLifeBarOrnament() {
		assertNotNull(shieldLifeBarOrnament);
	}
	
	private Sprite numberOfLivesImage = new Sprite("path");
	
	@Test
	public void testSpriteNumberOfLives() {
		assertNotNull(numberOfLivesImage);
	}
}
