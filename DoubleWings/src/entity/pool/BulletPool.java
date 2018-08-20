package entity.pool;

import entity.Bullet;
import entity.GameEntity;

public class BulletPool extends ObjectPool<Bullet>{

	public Bullet create(){
		Bullet bullet = new Bullet();
		return bullet;
	}
}
