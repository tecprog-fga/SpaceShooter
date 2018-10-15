/**
 * File: GameEntityObserver.java
 * Purpose: Observer the entity for realize notification
 */

package observer;

/**
 * This interface is responsable for observer the entity for realize notification
 */
public interface GameEntityObserver {
	/**
	 * Method for notify the entity
	 * @param entity
	 */
	public void notifyObserver(Object entity);
}
