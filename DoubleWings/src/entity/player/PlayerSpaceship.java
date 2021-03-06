/*****************************************************************
 * File: PlayerSpaceship.java
 * Purpose: Player's Spaceship class implementation
 *****************************************************************/

package entity.player;


import org.apache.log4j.Logger;

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

	// Sprite of the level 1 spaceship
	private static final String SPRITE_IMAGE_PATH = "src/assets/img/player_lvl1.png"; //$NON-NLS-1$
	
	// Spaceship's velocity
	private static final int DEFAULT_MOVEMENT_VELOCITY = 4;
	
	// Build's up the spaceship's Shield
	private Shield shield = null;
	
	// It's an object that receives the events of the game
	private Player player = null;

	/**
	 * Builds the the spaceship, setting the player, shield, life
	 * @param player   Object player to build the spaceship
	 * @param x        X position in the X axis
	 * @param y        Y position in the Y axis
	 * @param adjust   decide if the position needs adjustment
	 */
	public PlayerSpaceship(Player player, double x, double y, boolean adjust) {
		super(SPRITE_IMAGE_PATH);
		this.life = this.maxLife;
		this.shield = new Shield(this);
		
		assert(player != null) : "The player is null"; //$NON-NLS-1$
		this.player = player;
		
		// Adjusting x position to fit the sprite
		if (adjust) {
			this.x = x - this.width / 2;	
		} else {
			this.x = x;
		}
		this.y = y;
	}

	/* 
	 * If the player makes contact, he will receive damage and decrease life and shield
	 * (non-Javadoc)
	 * @see entity.GameEntity#didContact(entity.GameEntity)
	 */
	@Override
	public void didContact(GameEntity entity){
		
		if (entity.getClass() == Enemy.class) {
			entity.receiveDamage(100);
			
			if (this.shield.getLife() <= 0) { // security check to avoid double dying bug
				this.receiveDamage(20);
			}
		}
	}

	/**
	 * Get the spaceship's shield
	 * @return
	 */
	public Shield getShield() {
		
		assert(this.shield != null):"Shield is returning null"; //$NON-NLS-1$
		return this.shield;
	}
	
	/**
	 * Get the spaceship's player
	 * @return
	 */
	public Player getPlayer() {
		
		assert(this.player != null):"Player is returning null";  //$NON-NLS-1$
		return this.player;
	}
	
	// Set's the controls of the game on the keyboard
	private int upKey = Keyboard.UP_KEY;
	private int downKey = Keyboard.DOWN_KEY;
	private int leftKey = Keyboard.LEFT_KEY;
	private int rightKey = Keyboard.RIGHT_KEY;
	private int shootKey = 0;
	
	/**
	 * Keyboard configuration
	 * @param upKey     Makes the spaceship go up
	 * @param downKey   Makes the spaceship go down
	 * @param rightKey  Makes the spaceship go right
	 * @param leftKey   Makes the spaceship go left
	 * @param shootKey  Makes the spaceship shoot
	 */
	public void setKeySet(int upKey, int downKey, int rightKey, int leftKey, int shootKey) {
		
		this.upKey = upKey;
		this.downKey = downKey;
		this.rightKey = rightKey;
		this.leftKey = leftKey;
		this.shootKey = shootKey;
	}
	
	public PlayerSceneDelegate delegate = null;

	
	// Verify if the player has died
	private boolean didDie = false;
	
	/* 
	 * Use the keyboard input to update the game
	 * (non-Javadoc)
	 * @see entity.GameEntity#update()
	 */
	@Override
	public void update() {
		super.update();

			if (this.life <= 0) {
				// security check to avoid double dying bug
				if (this.didDie == false) {
					this.didDie = true;
					// Enter here if the spaceship is destroyed
					this.player.loseLife();
				}
				else {
					// Nothing to do
				}
			} 
			else {
				checkInput();
			}
	}
	
	/* 
	 * Reborn the spaceship and set it's shield to full
	 * (non-Javadoc)
	 * @see entity.GameEntity#reborn()
	 */
	@Override
	public void reborn(){
		super.reborn();
		this.shield.reborn();
		this.didDie = false;
	}
	
	/* 
	 * Set the life to zero if it reaches negative 
	 * (non-Javadoc)
	 * @param newlife
	 * @see entity.GameEntity#setLife(int)
	 */
	@Override
	public void setLife(int newlife){
		this.life = newlife;
		
		if (this.life < 0){
			this.life = 0;
		}
	}
	
	/* 
	 * Controls if the player is dead or alive
	 * @return
	 * (non-Javadoc)
	 * @see entity.GameEntity#isDead()
	 */
	@Override
	public boolean isDead(){
		return false;
	}
	
	private static Logger logger = Logger.getLogger(PlayerSpaceship.class);
	
	/**
	 * Time of cooldown of the spaceship weapon 
	 */
	public final int shootCooldown = 100;
	
	// Controls if the spaceship can shoot or not 
	private boolean canShoot = true;
	
	// Delay from the keyboard to the screen
	private DelayTimer shootCDTimer = new DelayTimer(this, 1);
	
	/**
	 * Action of shooting 
	 */
	public void fireBullet(){
		if (this.canShoot){
			this.canShoot = false;
			this.shootCDTimer.schedule(this.shootCooldown);	
			
			Bullet bullet = new Bullet();
			
			try {
				assert(bullet != null): "Bullet is receiving null";
				bullet.fireBy(this, -10);
				this.gameWorld.add(bullet);				
			}
			catch(NullPointerException exception) {
				logger.error("Null returned, verify the Bullets", exception);
				exception.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Set the movement velocity to the default
	 */
	public double movimentVel = DEFAULT_MOVEMENT_VELOCITY; // default value
	
	/**
	 * Checks the keyboard input
	 */
	public void checkInput(){
		//Player movement
		moveX(this.leftKey, this.rightKey, this.movimentVel);
		moveY(this.upKey, this.downKey, this.movimentVel);

		try {
			assert(this.gameWorld != null): "teste";
			if(this.gameWorld != null){
				assert(this.gameWorld.keyboard != null): "teste";
				if (this.gameWorld.keyboard != null){
					if(this.gameWorld.keyboard.keyDown(this.shootKey)){
						this.fireBullet();
					}
					else {
						// Nothing to do
					}
				}
				else {
					//Nothing to do
				}
			}
			else {
				// Nothing to do
			}			
		}catch(NullPointerException e) {
			logger.error("Null return for the Keyboard", e);
			e.printStackTrace();
		}
	}
	
	/* 
	 * Move in the X axis when receiving an input
	 * (non-Javadoc)
	 * @see jplay.Sprite#moveX(int, int, double)
	 */
	@Override
	public void moveX(int leftKey1, int rightKey1, double vel){
		
		if(this.gameWorld != null){
			if (this.gameWorld.keyboard != null){
				if(this.gameWorld.keyboard.keyDown(leftKey1)){
					this.x -= vel;
				}
				if(this.gameWorld.keyboard.keyDown(rightKey1)){
					this.x += vel;
				}
			}
		}
	}
	
	/* 
	 * Move in the Y axis when receiving an input
	 * (non-Javadoc)
	 * @see jplay.Sprite#moveY(int, int, double)
	 */
	@Override
	public void moveY(int upKey1, int downKey1, double vel){
		
		if(this.gameWorld != null){
			if (this.gameWorld.keyboard != null){
				if(this.gameWorld.keyboard.keyDown(upKey1)){
					this.y -= vel;
				}
				if(this.gameWorld.keyboard.keyDown(downKey1)){
					this.y += vel;
				}
			}
		}
	}

	/* 
	 * The spaceship can shoot again when the delay ends
	 * (non-Javadoc)
	 * @see util.DelayDelegate#delayEnded(util.DelayTimer)
	 */
	@Override
	public void delayEnded(DelayTimer timer) {
		
		if (timer.getType() == 1){
			this.canShoot = true;
		}
	}
}
