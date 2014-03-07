package com.JB.Empiricus.level.tile;

import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.level.tile.GameTiles.BlockETile;
import com.JB.Empiricus.level.tile.GameTiles.BlockNTile;
import com.JB.Empiricus.level.tile.GameTiles.BlockNWTile;
import com.JB.Empiricus.level.tile.GameTiles.BlockRTile;
import com.JB.Empiricus.level.tile.GameTiles.BlockSETile;
import com.JB.Empiricus.level.tile.GameTiles.BlockSTile;
import com.JB.Empiricus.level.tile.GameTiles.BlockSWTile;
import com.JB.Empiricus.level.tile.GameTiles.BlockWTile;
import com.JB.Empiricus.level.tile.GameTiles.GrassTile;
import com.JB.Empiricus.level.tile.GameTiles.PatioTile;
import com.JB.Empiricus.level.tile.GameTiles.PavementTile;
import com.JB.Empiricus.level.tile.GameTiles.RoadHTile;
import com.JB.Empiricus.level.tile.GameTiles.RoadTile;
import com.JB.Empiricus.level.tile.GameTiles.RoadVTile;
import com.JB.Empiricus.level.tile.GameTiles.WaterTile;

/* A tile is a group of pixels
 
 Each tile should have a position and sprite assigned,
 to it. 
 */
public class Tile {

	public int x, y; 
	public Sprite sprite;
	
	
	/////////////////////////////////////TILES/////////////////////////////////////// INORDER OF APPEARENCE

	public static Tile blockR = new BlockRTile (Sprite.blockR);
	public static Tile blockS = new BlockSTile (Sprite.blockS);
	public static Tile blockE = new BlockETile (Sprite.blockE);
	public static Tile blockW = new BlockWTile (Sprite.blockW);
	public static Tile blockN = new BlockNTile (Sprite.blockN);
	public static Tile blockNE = new BlockNWTile (Sprite.blockNE);
	public static Tile blockNW = new BlockNWTile (Sprite.blockNW);
	public static Tile blockSW = new BlockSWTile (Sprite.blockSW);
	public static Tile blockSE = new BlockSETile (Sprite.blockSE);
	
	//roof tiles here
	
	public static Tile grass = new GrassTile (Sprite.grass);
	public static Tile pavement = new PavementTile (Sprite.pavement);
	public static Tile patio = new PatioTile (Sprite.patio);
	public static Tile road = new RoadTile (Sprite.road);
	public static Tile roadH = new RoadHTile (Sprite.roadH);
	public static Tile roadV = new RoadVTile (Sprite.roadV);
	public static Tile water = new WaterTile (Sprite.water);
	
	public static int col_blockR = 0xff808080;
	public static int col_blockS = 0xff808180;
	public static int col_blockE = 0xff808081;
	public static int col_blockW = 0xff818181;
	public static int col_blockN = 0xff828181;
	public static int col_blockNE = 0xff828281;
	public static int col_blockNW = 0xff828282;
	public static int col_blockSW = 0xff838282;
	public static int col_blockSE = 0xff838382;
							
	public static int col_grass = 0xff00A300;
	public static int col_pavement = 0xffB4C1A6;
	public static int col_patio = 0xffFFD1A3;
	public static int col_road = 0xff000000;
	public static int col_roadH = 0xff010000;
	public static int col_roadV =0; //unused
	public static int col_water = 0xff0094FF;
	
	public static Tile voidTile = new VoidTile (Sprite.voidSprite);
	
	///////////////////////////////////////////////////////////////////////////////////
	
	
	//Constructor
	public Tile (Sprite sprite)
	{
		//this - its value is the reference to the current object
		// it keeps a current copy of it modifable to its subclass
		this.sprite = sprite;
	}

public void render (int x, int y, Screen screen)
{
	
}
//Is it a solid to mobs- YES/NO? Also should be private
 public boolean solid (){
	return false;
  	}

//Need to implement breakable here

}
