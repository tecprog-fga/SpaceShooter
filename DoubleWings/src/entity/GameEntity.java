package entity;

import jplay.Sprite;
import game.World;


public class GameEntity extends Sprite {

	public String name;
	protected int life = 1;
	private boolean isDead = false;
	public Double velx = 0.0;
	public Double vely = 0.0;
	public int maxLife = 1;
	public boolean isCollidable = true;
	public World gameWorld = null;
	
	private double entityLimit = 1000; // Kill entity after leaving bounds
	
	
	public GameEntity(String fileName) {
		super(fileName);
		name = fileName;
	}

	//Life getter and setter
	public int getLife() {
		return this.life;
	}
	
	public void setLife(int newLife){
		this.life = newLife;
		
		if (life <= 0) {
			die();
		}
	}
	
	@Override
	public void update(){
		super.update();
		
		if( Math.abs(this.x) > this.entityLimit){
			this.isDead = true;
		}
		
		if( Math.abs(this.y) > this.entityLimit){
			this.isDead = true;
		}
	}
	
	
	
	public boolean isDead(){
		return isDead;
	}
	
	//Trigger an event when contact happens
	public void didContact(GameEntity entity) {
		System.out.println(this.name + " contact: " + entity.name);
	}
	
	public void receiveDamage(int damage){
		setLife(life - damage);
	}
	
	protected void die(){
		isDead = true;
		destroy();
	}
	
	public void destroy(){
		
	}

	public void reborn(){
		this.isDead = false;
		this.x = 0;
		this.y = 0;
		this.velx = 0.0;
		this.vely = 0.0;
		this.setLife(maxLife);
		this.name = "empty entity";
	}
}