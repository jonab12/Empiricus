package com.JB.Empiricus.entity.mob;

import com.JB.Empiricus.graphics.AnimatedSprite;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.graphics.SpriteSheet;

//NOTE: You can create another clone of this class for e.g. cars so that they follow the road

public class Dummy extends Mob{

	
	private AnimatedSprite down = new AnimatedSprite (SpriteSheet.ally_down, 32, 32,3);
	private AnimatedSprite up = new AnimatedSprite (SpriteSheet.ally_up, 32, 32,3);
	private AnimatedSprite left = new AnimatedSprite (SpriteSheet.ally_left, 32, 32,3);
	private AnimatedSprite right = new AnimatedSprite (SpriteSheet.ally_right, 32, 32,3);
	
	private AnimatedSprite animSprite = down;
	
	private int time = 0;
	private int xabs = 1;
	private int yabs = 0;
	
    public Dummy (int x, int y) {
     this.x = x * 16;
     this.y = y * 16;
     sprite = Sprite.ally;
    }
	
    //Basic Pokemon AI
	public void update() {
		time++; // every second is 60 updates
		//time % 60 == 0; // = 1 second
		 
		if (time % (random.nextInt(50) + 30) == 0){
		xabs = random.nextInt(3) -1;
		yabs = random.nextInt(3) -1;
			if (random.nextInt(3) == 0){
			xabs = 0;
			yabs = 0;
			}
		}
		
		
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

		if (xabs != 0 || yabs != 0) {
			move (xabs,yabs);
			walking = true;
		}
		else
		{
			walking = false;
			
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, sprite, 0);
		
	}

	

}
