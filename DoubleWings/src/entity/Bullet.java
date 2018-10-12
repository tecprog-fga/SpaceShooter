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
		
	/**
	 * spaceship bullet sprite
	 */
	private static final String IMAGE_BULLET = "src/assets/img/bullet_player.png";{
		
	}
		
	
	/**
	 * builds the bullet sprite on the screen 
	 */
	public Bullet() {
		super(IMAGE_BULLET);
		
		assert(IMAGE_BULLET != null): ("Sprite of the bullet is null");
	}
	
	/**
	 *  bullet firing position
	 * @param owner bullet target
	 * @param position of the y-axis running through the bullet
	 */
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
	
	/*  
	 * reborn bullet 
	 * (non-Javadoc)
	 * @see entity.GameEntity#reborn()
	 */
	@Override
	public void reborn() {
		super.reborn();
		
		assert(owner != null):("owner is null");
		this.owner = owner;
	}
	
	public GameEntity owner = null; 
}