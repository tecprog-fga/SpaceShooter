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

	/** build the sprite of the enemy and define your position
	 * @param x x-axis position
	 * @param y y-axis position
	 */
	public Enemy(int x, int y) {
		super(Enemy.IMAGE_TEMP_PLAYER);	

		assert(IMAGE_TEMP_PLAYER != null):("Sprite of the enemy not found");
		this.x = x;
		this.y = y;
	}
	
	/**
	 * initialization for the movements of the enemy
	 */
	private int commandCount = 0;
	
	/** 
	 * command executed by the enemy
	 * @param commands 
	 */
	public void executeBehavior(Command[] commands) {
		if (commandCount < commands.length) {
			if (commands[commandCount].executeDisplacement(this)) {
				commandCount += 1;
				} else  {/*donot*/}
		System.out.println("x: " + this.x + " y: " + this.y);
		} else {/*donot*/}
	}
	
	
	/** 
	 * builds the enemy
	 * @param fileName
	 */
	public Enemy(String fileName) {
		super(fileName);
	}

	/* 
	 * Damage done by the enemy
	 * (non-Javadoc)
	 * @see entity.GameEntity#didContact(entity.GameEntity)
	 */
	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Bullet.class) {
			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes
			Bullet bullet = (Bullet) entity;
			PlayerSpaceship spaceship = (PlayerSpaceship) bullet.owner;
			spaceship.getPlayer().increaseScore(100);//Increase Player Score
		}
	}
}