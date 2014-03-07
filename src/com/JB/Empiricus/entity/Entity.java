package com.JB.Empiricus.entity;

import java.util.Random;

import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.level.Level;
/*
 Abstraction in Java or Object oriented programming is a way to segregate implementation from interface
An abstract class is something which is incomplete and you can not create instance of abstract class.

Use abstraction if you know something needs to be in class but implementation of that varies.
 */

//An entity interacts with our game - e.g. a mob,
// since an entity can be any object on our screen except map
public class Entity {

	//NOTE - Make a setter method or x/y public 
	protected int x, y; // controls location of particular entity on map
	protected Sprite sprite;
	private boolean removed = false;
	protected Level level;
	protected Random random = new Random ();
	
	public Entity(){
	}
	
	public Entity(int x, int y, Sprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	
	public void update (){
	}
	
	public void render (Screen screen) {
		if (sprite != null) screen.renderSprite ((int)x,(int)y,sprite,true);
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public void remove ()
	{ // Remove entity from level
		removed = true;
	}
	
	public boolean isRemoved (){
		return removed;
	}
	
	public void init (Level level){
		this.level = level;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
}
