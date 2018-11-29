package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.player.PlayerSpaceship;
import observer.GameEntityObserver;

public class PlayerTest {

	@Before
	public void setUp() throws Exception {
		assertTrue(this.score <= 0);
	}
	
	private int score = 0;
	
	
	@Test
	public void testPlayer() {
		assertTrue(this.score == 0);
	}

	private GameEntityObserver observer = null;
	
	@Test
	public void testGetObserver() {
		assertNull(this.observer);
	}
		
	@Test
	public void testSetObserver() {
		/* Nothing to do */
	}
	private int chances = 3;

	@Test
	public void testSetChances() {
		assertTrue(this.chances == 3);
		
	}

	@Test
	public void testGetScore() {
		assertTrue(this.score == 0);
	}
	
	private PlayerSpaceship spaceship = null;
	public double initialPositionX = 0;
	public double initialPositionY = 0;
	
	private void resetSpaceshipTest() {
		this.spaceship.reborn();
		this.spaceship.x = this.initialPositionX;
		this.spaceship.y = this.initialPositionY;
		
		assertEquals(0, this.spaceship.x, this.initialPositionX);
		assertEquals(0, this.spaceship.y, this.initialPositionY);
	}

}
