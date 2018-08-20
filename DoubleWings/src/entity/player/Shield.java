package entity.player;

import entity.Enemy;
import entity.GameEntity;
import observer.GameEntityObserver;

public class Shield extends GameEntity {

	private static String spriteFilePath = "src/assets/img/forceshield.png";
	private int regeneration;
	private GameEntity player;
	private GameEntityObserver observer = null;
	
    
	//Creation constructor to Shield
	public Shield(GameEntity player) {

		//Initialization with shield image
		super(spriteFilePath);

		//Getting the player from the StageTest class
		this.player = player;
		this.life = maxLife;

		//Putting shield on the screen with reference the player position
		this.x = player.x;
		this.y = player.y;
	}

	//Method to update the shield according the player
	public void update() {
		
		//System.out.println(this.observer);
		
		if(this.isCollidable == false){
			this.x = -500;
			this.y = -500;
			return;
		}
		
		super.update();
		
		//Shield movement
		Integer horizontalCorrection = (this.width - player.width)/2;
		Integer verticalCorrection = (this.height - player.height)/2;

		//Adjusting position player with force shield
		this.x = player.x - horizontalCorrection;
		this.y = player.y - verticalCorrection;
	}

	// Handle when contact happen
	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Enemy.class){

			entity.receiveDamage(100);
			this.receiveDamage(10);
			System.out.println("hit enemy");
		}
	}

	@Override
	public void setLife(int newLife){
		
		this.life = newLife;
		
		//System.out.println(life);
		
		if (life <= 0) {
			life = 0;
			this.isCollidable = false;
		}
		
		//Notifing HUD to update shield life bar
		if (observer != null) {
			observer.notifyObserver(this);
		} else {
			System.out.println("Shield log: HUD is null :(");
		}
	}

	// HUD observer getter and setter 
	public GameEntityObserver getObserver() {
		return this.observer;
	}

	public void setObserver(GameEntityObserver observer) {
		this.observer = observer;
	}

	@Override
	public void reborn(){
		super.reborn();
		this.x = this.player.x;
		this.y = this.player.y;
		
		this.isCollidable = true;
		
		observer.notifyObserver(this);
	}
}
