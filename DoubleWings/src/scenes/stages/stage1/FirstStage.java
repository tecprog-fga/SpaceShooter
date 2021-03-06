/**
 * File: FirstStage.java
 * Purpose: Create the first stage of the game
 */

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
import org.apache.log4j.Logger;

/**
 * This class is responsible to create the scenario, 
 * the parallax effect, the spaceship and the enemies
 */

public class FirstStage extends GameScene implements GameEventCallback, PlayerSceneDelegate {

	private World gameWorld = null;
	
	boolean errorOccurred = false;
	
	final static Logger logger = Logger.getLogger(FirstStage.class);

	// This method set the keys to control the spaceship and configure the entities
	@Override
	public void buildInitialScene() {

		try {
			gameWorld = new World();
			assert(gameWorld != null):("gameWorld cannot be null");
			
			gameWorld.keyboard = this.keyboard;
			assert(gameWorld.keyboard != null):("keyboard cannot be null");

			// Configuring the up and down keys for player one and for player two	
			keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
			keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_EVERY_PRESS);
			keyboard.setBehavior(Keyboard.SPACE_KEY, Keyboard.DETECT_EVERY_PRESS);

			keyboard.addKey(KeyEvent.VK_A, Keyboard.DETECT_EVERY_PRESS);
			keyboard.addKey(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);
			keyboard.addKey(KeyEvent.VK_D, Keyboard.DETECT_EVERY_PRESS);
			keyboard.addKey(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, gameWorld cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
		
		configureEntities();

		// Creating commands and configuring events that will be added
		creatingCommands();
		this.configureEvents();
	}

	private Parallax parallaxEffect = null;

	// This method set the parallax effect in the scenario
	protected void viewSetup() {
		
		final String LAYER0_PATH = "src/assets/img/background_layer_0.png"; 
		final String LAYER1_PATH = "src/assets/img/background_layer_1.png"; 
		final String LAYER2_PATH = "src/assets/img/background_layer_2.png"; 
		
		try {
			// Creation a object to class Parallax
			parallaxEffect = new Parallax();
			assert(parallaxEffect != null):("parallaxEffect cannot be null");

			// The first one added will be the last one to be painted.
			parallaxEffect.add(LAYER0_PATH);
			parallaxEffect.add(LAYER1_PATH);
			parallaxEffect.add(LAYER2_PATH);

			// Set the transition's speed of the layers
			parallaxEffect.getLayer(0).setVelY(0.5);
			parallaxEffect.getLayer(1).setVelY(4.5);
			parallaxEffect.getLayer(2).setVelY(5);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, parallaxEffect cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
	
	// This method creates the spaceship and its configuration
	public void createSpaceShip() {

		// Creating player sprite
		PlayerSpaceship spaceship = null;

		spaceship = player.getSpaceship();
		spaceship.gameWorld = this.gameWorld;
		try {
			assert(spaceship != null):("spaceship cannot be null");
			spaceship.setKeySet(Keyboard.UP_KEY, Keyboard.DOWN_KEY, Keyboard.RIGHT_KEY, Keyboard.LEFT_KEY,
					Keyboard.SPACE_KEY);

			gameWorld.add(spaceship);
			gameWorld.add(spaceship.getShield());
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, spaceship cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	// This method updates the parallax, updates and draw all entities added in the game
	@Override
	public void updateScene() {
		try {
			updateParalax();
			gameWorld.update(); // Updates and draw all entities added in game world
			hud.draw(); // Draw all HUD elements
			executeAsteroidCommand();
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	/**
	 * The method below is responsible for maintaining infinite 
	 * repetition of the layers 
	 */

	public void updateParalax() {

		final int PIXELS_DOWN = 800;
		final int PIXELS_SIDES = 600;
		
		try {
			// Print all layers that have been added
			assert(parallaxEffect != null):("spaceship cannot be null");
			parallaxEffect.drawLayers();

			parallaxEffect.repeatLayers(PIXELS_DOWN, PIXELS_SIDES, false);

			// Move the parallax orientation vertically
			parallaxEffect.moveLayersStandardY(false);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, parallaxEffect cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}	
	}
	
	/**
	 * This method creates the asteroids and its configuration
	 * @param velY
	 */
	public void createAsteroid(double velY) {
		
		Enemy asteroid = null;

		try {
			asteroid = this.gameWorld.createEnemy();
			asteroid.loadImage(ASTEROID_PATH);
			assert(asteroid != null):("asteroid cannot be null");

			asteroid.setLife(LIFES);
			asteroid.x = Math.random() * (WindowConstants.WIDTH - asteroid.width * 2) + asteroid.width;
			asteroid.y = -200;
			asteroid.vely = velY;

			gameWorld.add(asteroid);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, asteroid cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	private Enemy asteroid1 = null; //declaring an asteroid of the type enemy

	// This method sets the asteroids in the scenario and its initial position 
	public void createTestAsteroid() {

		try {
			asteroid1 = new Enemy(ASTEROID_PATH);
			assert(asteroid1 != null):("asteroid1 cannot be null");

			asteroid1.setLife(LIFES);
			asteroid1.x = WindowConstants.WIDTH / 2 - asteroid1.width / 2;
			asteroid1.y = 0;
			asteroid1.vely = 2.0;

			gameWorld.add(asteroid1);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, asteroid1 cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
		
	}

	private int commandCount = 0;

	public final static int MAX_COMMANDS = 50;

	public void executeAsteroidCommand() {

		// Asteroid command execute
		commandCount += 1;

		if (commandCount >= MAX_COMMANDS && commands.size() > 0) {

			currentCommand = commands.remove(commands.size() - 1); // return removed object

			commandCount = 0;
		}
		else {
			//Nothing to do
		}

		if (currentCommand != null) {
			
			currentCommand.executeDisplacement(asteroid1);
		}
		else {
			//Nothing to do
		}
	}

	private ArrayList<Command> commands = null;
	private Command currentCommand = null;

	// This method create the commands left, down and right
	private void creatingCommands() {
		
		try {
			commands = new ArrayList<Command>();
			assert(commands != null):("commands cannot be null");

			commands.add(CommandCreator.createPlayerCommand(CommandType.LEFT));
			commands.add(CommandCreator.createPlayerCommand(CommandType.DOWN));
			commands.add(CommandCreator.createPlayerCommand(CommandType.RIGHT));
			commands.add(CommandCreator.createPlayerCommand(CommandType.RIGHT));
			commands.add(CommandCreator.createPlayerCommand(CommandType.RIGHT));

			currentCommand = commands.remove(commands.size() - 1); // return removed object
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, asteroid1 cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	private HUD hud = null;
	private Player player = new Player();
	
	// This method creates the hud, the initial position of the spaceship and the asteroids 
	private void configureEntities() {
		
		try {
			// Create the HUD and adding it as player's observer
			hud = new HUD();
			assert(hud != null):("hud cannot be null");

			// Creating player sprite on the center-bottom of the screen
		
			final int WIDTH_CENTER = WindowConstants.WIDTH / 2; // Constant to create in the center of the width
			final int HEIGHT_CENTER = WindowConstants.HEIGHT / 2; // Constant to create in the center of the height
		
			assert(player != null):("player cannot be null");
		
			player.initialPositionX = WIDTH_CENTER;
			player.initialPositionY = HEIGHT_CENTER;

			createSpaceShip();

			player.setObserver(hud);
			player.delegate = this;

			createAsteroid(2.0);
			createAsteroid(4.0);
			createTestAsteroid();
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, player cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}	
	}

	public final static int LIFES = 10;
	final String ASTEROID_PATH = "src/assets/img/asteroid.png"; //Declaring constant with asteroid path
	
	// Callback event handler. Adding different events for each case
	@Override
	public void eventCallback(GameEvent event) {

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

	//Creating the first type of enemy
	public void launchEnemyDown() {
		
		try {
			this.createAsteroid(2.0);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, player cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	//Creating the second type of enemy
	public void launchEnemyCrazy() {
		
		try {
			this.createAsteroid(4.0);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, player cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
	
	// Adding the events created
	public void configureEvents() {
		
		try {
			this.addEvents();
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, player cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	public void addEvents() {

		/**
		 *  Adding events in the game world
		 *  200 = default
		 *  1 = first enemy
		 *  2 = second type enemy
		 */
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

	// Transition to game over screen
	@Override
	public void transitToGameOver() {
		
		GameOver gameOver = null;
		gameOver = new GameOver();
		
		try {
			assert(gameOver != null):("gameOver cannot be null");
			this.game.transitTo(gameOver);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, player cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}

	//Transition to the Continue screen
	@Override
	public void transitToContinue() {
		
		GameScene scene = null;
		scene = new ContinueGame();
		
		try {
			assert(scene != null):("scene cannot be null");
			this.game.transitTo(scene);
		}
		catch(NullPointerException exception) {
			logger.error("Null returned, player cannot be null", exception);
			
			exception.printStackTrace();
			errorOccurred = true;
		}
	}
}