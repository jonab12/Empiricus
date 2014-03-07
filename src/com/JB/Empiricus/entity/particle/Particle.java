package com.JB.Empiricus.entity.particle;

import com.JB.Empiricus.entity.Entity;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;

public class Particle extends Entity {

	
	private Sprite sprite;
	private int time = 0;

	private int life;
	protected double xx, yy, zz;
	protected double xa, ya, za; // amount of pixels particle moves in x and y axis

	public Particle(int x, int y, int life) {
		this.x = x; // declaration is in entity superclass
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) -10);
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian(); // between -1 and 1
		this.ya = random.nextGaussian() + 2.2;
		this.zz = random.nextFloat() + 2.0;
	}

	
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) {
			remove();
		}
		za -= 0.1;
		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.5;
			ya *= 0.5;
		}
		
		move (xx + xa, (yy + ya) + (zz + za));
		
	}
	
	private void move(double x, double y) {
		
		if (Collision(x,y)){
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
		
	}

	public boolean Collision(double x, double y) {
		boolean solid = false;
		// four corner collision algorithm
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor (xt);
			if (c / 2 == 0) iy = (int) Math.floor (yt);
			if (level.getTile (ix,iy).solid()) solid = true;
		}
		return solid;
	}

	public void render (Screen screen){
		screen.renderSprite((int)xx, (int) yy - (int) zz, sprite, true);
		
	}
	
	
}
