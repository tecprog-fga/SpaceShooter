package test;

import static org.junit.Assert.*;

import java.util.Timer;

import org.junit.Before;
import org.junit.Test;

import constants.WindowConstants;
import game.GameController;
import jplay.GameImage;
import jplay.InputBase;
import jplay.Keyboard;
import jplay.Parallax;
import jplay.Sprite;
import scenes.ErrorScene;
import scenes.GameScene;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.SpritePosition;

public class ErrorSceneTest {
	private Sprite sprite;
	final String PATH = "src/assets/img/continue/temp_background.png";
	private SpritePosition spos;
	private GameImage background;
	
	@Before
	public void setup() {
		sprite = new Sprite(PATH);
		spos = new SpritePosition();
		background = new GameImage(PATH);
	}
	
	@Test
	public void buildInitialSceneTest() {
		assertNotNull(background);
		assertNotNull(spos);
		assertNotNull(sprite);
	}
	
}
