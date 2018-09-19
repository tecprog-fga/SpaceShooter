/*****************************************************************
 * File: BulletPool.java
 * Purpose: Spaceship's Bullet Poll class implementation
 *****************************************************************/

package entity.pool;

import entity.Bullet;
import entity.GameEntity;

/*
 * Create lots of the SpaceShip's bullets
 */
public class BulletPool extends ObjectPool<Bullet>{

	public Bullet create(){
		Bullet bullet = new Bullet();
		return bullet;
	}
}
