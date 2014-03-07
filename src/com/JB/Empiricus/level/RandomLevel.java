package com.JB.Empiricus.level;

import java.util.Random;

public class RandomLevel extends Level{

	private static final Random random = new Random ();
	
	public RandomLevel(int width, int height) {
		/*Super means it will take the parameters
		 here and run them in the Superclasses
		 main constructor and execute the code there
		*/
		super(width, height);
	}

	//Over-rides superclasses same method
	protected void generateLevel ()
	{
	for (int y = 0; y < height; y++){
		for (int x = 0; x < width; x++){
			tilesInt [x + y * width] = random.nextInt (4);
		    
		}
	}
	}
	
}
