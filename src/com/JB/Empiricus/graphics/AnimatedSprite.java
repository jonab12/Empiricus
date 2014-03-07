package com.JB.Empiricus.graphics;

public class AnimatedSprite extends Sprite{

private int frames = 0;	
private Sprite sprite;
private int rate = 5;
private int length = -1; // amount of frames in animation
private int time = 0;

public AnimatedSprite (SpriteSheet sheet, int width, int height, int length)
{
 super (sheet, width, height);
 this.length = length;
 sprite = sheet.getSprites()[0];
 if (length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long!");

}

public void update () {
	time++;
	if (time % rate == 0){
	if (frames >= length - 1) frames = 0;
	else frames++;
	sprite = sheet.getSprites()[frames];
	}
}

public Sprite getSprite () {
	return sprite;
}
	
public void setFrameRate (int frames){
	rate = frames;
}

public void setFrame(int index) {
	if (index > sheet.getSprites().length - 1) {
		System.err.println ("Index out of bounds in  " + this);
		return;
	}
	sprite = sheet.getSprites()[index];
	
}

}
