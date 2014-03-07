package com.JB.Empiricus.entity.mob;

import java.util.List;

import com.JB.Empiricus.entity.mob.Mob.Direction;
import com.JB.Empiricus.graphics.AnimatedSprite;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.graphics.SpriteSheet;

//A Dum AI that chases the player
public class Chaser extends Mob{

	
	private AnimatedSprite down = new AnimatedSprite (SpriteSheet.enemy_down, 32, 32,3);
	private AnimatedSprite up = new AnimatedSprite (SpriteSheet.enemy_up, 32, 32,3);
	private AnimatedSprite left = new AnimatedSprite (SpriteSheet.enemy_left, 32, 32,3);
	private AnimatedSprite right = new AnimatedSprite (SpriteSheet.enemy_right, 32, 32,3);
	
	private AnimatedSprite animSprite = down;
	
	private double xabs = 0;
	private double yabs = 0;
	private double speed = 0.7;

	public Chaser (int x, int y){
		this.x = x << 4;
		this.y = y << 4;
		sprite = down;
	}
	
	private void move() {
		xabs = 0;
		yabs = 0;
		List<Player> players = level.getPlayers(this,80);
		
		if (players.size() > 0){
			Player player = players.get(0);
		if (x < player.getX()) xabs+= speed;
		if (x > player.getX()) xabs-= speed;
		if (y < player.getY()) yabs+= speed;
		if (y > player.getY()) yabs-= speed;
		
		}
		if (xabs != 0 || yabs != 0) {
			move (xabs,yabs);
			walking = true;
		}
		else
		{
			walking = false;
			
		}
		
	}
	
	public void update() {
		move();
		
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		if (yabs < 0) {
			animSprite = up;
			direction = Direction.UP;
		}
		else if (yabs > 0) {
			animSprite = down;
			direction = Direction.DOWN;
		}
		if (xabs < 0) {
			animSprite = left;
			direction = Direction.LEFT;
		}
		else if (xabs > 0) {
			animSprite = right;
			direction = Direction.RIGHT;
		}
		
	}

	
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int)(x - 16),(int) (y -16), this);
		
	}

	

}
