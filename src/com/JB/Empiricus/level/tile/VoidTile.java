package com.JB.Empiricus.level.tile;

import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public void render (int x, int y, Screen screen)
	{// Do render stuff here
		screen.renderTile(x << 4, y << 4, this);
	}
}
