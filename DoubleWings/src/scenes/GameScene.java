package scenes;

import game.GameController;
import jplay.Keyboard;

public abstract class GameScene {

	protected GameController game;
	protected Keyboard keyboard;

	public abstract void update();

	public void configure(GameController game) {
		
		this.game = game;
		this.keyboard = game.keyboard;
		final String MSG_KEYBOARD = "keyboard: "; //$NON-NLS-1$
		System.out.println(MSG_KEYBOARD + this.keyboard); 

		initialSetup();
		viewSetup();
	}
	protected abstract void initialSetup();
	protected abstract void viewSetup();

	public void destroy() {
		
		this.game = null;
		this.keyboard = null;
	}
}