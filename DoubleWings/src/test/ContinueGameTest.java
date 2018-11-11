package test;

import static org.junit.Assert.*;

import java.util.Timer;

import org.junit.Before;
import org.junit.Test;

import constants.WindowConstants;
import game.GameController;
import jplay.InputBase;
import jplay.Keyboard;
import jplay.Parallax;
import jplay.Sprite;
import scenes.ErrorScene;
import scenes.GameScene;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.SpritePosition;

public class ContinueGameTest {
	private Sprite sprite;
	final String PATH = "src/assets/img/continue/continue.png";
	private SpritePosition spos;
	private Keyboard keyboard;
	private Parallax parallax;
	private Timer timer;
	private CountDownTimer countDown;
	private GameController game;
	private GameScene errrorScene;
	private MenuScene transitScene;
	
	@Before
	public void setup() {
		sprite = new Sprite(PATH);
		spos = new SpritePosition();
		keyboard = new Keyboard();
		parallax = new Parallax();
		timer = new Timer();
		game = new GameController();
		errrorScene = new ErrorScene();
		transitScene = new MenuScene();
	    countDown = new CountDownTimer();
	}
	
	@Test
	public void buildInitialSceneTest() {
		assertNotNull(sprite);
		assertNotNull(spos);
		assertNotNull(keyboard);
		assertNotNull(parallax);
	}
	
	@Test
	public void buildWaitSceneTest() {
		assertNotNull(timer);
		assertNotNull(countDown);
	}
	
	@Test
	public void updateSceneTest() {
		//not implement yet
	}
	
	@Test
	public void finishSceneTest() {
		assertNotNull(game);
	}
	
	@Test
	public void transitErrorSceneTest() {
		assertNotNull(errrorScene);
	}
	
	@Test
	public void updateImageForIndexTest() {
		//not implement yet
	}
	
	@Test
	public void checkButtonSelectionTest() {
		assertNotNull(transitScene);
	}
	
}
