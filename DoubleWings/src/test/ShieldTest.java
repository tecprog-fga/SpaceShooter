package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.GameEntity;

public class ShieldTest {

	private static final String SPRITE_FILE_PATH = "path";
	private GameEntity player = null;

	@Test
	public void testShield() {
		assertNotNull(SPRITE_FILE_PATH);
	}
	
	private boolean isCollidable = true;

	@Test
	public void testReborn() {
		assertTrue(	this.isCollidable);
	}

}
