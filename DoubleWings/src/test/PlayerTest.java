package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.player.PlayerSceneDelegate;
import entity.player.PlayerSpaceship;
import observer.GameEntityObserver;

public class PlayerTest {
	
	private static final int INITIAL_CHANCES = 3;
	private boolean canContinue = true;
	public PlayerSceneDelegate delegate = null;

	
	@Test
	public void loseGameTest() {
		assertNull(this.delegate);
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
