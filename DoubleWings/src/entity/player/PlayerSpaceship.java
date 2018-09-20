/*****************************************************************
 * File: PlayerSpaceship.java
 * Purpose: Player's Spaceship class implementation
 *****************************************************************/


package entity.player;

import entity.Bullet;
import util.*;
import entity.Enemy;
import entity.GameEntity;
import jplay.Keyboard;
import util.DelayTimer;

/**
 * This class builds the Player's Spaceship life,
 * shield and movement across the XY axis
 */
public class PlayerSpaceship extends GameEntity implements DelayDelegate{

	/**
	 * Sprite of the level 1 spaceship
	 */
	private static final String SPRITE_IMAGE_PATH = "src/assets/img/player_lvl1.png";
	
	/**
	 * Spaceship's velocity
	 */
	private static final int DEFAULT_MOVEMENT_VELOCITY = 4;
	
	/**
	 * 
	 */
	private Shield shield = null;
	private Player player = null;

	public PlayerSpaceship(Player player, double x, double y, boolean adjust) {
		super(SPRITE_IMAGE_PATH);
		this.life = maxLife;
		this.shield = new Shield(this);
		this.player = player;
		
		if (adjust) {
			// x position fixed for sprite width
			this.x = x - this.width / 2;	
		} else {
			this.x = x;
		}
		this.y = y;
	}

	@Override
	public void didContact(GameEntity entity){
		
		if (entity.getClass() == Shield.class){
		} else if (entity.getClass() == Enemy.class) {
			entity.receiveDamage(100); // test purposes
			if (shield.getLife() <= 0) { // security check to avoid double dying bug
				this.receiveDamage(20); // test purposes	
			}
		}else {
		}
	}

	public Shield getShield() {
		return this.shield;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	// Default values for keys. Can be reset using setKeySet 
	private int upKey = Keyboard.UP_KEY;
	private int downKey = Keyboard.DOWN_KEY;
	private int leftKey = Keyboard.LEFT_KEY;
	private int rightKey = Keyboard.RIGHT_KEY;
	private int shootKey = 0;
	
	public void setKeySet(int upKey, int downKey, int rightKey, int leftKey, int shootKey) {
		this.upKey = upKey;
		this.downKey = downKey;
		this.rightKey = rightKey;
		this.leftKey = leftKey;
		this.shootKey = shootKey;
	}
	
	private boolean didDie = false;

	@Override
	public void update() {
		super.update();
		
		if (this.life <= 0) {
			// security check to avoid double dying bug
			if (didDie == false) {
				didDie = true;
				// Enter here if the spaceship is destroyed
				this.player.loseLife();
			} else { /*do nothing*/ }
		} else {
			checkInput();
		}
	}
	
	@Override
	public void reborn(){
		super.reborn();
		this.shield.reborn();
		this.didDie = false;
	}
	
	@Override
	public void setLife(int newlife){
		this.life = newlife;
		
		if (this.life < 0){
			this.life = 0;
		}
	}
	
	@Override
	public boolean isDead(){
		return false;
	}
	
	public int shootCooldown = 100;
	private boolean canShoot = true;
	private DelayTimer shootCDTimer = new DelayTimer(this, 1);
	
	public void fireBullet(){
		
		if (canShoot){
			canShoot = false;
			this.shootCDTimer.schedule(this.shootCooldown);	
			//System.out.println("Fire Bullet!");
			Bullet bullet = new Bullet();
			bullet.fireBy(this, -10);
			gameWorld.add(bullet);
		}
	}
	
	public double movimentVel = DEFAULT_MOVEMENT_VELOCITY; // default value
	
	public void checkInput(){
		//Player movement
		moveX(leftKey, rightKey, this.movimentVel);
		moveY(upKey, downKey, this.movimentVel);
		//shootKey
		if(gameWorld != null){
			if (gameWorld.keyboard != null){
				if(gameWorld.keyboard.keyDown(shootKey)){
					this.fireBullet();
				}
			}
		}
	}
	
	@Override
	public void moveX(int leftKey, int rightKey, double vel){
		
		if(gameWorld != null){
			if (gameWorld.keyboard != null){
				if(gameWorld.keyboard.keyDown(leftKey)){
					this.x -= vel;
				}
				if(gameWorld.keyboard.keyDown(rightKey)){
					this.x += vel;
				}
			}
		}
	}
	
	@Override
	public void moveY(int upKey, int downKey, double vel){
		
		if(gameWorld != null){
			if (gameWorld.keyboard != null){
				if(gameWorld.keyboard.keyDown(upKey)){
					this.y -= vel;
				}
				if(gameWorld.keyboard.keyDown(downKey)){
					this.y += vel;
				}
			}
		}
	}

	@Override
	public void delayEnded(DelayTimer timer) {
		
		if (timer.getType() == 1){
			this.canShoot = true;
		}
	}
}
