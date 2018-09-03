package scenes.stages.stage1;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Parallax;
import game.World;
import hud.HUD;
import entity.Enemy;
import entity.player.*;
import commands.*;
import constants.WindowConstants;
import scenes.ContinueGame;
import scenes.GameOver;
import scenes.GameScene;
import game.evolver.GameEvent;
import game.evolver.GameEventCallback;

public class FirstStage extends GameScene implements GameEventCallback, PlayerSceneDelegate {

	private World gameWorld = null;

	@Override
	public void buildInitialScene() {

		gameWorld = new World();
		gameWorld.keyboard = this.keyboard;

		// Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(Keyboard.SPACE_KEY, Keyboard.DETECT_EVERY_PRESS);

		// Second Player configuration
		keyboard.addKey(KeyEvent.VK_A, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_D, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);

		configureEntities();

		// Development purposes
		creatingCommands();
		this.configureEvents();
	}

	private Parallax parallaxEffect = null;

	protected void viewSetup() {
		
		final String LAYER0_PATH = "src/assets/img/background_layer_0.png"; //$NON-NLS-1$
		final String LAYER1_PATH = "src/assets/img/background_layer_1.png"; //$NON-NLS-1$
		final String LAYER2_PATH = "src/assets/img/background_layer_2.png"; //$NON-NLS-1$
		// Creation a object to class Parallax
		parallaxEffect = new Parallax();

		// The first one added will be the last one to be painted.
		parallaxEffect.add(LAYER0_PATH);
		parallaxEffect.add(LAYER1_PATH);
		parallaxEffect.add(LAYER2_PATH);
		// parallax.add("src/assets/img/universe2.jpg");
		// parallax.add("src/assets/img/universe3.jpg");
		// //Since universe4.jpg was the last to be added to the list, it will be the
		// main layer (mainLayer).
		// parallax.add("src/assets/img/universe4.jpg");

		parallaxEffect.getLayer(0).setVelY(0.5);
		parallaxEffect.getLayer(1).setVelY(4.5);
		parallaxEffect.getLayer(2).setVelY(5);
		// parallax.getLayer(2).setVelY(2);
		// parallax.getLayer(3).setVelY(5);
		// parallax.getLayer(4).setVelY(10);

	}

	private ArrayList<Command> commands = null;
	private Command currentCommand = null;

	private void creatingCommands() {
		
		commands = new ArrayList<Command>();

		commands.add(CommandCreator.createPlayerCommand(CommandType.LEFT));
		commands.add(CommandCreator.createPlayerCommand(CommandType.DOWN));
		commands.add(CommandCreator.createPlayerCommand(CommandType.RIGHT));
		commands.add(CommandCreator.createPlayerCommand(CommandType.RIGHT));
		commands.add(CommandCreator.createPlayerCommand(CommandType.RIGHT));

		currentCommand = commands.remove(commands.size() - 1); // return removed object
	}

	private HUD hud = null;
	private Player player = new Player();

	private void configureEntities() {
		
		// Create the HUD and adding it as player's observer
		hud = new HUD();

		// Creating player sprite on the center-bottom of the screen
		player.initialPositionX = WindowConstants.WIDTH / 2;
		player.initialPositionY = WindowConstants.HEIGHT / 2;

		createSpaceShip();

		player.setObserver(hud);
		player.delegate = this;

		createAsteroid(2.0);
		createAsteroid(4.0);

		createTestAsteroid();
	}

	public void createSpaceShip() {
		
		// Creating player sprite
		PlayerSpaceship spaceship = null;
		
		spaceship = player.getSpaceship();

		spaceship.gameWorld = this.gameWorld;
		spaceship.setKeySet(Keyboard.UP_KEY, Keyboard.DOWN_KEY, Keyboard.RIGHT_KEY, Keyboard.LEFT_KEY,
				Keyboard.SPACE_KEY);

		gameWorld.add(spaceship);
		gameWorld.add(spaceship.getShield());
	}

	public final static int LIFES = 10;
	final String ASTEROID_PATH = "src/assets/img/asteroid.png"; //$NON-NLS-1$

