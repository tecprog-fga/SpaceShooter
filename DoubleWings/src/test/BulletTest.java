package test;

import static org.junit.Assert.*;


import org.junit.Test;

import entity.GameEntity;

public class BulletTest {
	
	
	private static final String IMAGE_BULLET = "path";
	public GameEntity owner = null;
	;
	@Test
	public void testSpriteBullet() {
		assertNotNull(IMAGE_BULLET);
	}
	
	@Test
	public void testFireBy() {
		assertNull(owner);
	}
	
	@Test
	public void testReborn() {
		assertNull(owner);
		assertEquals(null, this.owner);
	}
}
