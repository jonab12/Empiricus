package com.JB.Empiricus.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import com.JB.Empiricus.entity.mob.Chaser;
import com.JB.Empiricus.entity.mob.Dummy;
import com.JB.Empiricus.entity.mob.SmartChaser;
import com.JB.Empiricus.level.tile.Tile;

public class SpawnLevel extends Level{

	public SpawnLevel(String path) {
		/*Super means it will take the parameters
		 here and run them in the Superclasses
		 main constructor and execute the code there
		*/
		super(path);
		
		
	}
	
    //To run from level class
	protected void loadLevel (String path)
	{
		try{
			BufferedImage image = ImageIO.read (SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int [w * h];
			
			/*We need to convert image to array of pixels to tell which color every
			pixel is. We are loading levels through images where every pixel in the image
			is a tile within the game that we use.
			*/
			//start x,y,width,height,rgbarray, offset, scansize
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e){
			e.printStackTrace();
		System.out.println ("Exception! Could not load level file!" );
	}
		//Add AI onto level
		add (new Chaser(19,30));
		add (new SmartChaser(19,33));
		
		for (int i = 0; i < 3; i++){
		 add (new Dummy(19,30));
		}
	}
	
    protected void generateLevel (){
		// We need to convert array of pixels to array of tiles

    }
	
	
}