	public void createAsteroid(double velY) {
		
		Enemy asteroid = null;

		asteroid = this.gameWorld.createEnemy();
		asteroid.loadImage(ASTEROID_PATH);
		asteroid.setLife(LIFES);
		asteroid.x = Math.random() * (WindowConstants.WIDTH - asteroid.width * 2) + asteroid.width;
		asteroid.y = -200;
		asteroid.vely = velY;

		gameWorld.add(asteroid);
	}

	private Enemy asteroid1 = null;

	public void createTestAsteroid() {
		
		asteroid1 = new Enemy(ASTEROID_PATH);
		asteroid1.setLife(LIFES);
		asteroid1.x = WindowConstants.WIDTH / 2 - asteroid1.width / 2;
		asteroid1.y = 0;
		asteroid1.vely = 2.0;

		gameWorld.add(asteroid1);
	}

	@Override
	public void updateScene() {
		
		updateParalax();

		gameWorld.update(); // Updates and draw all entities added in game world
		hud.draw(); // Draw all HUD elements

		executeAsteroidCommand();
	}

	public void updateParalax() {
		
		// Print all layers that have been added
		parallaxEffect.drawLayers();

		final int PIXELS_DOWN = 800;
		final int PIXELS_SIDES = 600;

		// The method below is responsible for maintaining infinite repetition of the
		// layers.
		parallaxEffect.repeatLayers(PIXELS_DOWN, PIXELS_SIDES, false);

		// Move the parallax orientation vertically
		parallaxEffect.moveLayersStandardY(false);
	}

	private int commandCount = 0;

	public final static int MAX_COMMANDS = 50;

	public void executeAsteroidCommand() {

		// Asteroid command execute
		commandCount += 1;

		if (commandCount >= MAX_COMMANDS && commands.size() > 0) {

			// System.out.println("Commands: " + commands.size());
			currentCommand = commands.remove(commands.size() - 1); // return removed object
			// System.out.println("current: "+ String.valueOf(currentCommand));

			commandCount = 0;
		}

		if (currentCommand != null) {
			
			currentCommand.executeDisplacement(asteroid1);
		}
	}
	
	// Callback event handler
	@Override
	public void eventCallback(GameEvent event) {

		// System.out.println("Event callback received with type: " + event.type);

		switch (event.type) {
		case 1:
			launchEnemyDown();
			break;
		case 2:
			launchEnemyCrazy();
			break;
		default:
			this.addEvents();
			break;
		}
	}

	public void launchEnemyDown() {
		// System.out.println("Launch Enemy Down! \\o/");
		this.createAsteroid(2.0);
	}

	public void launchEnemyCrazy() {
		// System.out.println("Crazy enemy in sight! ò.Ó ");
		this.createAsteroid(4.0);
	}

	public void configureEvents() {
		this.addEvents();
	}

	public void addEvents() {

		// 200 = default
		// 1 = first enemy
		// 2 = second type enemy
		this.gameWorld.addEventAfterCurrentTime(this, 700, 200, "Recursive script");

		this.gameWorld.addEventAfterCurrentTime(this, 200, 1, "Enemy down");

		this.gameWorld.addEventAfterCurrentTime(this, 400, 2, "Enemy Crazy bastard");

		this.gameWorld.addEventAfterCurrentTime(this, 250, 1, "Enemy down");
		this.gameWorld.addEventAfterCurrentTime(this, 300, 1, "Enemy down");
		this.gameWorld.addEventAfterCurrentTime(this, 400, 1, "Enemy down");

		this.gameWorld.addEventAfterCurrentTime(this, 50, 2, "Enemy Crazy bastard");
		this.gameWorld.addEventAfterCurrentTime(this, 620, 2, "Enemy Crazy bastard");
		this.gameWorld.addEventAfterCurrentTime(this, 660, 2, "Enemy Crazy bastard");
		this.gameWorld.addEventAfterCurrentTime(this, 690, 2, "Enemy Crazy bastard");
	}

	// Player Scene Delegate
	@Override
	public void transitToGameOver() {
		GameOver gameOver = null;
		gameOver = new GameOver();
		this.game.transitTo(gameOver);
	}

	@Override
	public void transitToContinue() {
		GameScene scene = null;
		scene = new ContinueGame();
		this.game.transitTo(scene);
	}
}
