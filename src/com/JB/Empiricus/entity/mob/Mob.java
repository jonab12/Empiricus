package com.JB.Empiricus.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.JB.Empiricus.entity.Entity;
import com.JB.Empiricus.entity.particle.Particle;
import com.JB.Empiricus.entity.projectile.BulletProjectile;
import com.JB.Empiricus.entity.projectile.Projectile;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;


//Main mob class - need sprites
public abstract class Mob extends Entity {

	//Mob variables
	
	protected boolean moving = false;
	
	protected boolean walking = false;
	
	protected enum Direction {
	    	UP,DOWN,LEFT,RIGHT
	    }
	    
	protected Direction direction;

	
	public void move (double xabs, double yabs){
		
		if (xabs !=0 && yabs !=0) {
	    	   move(xabs, 0);
	    	   move(0, yabs);
	    	   return;
	       }
		
		if (xabs > 0) direction = Direction.RIGHT; // move right
		if (xabs < 0) direction = Direction.LEFT; // move left
		if (yabs > 0) direction = Direction.DOWN; // move down
		if (yabs < 0) direction = Direction.UP; // move up
		
		//for x
		while (xabs != 0){
			if (Math.abs(xabs) > 1){
				if (!collision(abs(xabs),yabs)){
					this.x+= abs(xabs);			
					
				}	
				xabs-= abs(xabs);
			}
			else {
				if (!collision(abs(xabs),yabs)){
					this.x+= xabs;			
				}
				xabs = 0;
			}
		}
		
		//for y
		while (yabs != 0){
			if (Math.abs(yabs) > 1){
				if (!collision(xabs,abs(yabs))){
					this.y+= abs(yabs);		
			}
				yabs-= abs(yabs);
			}
			else {
				if (!collision(xabs,abs(yabs))){
					this.y+= yabs;	
			}
				yabs = 0;
			}
		}
		
		
		}
	
	private int abs(double value){
		if (value < 0) return -1;
	    return 1;
	}
	
    public abstract void update ();
  
    public abstract void render (Screen screen);
    
    //firing projectile
    protected void shoot (double x, double y, double direction){
    	//direction *= 180 / Math.PI; // get direction in degrees
        Projectile p = new BulletProjectile (x,y, direction);   
    	level.add (p);
    	
	
    }
    
    private boolean collision(double xabs, double yabs) {
		boolean solid = false;
		// for corner collision
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xabs) - c % 2 * 16) / 16;
			double yt = ((y + yabs)- c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor (xt);
			if (c / 2 == 0) iy = (int) Math.floor (yt);
			if (level.getTile (ix, iy).solid()) {
				solid = true;
			}
		}
		return solid;
	}
    
    
    
}
