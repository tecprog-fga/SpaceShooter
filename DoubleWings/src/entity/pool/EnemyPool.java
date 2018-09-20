/*****************************************************************
 * File: EnemyPool.java
 * Purpose: Enemy Pool class implementation
 *****************************************************************/

package entity.pool;

import entity.Enemy;

/**
 * Create lots of enemies
 */
public class EnemyPool extends ObjectPool<Enemy>{

	protected Enemy create(){
		
		/**
		 * Enemy sprite directory
		 */
		Enemy enemy = new Enemy("src/assets/asteroid.png");
		return enemy;
	}
}
