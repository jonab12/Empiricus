package com.JB.Empiricus.entity.mob;

import java.util.List;

import javax.swing.JFrame;

import com.JB.Empiricus.MainGame;
import com.JB.Empiricus.entity.Entity;
import com.JB.Empiricus.entity.projectile.BulletProjectile;
import com.JB.Empiricus.entity.projectile.Projectile;
import com.JB.Empiricus.graphics.AnimatedSprite;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.graphics.SpriteSheet;
import com.JB.Empiricus.input.Keyboard;
import com.JB.Empiricus.input.Mouse;

public class Player extends Mob{

	private Keyboard input;
	private Sprite sprite; // sprite for player
	private int animate = 0;
	private boolean walking = false; // should be here??
	private AnimatedSprite down = new AnimatedSprite (SpriteSheet.player_down, 32, 32,3);
	private AnimatedSprite up = new AnimatedSprite (SpriteSheet.player_up, 32, 32,3);
	private AnimatedSprite left = new AnimatedSprite (SpriteSheet.player_left, 32, 32,3);
	private AnimatedSprite right = new AnimatedSprite (SpriteSheet.player_right, 32, 32,3);
	
	private AnimatedSprite animSprite = down;
	
	public int ammo = 30;
	
	private int fireRate = 0;
	
	//Main constructor
	public Player (Keyboard input) {
		this.input = input;
		sprite = sprite.player_forward;
		animSprite = down;
	}
	
	
	public Player (int x, int y, Keyboard input)
	{// When new level is loaded we need to create a new player object at a spawnpoint
	
		/*
		 You can hold cntrl and select x to see where we are taking the values from. As you can see we are taking the values
		 of the global variables in our super class and bringing them here.
		 */
		this.x = x; //We keep track of these variables - the sprite or player is them
		this.y = y;
		this.input = input;
		sprite = sprite.player_forward;
		fireRate = BulletProjectile.FIRE_RATE;
		
	}
	
	public void setAmmo (int ammo){
		this.ammo = ammo;
	}
	
	public int getAmmo (){
		return ammo;
	}
	
	//When player moves (listeners) we want to update it
	public void update (){
		
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		if (fireRate > 0) fireRate--;
			
	//over-rides mob update which over-rides entity update
	double xabs = 0, yabs = 0; // note down direction
	
	double speed = 1.0;
	/*
	//If animate is greater than 7500 it will bring it to 0 and start counting again
	if (animate < 7500) animate++;
	else animate = 0;
	*/
	if (input.up) {
		animSprite = up;
		yabs-= speed;
	}
	else if (input.down) {
		animSprite = down;
		yabs+= speed;
	}
	if (input.left) {
		animSprite = left;
		xabs-= speed;
	}
	else if (input.right) {
		animSprite = right;
		xabs+= speed;
	}

	if (xabs != 0 || yabs != 0) {
		move (xabs,yabs);
		walking = true;
	}
	else
	{
		walking = false;
		
	}
	

	clear();
	updateShooting ();
	}
		
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++){
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
		
	}


	private void updateShooting (){
		/*To determine where to shoot we use trigometry. Look at C:/Pictures/Game/Others to see
		
		Basically we shoot at an angle (at x,y) and to determine x and y we need to find the angle.
		We first find OPP. by (Xmouse position - Xplayer position) and get a absolute value for it.
		Then ADJ by same for y. Then we use cotangent to determine the angle.
		Atan is Atan2 - a function where if we divide by 0 it returns default
		*/
		
		if (Mouse.getButton() == 1 && fireRate <= 0){ // if you clock left mouse button
		
		double dx = Mouse.getX()- (MainGame.getWindowWidth())/2;
		double dy = Mouse.getY()- (MainGame.getWindowHeight())/2;	
		double direction = Math.atan2(dy, dx);

		if (ammo > 0) {
			ammo = ammo - 1;
		}
		// Ok you cannot use Thread.sleep for the game to add a delay. And multi-threads can cause issues...
		
		if (ammo > 0){
		shoot (x,y,direction);
		fireRate = BulletProjectile.FIRE_RATE;
		}
		
		}
		if (input.reload) ammo = 30;
	
	}


	//We need to set the location for the camera
	public void render (Screen screen){
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob ((int)(x-16) ,(int)(y-16) ,sprite,flip);
		
	}
}
