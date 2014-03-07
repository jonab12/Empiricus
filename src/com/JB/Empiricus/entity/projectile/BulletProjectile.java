package com.JB.Empiricus.entity.projectile;

import com.JB.Empiricus.entity.particle.Particle;
import com.JB.Empiricus.entity.spawner.ParticleSpawner;
import com.JB.Empiricus.entity.spawner.Spawner;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;

public class BulletProjectile extends Projectile{

	public static final int FIRE_RATE = 5; // time within projectiles
	
	public BulletProjectile (double x, double y, double direction){
		super (x,y,direction);
		
		//attributes
		range = 200;
		speed = 10;
		damage = 20;
		sprite = Sprite.projectile_soldier;
		
		//We use vectors to determine newx
		newx = speed * Math.cos(angle);
		newy = speed * Math.sin(angle);
	}
	
	public void update (){
		if (level.tileCollision((int)(x + newx),(int) (y + newy), 4,4,0)) { // last parameter should be 8
			level.add(new ParticleSpawner ((int)x , (int)y, 50, 44, level));
			remove();
			
		}
		move ();
	}
	
	protected void move (){
		x += newx;
		y += newy;
		if (calculateDistance() > range) remove ();
		
	}
	
	private double calculateDistance () {
		double dist = 0;
		dist = Math.sqrt (Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
	
	public void render (Screen screen){
		 screen.renderProjectiles ((int) x - 6, (int)y -2, this);
		
	}
	
}
