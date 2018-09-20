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
	public void transitToGameOver();
	public void transitToContinue();
}
