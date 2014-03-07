package com.JB.Empiricus.level.tile.GameTiles;

import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.level.tile.Tile;

public class BlockSETile extends Tile{

	public BlockSETile(Sprite sprite) {
		super(sprite);
	}

	public void render (int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);	
	}
	
	public boolean solid (){
		return true;
	}

	private boolean breakable ()
	{
		return false;
		
	}
}
