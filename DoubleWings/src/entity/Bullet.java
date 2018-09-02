package entity;

import entity.player.PlayerSpaceship;

public class Bullet extends GameEntity {
	
	private static final String IMAGE_BULLET = "src/assets/img/bullet_player.png";
	public GameEntity owner = null;
	
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
}