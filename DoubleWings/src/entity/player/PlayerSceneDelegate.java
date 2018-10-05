/*****************************************************************
 * File: PlayerSceneDelegate.java
 * Purpose: PlayerSceneDelegate interface implementation
 *****************************************************************/

package entity.player;

/**
 * This interface delegate the player to GameOver scene
 * or the Continue scene
 */
public interface PlayerSceneDelegate {
	
	/**
	 * Takes the player to game over screen after loosing all his lives
	 */
	public void transitToGameOver();
	
	/**
	 * Takes the player to continue game, after loosing all his chances (lives)
	 */
	public void transitToContinue();
}
