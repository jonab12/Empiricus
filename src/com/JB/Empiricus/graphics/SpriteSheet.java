package com.JB.Empiricus.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// In charge of sprite sheets and handling them
public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int [] pixels;
	
	private Sprite [] sprites;
	 
	public static SpriteSheet blocktiles= new SpriteSheet ("/textures/Blocks/spritesheet.png", 160); // blocks
	public static SpriteSheet playertiles = new SpriteSheet ("/textures/Entities/GamePlayer.png", 384); //entities
	public static SpriteSheet projectile_soldier = new SpriteSheet ("/textures/Entities/Projectiles.png", 48);
	public static SpriteSheet world_objects = new SpriteSheet ("/textures/Blocks/worldobjects.png", 160); // world objects
	
	public static SpriteSheet player = new SpriteSheet ("/textures/Entities/player_sheet.png", 128,96);
    public static SpriteSheet player_down = new SpriteSheet (player, 0, 0, 1 , 3 , 32);
    public static SpriteSheet player_up = new SpriteSheet (player, 1, 0, 1 , 3 , 32);
    public static SpriteSheet player_left = new SpriteSheet (player, 2, 0, 1 , 3 , 32);
    public static SpriteSheet player_right = new SpriteSheet (player, 3, 0, 1 , 3 , 32);
    
    public static SpriteSheet ally = new SpriteSheet ("/textures/Entities/npc_ally.png", 128,96);
    public static SpriteSheet ally_down = new SpriteSheet (ally, 0, 0, 1 , 3 , 32);
    public static SpriteSheet ally_up = new SpriteSheet (ally, 1, 0, 1 , 3 , 32);
    public static SpriteSheet ally_left = new SpriteSheet (ally, 2, 0, 1 , 3 , 32);
    public static SpriteSheet ally_right = new SpriteSheet (ally, 3, 0, 1 , 3 , 32);
    
    public static SpriteSheet enemy = new SpriteSheet ("/textures/Entities/npc_bad.png", 128,96);
    public static SpriteSheet enemy_down = new SpriteSheet (enemy, 0, 0, 1 , 3 , 32);
    public static SpriteSheet enemy_up = new SpriteSheet (enemy, 1, 0, 1 , 3 , 32);
    public static SpriteSheet enemy_left = new SpriteSheet (enemy, 2, 0, 1 , 3 , 32);
    public static SpriteSheet enemy_right = new SpriteSheet (enemy, 3, 0, 1 , 3 , 32);
    
    public static SpriteSheet star_enemy = new SpriteSheet ("/textures/Entities/npc_star.png", 128,96);
    public static SpriteSheet star_enemy_down = new SpriteSheet (star_enemy, 0, 0, 1 , 3 , 32);
    public static SpriteSheet star_enemy_up = new SpriteSheet (star_enemy, 1, 0, 1 , 3 , 32);
    public static SpriteSheet star_enemy_left = new SpriteSheet (star_enemy, 2, 0, 1 , 3 , 32);
    public static SpriteSheet star_enemy_right = new SpriteSheet (star_enemy, 3, 0, 1 , 3 , 32);
	//Constructors
	
	//Sub Sprite Sheets
	public SpriteSheet (SpriteSheet sheet, int x, int y, int width, int height, int spriteSize)
	{
	int xx = x * spriteSize;	
	int yy = y * spriteSize;
	int w = width * spriteSize;
	int h = height * spriteSize;
	
	if (width == height) SIZE = width;
	else SIZE = -1;
	
	WIDTH = w;
	HEIGHT = h;
	
	pixels = new int [w * h];
	
	for (int y0 = 0; y0 < h; y0++){
		int yp = yy + y0;
		for (int x0 = 0; x0 < w;x0++){
			int xp = xx + x0;
			pixels [x0 + y0 * w] = sheet.pixels [xp + yp * sheet.WIDTH];
		}
	}
	int frame = 0;
	sprites = new Sprite [width * height];
	
	for (int ya = 0 ; ya < height; ya++){
		for (int xa = 0 ; xa < width; xa++){
			int [] spritePixels = new int [spriteSize * spriteSize];
			for (int y0 = 0 ; y0 < spriteSize; y0++){
				for (int x0 = 0 ; x0 < spriteSize; x0++){
					spritePixels [x0 + y0 * spriteSize] = pixels [(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					
				}
			}	
			Sprite sprite = new Sprite (spritePixels, spriteSize, spriteSize);
			sprites [frame++] = sprite;
		}
	}
	
	
	}
	
	public SpriteSheet (String path, int size)
	{
		this.path = path;
		this.SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int [SIZE * SIZE];
		load ();
	}
	
	public SpriteSheet (String path, int width, int height)
	{
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int [WIDTH * HEIGHT];
		load ();
	}
	
	public Sprite[] getSprites () {
		return sprites;
	}
	
	private void load () // take and determine dimensions of sprite
	{
		try {
			BufferedImage image = ImageIO.read (SpriteSheet.class.getResource (path));
		    int w = image.getWidth();
		    int h = image.getHeight ();
		    image.getRGB(0, 0, w, h, pixels, 0, w);
		    
		} catch (IOException e) {// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
