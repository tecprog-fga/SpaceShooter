package hud;

import jplay.Sprite;
import observer.GameEntityObserver;

import java.awt.Color;
import java.awt.Font;

import constants.WindowConstants;
import entity.player.*;




public class HUD implements GameEntityObserver{

	private float barSizeMax = WindowConstants.WIDTH;
	
	private Sprite shieldLifeBar;
	private Sprite shieldLifeBarOrnament;
	private Sprite chancesSimbol;
	private Score scoreText;

	public HUD(){
		//Setting HUD elements initial setups
		shieldLifeBar = new Sprite("src/assets/img/hud/energy.png");
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
		this.shieldLifeBar.y = WindowConstants.HEIGHT - this.shieldLifeBar.height;
		
		shieldLifeBarOrnament = new Sprite("src/assets/img/hud/shield_bar_ornament.png");
		this.shieldLifeBarOrnament.x = 0;
		this.shieldLifeBarOrnament.y = WindowConstants.HEIGHT - this.shieldLifeBarOrnament.height;
    
		chancesSimbol = new Sprite("src/assets/img/hud/chances.png", 4);
		this.chancesSimbol.setCurrFrame(3);
		this.chancesSimbol.x = WindowConstants.WIDTH - chancesSimbol.width;
		this.chancesSimbol.y = 0;
		
		scoreText = new Score(10, 40);
		scoreText.setColor(Color.WHITE);
		scoreText.setFont(new Font("Arial",Font.TRUETYPE_FONT, 40));
		scoreText.setScore(0);
	}

	// TIP: Perhaps use a pattern to specialize all the updates
	public void update(){
	  
	}

	public void draw(){
		shieldLifeBar.draw();
		shieldLifeBarOrnament.draw();
		chancesSimbol.draw();
		scoreText.draw();
	}
  
	public void updateShieldLifeBar(Shield shield){
		//Make life bar width proportional to the shield current life
//		System.out.println("Shield changed in the hud");
//		System.out.println(shieldLife);
		float proportion = ((float)shield.getLife()/(float)shield.maxLife);
//		System.out.println(proportion);
		float newLifeBarWidth = proportion * barSizeMax;
		this.shieldLifeBar.width = (int) newLifeBarWidth;
//		System.out.println(shieldLifeBar.width);
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
	}

	//Update player chances on HUD
	public void updateChances(int playerChances){
		if (playerChances <= 3 && playerChances >= 0) {
			this.chancesSimbol.setCurrFrame(playerChances);
			
		} else {
			System.out.println("HUD log: Player chances number is out of range.");
			this.chancesSimbol.setCurrFrame(0);
		}
	}

	//Update player score on HUD
	public void updateScore(int score){
		scoreText.setScore(score);
	}

	//Take action depending of the game entity 
	@Override
	public void notifyObserver(Object entity) {
		
		if (entity instanceof Shield) {
			
			Shield shield = (Shield) entity;
			System.out.println("HUD log: Shield class identified." + shield.getLife() + " " + shield.maxLife);
			updateShieldLifeBar(shield);
			
		} else if (entity instanceof Player){
			//System.out.println("HUD log: Player class identified.");
			Player player = (Player) entity;
			updateChances(player.getChances());
			updateScore(player.getScore());
			
		} else {
			System.out.println("HUD log: No class identified.");
		}	
	}
}
