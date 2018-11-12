/*********************************************************
  * File: World.java
  * Purpose: World class implementation
  *********************************************************/


package game;

import java.util.ArrayList;
import entity.pool.*;
import game.evolver.*;
import jplay.Keyboard;
import entity.Bullet;
import entity.Enemy;
import entity.GameEntity;
import scenes.ContinueGame;
import scenes.GameScene;

/**
 * This class projects the game scenario and its elements
 */
public class World {
	
	public Keyboard keyboard = null;
	
	public World() {
		objs = new ArrayList<GameEntity>();
		deadObjs = new ArrayList<GameEntity>();
		evolver.start();
	}
	
	/**
	 * adds an entity to the scenario
	 * @param entity
	 */
	public void add(GameEntity entity) {
		objs.add(entity);			
	}
	
	/**
	 * removes the entity to the scenario
	 * @param entity
	 */
	public void remove(GameEntity entity) {
		objs.remove(entity);
	}
	
	public void update() {	
		evolver.update();
		
		/**
		 * check all collisions
		 */
		for (int i = 0; i < objs.size(); i++) {
			GameEntity obj1 = objs.get(i);
			
			/**
			 * object crash test
			 */
			for (int k = i + 1; k < objs.size(); k++) {
				GameEntity obj2 = objs.get(k);
				
				if (obj1.collided(obj2) && obj1.isCollidable && obj2.isCollidable) {
					obj1.didContact(obj2);
					obj2.didContact(obj1);
				}
				else {
					//Nothing to do
				}
			}
			
			//draw and update all objects
			obj1.draw();
			obj1.update();
			
			//update object position
			obj1.x += obj1.velx;
			obj1.y += obj1.vely;
			
			//check if entity is dead
			if (obj1.isDead()) {
				deadObjs.add(obj1);
			}
		}
		
		for (GameEntity deadObj : deadObjs){
			boolean didRemove = objs.remove(deadObj);
			
			/**
			 * enemy pool recycling
			 */
			if (deadObj.getClass() == Enemy.class) {
				enemyPool.acquire((Enemy) deadObj);
			}
			
			/**
			 * bullet pool recycling
			 */
			if (deadObj.getClass() == Bullet.class) {
				bulletPool.acquire((Bullet) deadObj);
			}
			
			/**
			 * message stating the elimination of the entity from the game scene
			 */
			if (didRemove == true) {
				System.out.println("Entity removed from the world");
			} 
			else {
				System.out.println("Error removing entity");
			}
		}
		
		deadObjs.clear();
	}
	
	/**
	 * object pool facade
	 * @return
	 */
	public Enemy createEnemy() {
		Enemy enemy = enemyPool.release();
		enemy.reborn();
		
		assert(enemy != null): ("enemy cannot be null");
		return enemy;
	}
	
	/**
	 * immediately launch the enemy on the scene
	 * @param enemy
	 */
	public void releaseEnemy(Enemy enemy) {
		enemyPool.acquire(enemy);
	}


	/**
	 * GameEvent facade
	 * @param callback
	 * @param time
	 * @param type
	 * @param name
	 */
	public void addEvent(GameEventCallback callback, int time, int type, String name) {
		GameEvent event = this.createNewEvent(callback, time, type, name);
		this.evolver.add(event);
	}
	
	/**
	 * GameEvent Facade
	 * @param callback
	 * @param time
	 * @param type
	 * @param name
	 */
	public void addEventAfterCurrentTime(GameEventCallback callback, int time, int type, String name) {
		GameEvent event = this.createNewEvent(callback, time, type, name);
		event.time += evolver.getCurrentIteration();
		this.evolver.add(event);
	}
	
	/**
	 * GameEvent facade
	 * @param callback
	 * @param time
	 * @param type
	 * @param name
	 * @return
	 */
	private GameEvent createNewEvent(GameEventCallback callback, int time, int type, String name){
		GameEvent event = new GameEvent();
		event.setCallback(callback);
		event.time = time;
		event.name = name;
		event.type = type;
		
		assert(event != null):("event cannot be null");
		return event;
	}
		
	private BulletPool bulletPool = new BulletPool();
	private EnemyPool enemyPool = new EnemyPool();
	private GameEvolver evolver = new GameEvolver();
	//Array's that Handles dead entities
	private ArrayList<GameEntity> objs = null;
	private ArrayList<GameEntity> deadObjs = null; 
}