package test;

import static org.junit.Assert.*;

import entity.pool.EnemyPool;

import org.junit.Test;

public class WorldTest {

	private EnemyPool enemyPool = new EnemyPool();
	
	@Test
	public void testCreateEnemy() {
		assertNotNull(this.enemyPool);
	}
	
}
