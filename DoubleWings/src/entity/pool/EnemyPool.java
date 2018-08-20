package entity.pool;

import entity.Enemy;

public class EnemyPool extends ObjectPool<Enemy>{

	protected Enemy create(){
		Enemy enemy = new Enemy("src/assets/asteroid.png");
		return enemy;
	}
}
