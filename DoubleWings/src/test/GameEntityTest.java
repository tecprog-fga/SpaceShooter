package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
 
public class GameEntityTest {

	public String name = null;
	
	@Before
	public void gameEntityTest() {
		assertNull(this.name);
	}
	
	protected int life = 1;
	
	@Test
	public void testLife() {
		assertNotNull(this.life);
	}
	
	private boolean isDead = false;
	
	@Test
	public void testDie() {
		this.isDead = true;
		assertTrue(this.isDead);
	}
	
	private static final double ENTITYLIMIT = 1000;
	
	@Test
	public void testEntity() {
	 assertTrue(this.ENTITYLIMIT == 1000);	
	}
	
}
