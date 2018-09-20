/*********************************************************
  * File: Bullet.java
  * Purpose: Bullet class implementation
  *********************************************************/

package entity;

import entity.player.PlayerSpaceship;

/**
 * This class creates the project that leaves the spaceship
 * It's necessary because it defines the position in which it should exit
 */
public class Bullet extends GameEntity {
	
	private static final String IMAGE_BULLET = "src/assets/img/bullet_player.png";
	
	public Bullet() {
		super(IMAGE_BULLET);
	}
	
	public void fireBy(GameEntity owner, double vely) {
		this.owner = owner;
		this.vely = vely;
		Integer horizontalCorrection = (this.width - owner.width)/2;
		this.x = owner.x - horizontalCorrection;
		
		if (owner.getClass() == PlayerSpaceship.class) {
			this.y = owner.y;
		} else {
			this.y = owner.y + owner.height;
		}
	}
	
	@Override
	public void reborn() {
		super.reborn();
		this.owner = null;
	}
	public GameEntity owner = null; 
}