package com.JB.Empiricus.entity.projectile;

import java.util.Random;

import com.JB.Empiricus.entity.Entity;
import com.JB.Empiricus.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin; // spawn of projectile 
	protected double angle;
	protected Sprite sprite;
	protected double distance; // distance from origin
	protected double x,y; //over-ride x and y values since higher in hierarchy
	protected double newx,newy; //vectors
	protected double speed, range, damage;
	
	protected final Random random = new Random ();
	
	//Within the parameters we can add damage, speed and ROF. But that should be in sub-classes
	public Projectile (double x, double y, double direction){
	xOrigin = x;
	yOrigin = y;
	angle = direction;
	this.x = x;
	this.y = y;
		
	}
	public Sprite getSprite() {
		return sprite;
	}
	public int getSpritesize() {
		return sprite.SIZE;
	}
	
	protected void move (){
		
	}
}
