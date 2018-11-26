package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jplay.Parallax;
import jplay.Sprite;

public class LoseTest {
	private Sprite sprite;
	private Parallax parallax;
	final String PATH = "src/assets/img/continue/continue.png";
	
	@Before
	public void setup() {
		sprite = new Sprite(PATH);
		parallax = new Parallax();
	}
	
	@Test
	public void buildInitialSceneTest() {
		assertNotNull(sprite);
		assertNotNull(parallax);
	}

}
