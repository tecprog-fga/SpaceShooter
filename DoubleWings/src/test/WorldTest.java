package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.GameEntity;
import entity.pool.EnemyPool;
import jplay.Keyboard;


public class WorldTest {

	private static final String deadObj = null;

	public Keyboard keyboard = null;

	private Object objs;
	
	@Before
	public void testKeyboard() {
		assertNull(this.keyboard);
	}
	
	@Test
	public void testWorld() {
		/*Nothing to do*/
	}
	
	@Test
	public void testAdd() {
		/*Nothing to do*/
	}
	
	boolean didRemove = true;
	
	@Test
	public void testRemove() {
		assertTrue(this.didRemove);
	}
	
	private EnemyPool enemyPool = new EnemyPool();
	
	@Test
	public void testCreateEnemy() {
		assertNotNull(this.enemyPool);
	}
	
}
