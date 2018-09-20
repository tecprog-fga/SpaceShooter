/*********************************************************
  * File: Enemy.java
  * Purpose: Enemy class implementation
  *********************************************************/

package entity;

import commands.Command;
import entity.player.Player;
import entity.player.PlayerSpaceship;

/**
 * This class creates the enemy in the scene
 */
public class Enemy extends GameEntity {

	
	/**
	 * sprite of the enemy
	 */
	static private final String IMAGE_TEMP_PLAYER = "src/assets/img/temp_player.png";

	public Enemy(int x, int y) {
		super(Enemy.IMAGE_TEMP_PLAYER);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * initialization for the movements of the enemy
	 */
	private int commandCount = 0;
	
	public void executeBehavior(Command[] commands) {
		if (commandCount < commands.length) {
			if (commands[commandCount].executeDisplacement(this)) {
				commandCount += 1;
				} else  {/*donot*/}
		System.out.println("x: " + this.x + " y: " + this.y);
		} else {/*donot*/}
	}
	
	
	public Enemy(String fileName) {
		super(fileName);
	}

	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Bullet.class) {
			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes
			Bullet bullet = (Bullet) entity;
			PlayerSpaceship spaceship = (PlayerSpaceship) bullet.owner;
			
			/*
			 * Increase Player Score
			 */
			spaceship.getPlayer().increaseScore(100);
		}
	}
}