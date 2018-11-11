package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import constants.WindowConstants;
import jplay.Sprite;
import util.SpritePosition;

public class SpritePositionTest {
	private Sprite sprite;
	final String PATH = "src/assets/img/continue/continue.png";
	private SpritePosition spos;
	private static final double DELTA = 1e-15;
	double windowDividend;
	double screenDividend;
	
	@Before
	public void setup() {
		sprite = new Sprite(PATH);
		spos = new SpritePosition();
		windowDividend = 2;
		screenDividend = 1;
	}
	
	@Test
	public void objectsNullableTest() {
		assertNotNull(sprite);
		assertNotNull(spos);
	}
	
	@Test
	public void calculatePositionTest() {
		Sprite spriteTest = new Sprite(PATH);
		sprite.x = 31.00;
		sprite.y = 199.00;
		
		spriteTest.x = spos.calculatePosition(WindowConstants.WIDTH, windowDividend, spriteTest, screenDividend);
		spriteTest.y = spos.calculatePosition(WindowConstants.HEIGHT, windowDividend, spriteTest, screenDividend);
		
		assertEquals(sprite.x, spriteTest.x, DELTA);
		assertEquals(sprite.y, spriteTest.y, DELTA);
	}
	
	@Test
	public void validatePositionTest() {
		boolean validPosition = true;
		boolean validPositionTest;
		
		validPositionTest = this.spos.validatePosition(WindowConstants.WIDTH, windowDividend, screenDividend);
		
		assertEquals(validPosition, validPositionTest);	
	}
}
