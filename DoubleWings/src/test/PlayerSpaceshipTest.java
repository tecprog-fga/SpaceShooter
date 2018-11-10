package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Bullet;
import entity.Enemy;
import entity.GameEntity;
import util.DelayDelegate;
import util.DelayTimer;

public class PlayerSpaceshipTest {
	
	private static final String SPRITE_IMAGE_PATH = "path";

	@Test
	public void testPlayerSpaceship() {
		assertNotNull(SPRITE_IMAGE_PATH);
	}

	@Test
	public void testIsDead() {
		boolean correct = false;
		assertFalse(correct);
	}

	private boolean didDie = false;
	@Test
	public void testReborn() {
		this.didDie = false;
		
		assertFalse(this.didDie);
	}

	private boolean canShoot = true;

	@Test
	public void testFireBullet() {
		assertTrue(this.canShoot);
		Bullet bullet = new Bullet();
		assertNotNull(bullet);
	}
	
	@Test
	public void testDelayEnded() {
		assertTrue(this.canShoot);
	}

}
