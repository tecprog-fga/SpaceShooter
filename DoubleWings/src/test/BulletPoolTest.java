package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Bullet;

public class BulletPoolTest {

	Bullet bullet = new Bullet();
	
	@Test
	public void testCreate() {;
		assertNotNull(this.bullet);
	}
}
