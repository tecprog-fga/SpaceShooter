package entity;

//import Score.ScoreType;
import commands.Command;
import entity.player.Player;
import entity.player.PlayerSpaceship;

public class Enemy extends GameEntity {

	static private String spriteImagePath = "src/assets/img/temp_player.png";
	private int commandCount = 0;

	public Enemy(int x, int y) {
		super(Enemy.spriteImagePath);
		this.x = x;
		this.y = y;
	}
	
	public void executeBehavior(Command[] commands) {
		if (commandCount < commands.length) {
			if (commands[commandCount].execute(this)) {
				commandCount += 1;
			} else {/*donot*/}
		System.out.println("x: " + this.x + " y: " + this.y);
		} else {/*donot*/}
	}
	
	public Enemy(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Bullet.class) {
			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes
			Bullet bullet = (Bullet) entity;
			PlayerSpaceship spaceship = (PlayerSpaceship) bullet.owner;
			
			//Increase Player Score
			spaceship.getPlayer().increaseScore(100);
		}
	}
}
