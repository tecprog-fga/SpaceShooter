package game;

import java.util.ArrayList;
import entity.pool.*;
import game.evolver.*;
import jplay.Keyboard;
import entity.Bullet;
import entity.Enemy;
import entity.GameEntity;
import scenes.ClassicContinue;
import scenes.GameScene;

public class World {
	
	private ArrayList<GameEntity> objs;
	private ArrayList<GameEntity> deadObjs; //Array that Handles dead entities
	private GameEvolver evolver = new GameEvolver();
	private EnemyPool enemyPool = new EnemyPool();
	private BulletPool bulletPool = new BulletPool();
	
	public Keyboard keyboard = null;
	
	public World() {
		objs = new ArrayList<GameEntity>();
		deadObjs = new ArrayList<GameEntity>();
		
		evolver.start();
	}
	
	public void add(GameEntity entity) {
		objs.add(entity);			
	}
	
	public void remove(GameEntity entity) {
		objs.remove(entity);
	}
	
	public void update() {
		
		evolver.update();
		
		// check all collisions
		for(int i = 0; i < objs.size(); i++) {
			GameEntity obj1 = objs.get(i);
			
			// i + 1 to not repeat obj collision check
			for(int k = i+1; k <objs.size(); k++) {
				GameEntity obj2 = objs.get(k);
				
				if(obj1.collided(obj2) && obj1.isCollidable && obj2.isCollidable) {
					obj1.didContact(obj2);
					obj2.didContact(obj1);
				}
			}
			
			// Draw and update all objects
			obj1.draw();
			obj1.update();
			
			// Update object position
			obj1.x += obj1.velx;
			obj1.y += obj1.vely;
			
			// Check if entity is dead
			if (obj1.isDead()) {
				deadObjs.add(obj1);
			}
		}
		
		for (GameEntity deadObj : deadObjs){
			boolean didRemove = objs.remove(deadObj);
			
			//Enemy pool recycling
			if (deadObj.getClass() == Enemy.class){
				enemyPool.acquire((Enemy) deadObj);
			}
			
			//Bullet pool recycling
			if (deadObj.getClass() == Bullet.class){
				bulletPool.acquire((Bullet) deadObj);
			}
			
			if (didRemove == true){
				System.out.println("Entity removed from the world");
			}else{
				System.out.println("Error removing entity");
			}
		}
		
		
		deadObjs.clear();
	}
	
	//GameEvent Facade
	public void addEvent(GameEventCallback callback, int time, int type, String name){
		GameEvent event = this.createNewEvent(callback, time, type, name);
		this.evolver.add(event);
	}
	
	public void addEventAfterCurrentTime(GameEventCallback callback, int time, int type, String name){
		GameEvent event = this.createNewEvent(callback, time, type, name);
		event.time += evolver.getCurrentIteration();
		this.evolver.add(event);
	}
	
	private GameEvent createNewEvent(GameEventCallback callback, int time, int type, String name){
		GameEvent event = new GameEvent();
		event.setCallback(callback);
		event.time = time;
		event.name = name;
		event.type = type;
		
		return event;
	}
	
	
	// Object Pool facade
	public Enemy createEnemy(){
		Enemy enemy = enemyPool.release();
		enemy.reborn();
		return enemy;
	}
	
	public void releaseEnemy(Enemy enemy){
		enemyPool.acquire(enemy);
	}
}

