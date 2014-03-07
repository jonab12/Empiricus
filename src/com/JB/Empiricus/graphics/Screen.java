package com.JB.Empiricus.graphics;

import java.util.Random;

import com.JB.Empiricus.entity.mob.Mob;
import com.JB.Empiricus.entity.mob.Player;
import com.JB.Empiricus.entity.mob.SmartChaser;
import com.JB.Empiricus.entity.projectile.Projectile;
import com.JB.Empiricus.level.tile.Tile;

public class Screen {

	public int width, height;
	public int [] pixels;
	public int xOffset, yOffset;
	public final int MAP_SIZE  = 64;
	public final int MAP_SIZE_MASK  = MAP_SIZE -1;
	public int [] tiles = new int [MAP_SIZE * MAP_SIZE];
	
	public Screen (int width, int height)
	{
		this.width = width; // takes dimensions from other class
		this.height = height;
		pixels = new int [width * height]; // create an int for each pixel in game
		
	}
	
	public void clear () // clear screen, then update with new image
	{
		for (int i = 0; i < pixels.length; i++)
		{
		pixels [i] = 0; 
		}
		
	}
	
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sheet.HEIGHT; y++) {
            int ya = y + yp;
            for (int x = 0; x < sheet.WIDTH; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
            }
        }
    }
	
   
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }
	
	//Based on players position we need to offset the tiles accordingly
	
	/* Our player character will need to maintain a 'absolute position' inorder to move
	 * around its respective enviorment. Relative sprite positions stay the same and move along
	 * with their respective sprites. Basically we need to change the world based on
	 * the main characters position like a follow up camera. If there is no main player
	 * then we use that point to move the camera.
	 */
	public void renderTile	(int xp, int yp, Tile tile) //positions of tiles and tile type
{
		xp -= xOffset;
		yp -= yOffset;
	
for (int y = 0; y < tile.sprite.SIZE; y++){
 int yabs = y + yp;  
 for (int x = 0; x < tile.sprite.SIZE; x++){
	 int xabs = x + xp;  
	 if (xabs < - tile.sprite.SIZE || xabs >= width || yabs < 0 || yabs >= height) break;
     if (xabs < 0) xabs = 0;
	 // deals which pixels get rendered - which pixels of sprite get rendered
	 pixels [xabs + yabs *width] = tile.sprite.pixels [x + y * tile.sprite.SIZE];
    }
   }
	
}
public void renderProjectiles(int xp, int yp, Projectile p) {
	  xp -= xOffset;
	  yp -= yOffset;
	  for (int y =0; y < p.getSpritesize(); y++) {
		 int ya = y + yp;
		 for (int x =0; x < p.getSpritesize(); x++) {
  		 int xa = x + xp; 
  		 if (xa < - p.getSpritesize() || xa >= width || ya < 0 || ya >= height) break;
	             if (xa <0) xa = 0;
	          int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
	          if (col != 0xffFFAEC9) pixels [xa + ya * width] = col;
		 }
	  }
}



public void renderMob (int xp, int yp, Sprite sprite, int flip){
	xp -= xOffset;
	yp -= yOffset;
		
	for (int y = 0; y < 32; y++){
	 int yabs = y + yp; 
	int ys = y;
	 if (flip == 2 || flip == 3)  ys = 31 - y;
	 for (int x = 0; x < 32; x++){
		 int xabs = x + xp;  
		 int xs = x;
		 //x-sprite - flipping our sprite
		if (flip == 1 || flip == 3) xs = 31 - x;
		
		 if (xabs < - 32 || xabs >= width || yabs < 0 || yabs >= height) break;
	     if (xabs < 0) xabs = 0;
	     int col = sprite.pixels [xs + ys * 32];
	     // I added ff to remove pink from image. Because on SpriteSheet pixels are set to get RGB value
	     if (col != 0xffFFAEC9)  pixels [xabs + yabs *width] = col;
		 // deals which pixels get rendered - which pixels of sprite get rendered
	    }
	   }
}

public void renderMob (int xp, int yp, Mob mob){
	xp -= xOffset;
	yp -= yOffset;
		
	for (int y = 0; y < 32; y++){
	 int yabs = y + yp; 
	int ys = y;
	 for (int x = 0; x < 32; x++){
		 int xabs = x + xp;  
		 int xs = x;
		 if (xabs < - 32 || xabs >= width || yabs < 0 || yabs >= height) break;
	     if (xabs < 0) xabs = 0;
	     int col = mob.getSprite().pixels [xs + ys * 32];
	     if (col != 0xffFFAEC9)  pixels [xabs + yabs *width] = col;
	    }
	   }
}


public void setOffset (int xOffset, int yOffset)
{
	this.xOffset = xOffset;
	this.yOffset = yOffset;
}





}

