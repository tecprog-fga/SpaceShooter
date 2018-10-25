/*********************************************************
   * File: GameEntity.java
  * Purpose: GameEntity class implementation
  *********************************************************/

package entity;

import jplay.Sprite;
import game.World;

/**
 * This class defines the entity that damages the spaceship
 */
public class GameEntity extends Sprite {
	
	/**
	 * eliminates entity(asteroids) not hit by 
	 * the ship when they reach the limit 
	 */
	private double entityLimit = 1000; 
	
	public String name = null;
	
	/** 
	 * builds asteroids
	 * @param fileName
	 */
	public GameEntity(String fileName) {
		super(fileName);
		
		assert(fileName != null):("fileName is receiving null");
		this.name = fileName;
	}
	
	protected int life = 1;
	private boolean isDead = false;

	/**
	 * get from entity life
	 * @return 
	 */
	public int getLife() {
		return this.life;
	}
	
	/**
	 * set from entity life
	 * @param newLife
	 */
	public void setLife(int newLife){
		this.life = newLife;
		
		if (life <= 0) {
			die();
		}
	}
	
	/* 
	 * resurgence of the instance if it reaches the limit
	 * (non-Javadoc)
	 * @see jplay.Animation#update()
	 */
	@Override
	public void update() {
		super.update();
		
		if ( Math.abs(this.x) > this.entityLimit) {
			this.isDead = true;
		}
		
		if ( Math.abs(this.y) > this.entityLimit) {
			this.isDead = true;
		}
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	/**
	 * Trigger an event when contact happens
	 * @param entity
	 */
	public void didContact(GameEntity entity) {
		System.out.println(this.name + " contact: " + entity.name);
	}
	
	/**
	 * damage designed by the entity on the ship
	 * @param damage
	 */
	public void receiveDamage(int damage){
		setLife(life - damage);
	}
	
	/**
	 * elimination of the entity 
	 */
	private void die(){
		isDead = true;
	}
	
	/**
	 * birth position of the spacecraft sprit
	 */
	public Double velx = 0.0;
	public Double vely = 0.0;
	
	/**
	 * the number of times you will go through the continue menu
	 */
	public int maxLife = 1;		
	
	/**
	 * respawn of the entity 
	 */
	public void reborn(){
		this.isDead = false;
		this.x = 0;
		this.y = 0;
		this.velx = 0.0;
		this.vely = 0.0;
		this.setLife(maxLife);
		this.name = "empty entity";
	}

	public boolean isCollidable = true;
	public World gameWorld = null;
}