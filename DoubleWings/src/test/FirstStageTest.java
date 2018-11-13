package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.player.PlayerSpaceship;
import game.World;
import jplay.Keyboard;
import jplay.Parallax;

public class FirstStageTest {
	
	private World gameWorld;
	private Keyboard keyboard;
	private Parallax parallaxEffect;
	private PlayerSpaceship spaceship;

	@Before
	public void setUp() throws Exception {
		
		gameWorld = new World();
		keyboard = new Keyboard();
		parallaxEffect = new Parallax();
	}
	
	@Test
	public void testBuildInitialScene() {
		assertNotNull(gameWorld);
		assertNotNull(keyboard);
	}

	@Test
	public void testViewSetup() {
		assertNotNull(parallaxEffect);
	}

	@Test
	public void testCreateSpaceShip() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateParalax() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateAsteroid() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTestAsteroid() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteAsteroidCommand() {
		fail("Not yet implemented");
	}

	@Test
	public void testLaunchEnemyDown() {
		fail("Not yet implemented");
	}

	@Test
	public void testLaunchEnemyCrazy() {
		fail("Not yet implemented");
	}

	@Test
	public void testConfigureEvents() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransitToGameOver() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransitToContinue() {
		fail("Not yet implemented");
	}

}
