package com.JB.Empiricus.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Any key strokes pressed in our canvas will be recorded here.
public class Keyboard implements KeyListener{

	private boolean [] keys = new boolean [120]; //stores characters on keyboard up to 120 types
	public boolean up, down, left, right, reload, menu;
	
	//Update method we put in our main classes update method
	public void update ()
	{
	up = keys [KeyEvent.VK_UP]	|| keys[KeyEvent.VK_W];
	down = keys [KeyEvent.VK_DOWN]	|| keys[KeyEvent.VK_S];
	left = keys [KeyEvent.VK_LEFT]	|| keys[KeyEvent.VK_A];
	right = keys [KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	reload = keys [KeyEvent.VK_R];
	menu = keys [KeyEvent.VK_M];
	
	
	}
	
	public void keyPressed(KeyEvent e) {
		keys [e.getKeyCode()] = true;
		
	}

	
	public void keyReleased(KeyEvent e) {
		keys [e.getKeyCode()] = false;
		//get ID of key
		
	}

	
	public void keyTyped(KeyEvent e) {
	
		
	}

}
