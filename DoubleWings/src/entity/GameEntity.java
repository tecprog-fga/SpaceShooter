/*********************************************************
   * File: GameEntity.java
  * Purpose: GameEntity class implementation
  *********************************************************/

package entity;

import jplay.Sprite;

import org.apache.log4j.Logger;

import game.World;

/**
 * This class defines the entity that damages the spaceship
 */
public class GameEntity extends Sprite {
	 
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
	
	//eliminates entity(asteroids) not hit by the ship when they reach the limit
	private static final double ENTITYLIMIT = 1000;
	
	/* 
	 * resurgence of the instance if it reaches the limit
	 * (non-Javadoc)
	 * @see jplay.Animation#update()
	 */
	@Override
	public void update() {
		super.update();
		
		if ( Math.abs(this.x) > ENTITYLIMIT) {
			this.isDead = true;
		}
		
		if ( Math.abs(this.y) > ENTITYLIMIT) {
			this.isDead = true;
		}
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	//Logger debbuger declaration
	private static Logger logger = Logger.getLogger(GameEntity.class);
	/**
	 * Trigger an event when contact happens
	 * @param entity
	 */
	public void didContact(GameEntity entity) {
		logger.debug(this.name + " contact: " + entity.name);
	}
	
	/**
	 * damage designed by the entity on the ship
	 * @param damage
	 */
	public void receiveDamage(int damage){
		setLife(life - damage);
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
	
	//elimination of the entity
	private void die(){
		isDead = true;
	}
}