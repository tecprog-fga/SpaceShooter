/*****************************************************************
 * File: Shield.java
 * Purpose: Player's Shield class implementation
 *****************************************************************/

package entity.player;

import entity.Enemy;
import entity.GameEntity;
import observer.GameEntityObserver;

/**
 * Builds the player's Shield and the shield's life
 */
public class Shield extends GameEntity {

	// The spaceship force shield sprite directory
	private static final String SPRITE_FILE_PATH = "src/assets/img/forceshield.png"; //$NON-NLS-1$
	
	// Unused variable
	private int regeneration = 0;
	
	// Creates the player entity, that receives
	private GameEntity player = null;
	
	// Build the shield with it's sprite, player, life and position
	public Shield(GameEntity player) {
		super(SPRITE_FILE_PATH);
		
		assert(player != null): "player is receiving null"; //$NON-NLS-1$
		this.player = player;
		
		this.life = this.maxLife;
		
		this.x = player.x;
		this.y = player.y;
	}

	/**
	 * Method to update the shield according the player
	 * (non-Javadoc)
	 * @see entity.GameEntity#update()
	 */
	@Override
	public void update() {
		
		if(this.isCollidable == false){
			this.x = -500;
			this.y = -500;
			return;
		}

		super.update();
		
		// Shield movement in XY axis acording to the player position
		Integer horizontalCorrection = (this.width - this.player.width)/2;
		Integer verticalCorrection = (this.height - this.player.height)/2;

		// Adjusting player position with force shield
		this.x = this.player.x - horizontalCorrection;
		this.y = this.player.y - verticalCorrection;
	}

	/*
	 * Handle when contact happen
	 * (non-Javadoc)
	 * @see entity.GameEntity#didContact(entity.GameEntity)
	 */
	@Override
	public void didContact(GameEntity entity){
		
		if (entity.getClass() == Enemy.class){
			entity.receiveDamage(100);
			this.receiveDamage(10);
			System.out.println("hit enemy"); //$NON-NLS-1$
		}
	}
	
	// The observer receives the game events and update all his dependents
	private GameEntityObserver observer = null;

	@Override
	public void setLife(int newLife){

		this.life = newLife;
		
		if (this.life <= 0) {
			this.life = 0;
			this.isCollidable = false;
		}
		
		// Notifying HUD to update shield life bar
		assert(this.observer != null) : "Player log: HUD is null"; //$NON-NLS-1$
		this.observer.notifyObserver(this);
		
	}

	/**
	 * HUD observer getter and setter 
	 * @return
	 */
	public GameEntityObserver getObserver() {
		return this.observer;
	}

	public void setObserver(GameEntityObserver observer) {
		assert(observer != null): "observer is null"; //$NON-NLS-1$
		this.observer = observer;
	}

	/*
	 * reborn player and set his position, turning it collidable again 
	 * (non-Javadoc)
	 * @see entity.GameEntity#reborn()
	 */
	@Override
	public void reborn(){
		super.reborn();
		this.x = this.player.x;
		this.y = this.player.y;
		this.isCollidable = true;
		this.observer.notifyObserver(this);
	}
}
