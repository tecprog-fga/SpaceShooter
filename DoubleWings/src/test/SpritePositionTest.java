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
	double windowDividend = 2;
	double screeDividend = 1;
	
	@Before
	public void setup() {
		sprite = new Sprite(PATH);
		spos = new SpritePosition();
		
		sprite.x = spos.calculatePosition(WindowConstants.WIDTH, windowDividend, this.sprite, screeDividend);
		sprite.y = spos.calculatePosition(WindowConstants.HEIGHT, windowDividend, this.sprite, screeDividend);
	}
	
	@Test
	public void objectsNullableTest() {
		assertNotNull(sprite);
		assertNotNull(spos);
	}
	
	@Test
	public void calculatePositionTest() {
		Sprite spriteTest = new Sprite(PATH);
		
		spriteTest.x = spos.calculatePosition(WindowConstants.WIDTH, windowDividend, spriteTest, screeDividend);
		spriteTest.y = spos.calculatePosition(WindowConstants.HEIGHT, windowDividend, spriteTest, screeDividend);
		
		assertEquals(sprite.x, spriteTest.x, DELTA);
		assertEquals(sprite.y, spriteTest.y, DELTA);
	}
	
	@Test
	public void validatePositionTest() {
		boolean validPosition = true;
		boolean validPositionTest;
		
		validPositionTest = this.spos.validatePosition(WindowConstants.WIDTH, windowDividend, screeDividend);
		
		assertEquals(validPosition, validPositionTest);	
	}
}
