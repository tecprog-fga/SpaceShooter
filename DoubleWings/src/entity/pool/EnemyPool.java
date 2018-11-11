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

	final static String ENEMY_PATH = "src/assets/asteroid.png";
	
	@Override
	protected Enemy create(){
		
		/**
		 * Enemy sprite directory
		 */
		Enemy enemy = new Enemy(ENEMY_PATH); //$NON-NLS-1$
		assert(enemy != null): "enemy is receing null"; //$NON-NLS-1$
		return enemy;
	}
}
