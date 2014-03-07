package com.JB.Empiricus.entity.mob;

import java.util.List;

import com.JB.Empiricus.entity.mob.Mob.Direction;
import com.JB.Empiricus.graphics.AnimatedSprite;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.graphics.SpriteSheet;
import com.JB.Empiricus.level.Node;
import com.JB.Empiricus.util.Vector2i;

//A Dum AI that chases the player
public class SmartChaser extends Mob{

	
	private AnimatedSprite down = new AnimatedSprite (SpriteSheet.star_enemy_down, 32, 32,3);
	private AnimatedSprite up = new AnimatedSprite (SpriteSheet.star_enemy_up, 32, 32,3);
	private AnimatedSprite left = new AnimatedSprite (SpriteSheet.star_enemy_left, 32, 32,3);
	private AnimatedSprite right = new AnimatedSprite (SpriteSheet.star_enemy_right, 32, 32,3);
	
	private AnimatedSprite animSprite = down;
	
	private double xabs = 0;
	private double yabs = 0;
	private double speed = 0.7;

	private List<Node> path = null;
	private int time = 0;
	
	public SmartChaser (int x, int y){
		this.x = x << 4;
		this.y = y << 4;
		sprite = down;
	}
	
	private void move() {
		xabs = 0;
		yabs = 0;
		
		//Shouldne have to cast to an integer for any of these
		int px = (int) level.getPlayerAt(0).getX();
		int py = (int) level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i ((int)(getX() / 16),(int)(getY() / 16));
		Vector2i goal = new Vector2i (px / 16, py/16);
		
		if (time % 3 == 0) path = level.findPath(start, goal); // find path once per second for efficiency
		if (path != null){
			if (path.size() > 0){
				Vector2i vec = path.get(path.size()-1).tile;
				if (x < vec.getX() * 16) xabs++;
				if (x > vec.getX() * 16) xabs--;
				if (y < vec.getY() * 16) yabs++;
				if (y > vec.getY() * 16) yabs--;
			}
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
		time++;
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
