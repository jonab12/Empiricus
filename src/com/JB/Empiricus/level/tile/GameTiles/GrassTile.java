package com.JB.Empiricus.level.tile.GameTiles;

import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.level.tile.Tile;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}
		//Press cntrl and on class to be taken there.
		public void render (int x, int y, Screen screen)
		{// Do render stuff here
			screen.renderTile(x << 4, y << 4, this);
			//To get tile precision we use  << 4 which is * 16 but faster since a binary operation (efficiency)
		}
		
		public boolean solid (){
			return false;
		}
}
