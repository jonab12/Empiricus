package com.JB.Empiricus.graphics;
 
public class Sprite {
        // For individual sprites (e.g. a monster)
 
        public final int SIZE;
        private int x, y;
        public int[] pixels;
        protected SpriteSheet sheet; // to add sprites on
        private int width, height;
 
        // ///////////////////////////////////SPRITES///////////////////////////////////////
 
        public static Sprite voidSprite = new Sprite(16, 0xFF1B87E0); // 0 for black
 
        // create static sprite objects - to use as reference say e.g.
        // Sprite.concrete
 
        public static Sprite blockR = new Sprite(16, 0, 0, SpriteSheet.blocktiles);
        public static Sprite blockS = new Sprite(16, 0, 1, SpriteSheet.blocktiles);
        public static Sprite blockE = new Sprite(16, 0, 2, SpriteSheet.blocktiles);
        public static Sprite blockW = new Sprite(16, 0, 3, SpriteSheet.blocktiles);
        public static Sprite blockN = new Sprite(16, 0, 4, SpriteSheet.blocktiles);
        public static Sprite blockNE = new Sprite(16, 0, 5, SpriteSheet.blocktiles);
        public static Sprite blockNW = new Sprite(16, 0, 6, SpriteSheet.blocktiles);
        public static Sprite blockSW = new Sprite(16, 0, 7, SpriteSheet.blocktiles);
        public static Sprite blockSE = new Sprite(16, 0, 8, SpriteSheet.blocktiles);
 
        public static Sprite grass = new Sprite(16, 2, 0, SpriteSheet.blocktiles);
 
        public static Sprite pavement = new Sprite(16, 3, 0, SpriteSheet.blocktiles);
        public static Sprite patio = new Sprite(16, 3, 1, SpriteSheet.blocktiles);
 
        public static Sprite road = new Sprite(16, 4, 0, SpriteSheet.blocktiles);
        public static Sprite roadH = new Sprite(16, 4, 1, SpriteSheet.blocktiles);
        public static Sprite roadV = new Sprite(16, 4, 2, SpriteSheet.blocktiles);
 
        public static Sprite water = new Sprite(16, 5, 0, SpriteSheet.blocktiles);
 
        // //////////////////////////////////////////////////////////////////////////////////
 
        // Look at main constructor to understand paraemeters. Basically x *size
        // refers to current location on grid (tile wise)
        // Grid is a Sprite sheet
        // Stationary
        public static Sprite player_forward = new Sprite(32, 0, 0,
                        SpriteSheet.playertiles);
        public static Sprite player_side = new Sprite(32, 1, 0,
                        SpriteSheet.playertiles);
        public static Sprite player_back = new Sprite(32, 2, 0,
                        SpriteSheet.playertiles);
 
        public static Sprite player_forward_ani1 = new Sprite(32, 0, 1,
                        SpriteSheet.playertiles);
        public static Sprite player_forward_ani2 = new Sprite(32, 0, 2,
                        SpriteSheet.playertiles);
 
        public static Sprite player_side_ani1 = new Sprite(32, 1, 1,
                        SpriteSheet.playertiles);
        public static Sprite player_side_ani2 = new Sprite(32, 1, 2,
                        SpriteSheet.playertiles);
 
        public static Sprite player_back_ani1 = new Sprite(32, 2, 1,
                        SpriteSheet.playertiles);
        public static Sprite player_back_ani2 = new Sprite(32, 2, 2,
                        SpriteSheet.playertiles);
 
        // Particles
        public static Sprite particle_normal = new Sprite(1, 0x000000);
 
        // projectile sprites here
        public static Sprite projectile_soldier = new Sprite(16, 0, 0,
                        SpriteSheet.projectile_soldier);
        
        /////////////WORLD OBJECTS//////////
        
        public static Sprite car = new Sprite(64, 0, 0, SpriteSheet.world_objects);
        
        ///////////////////////////////////
 
        public static Sprite ally = new Sprite (32,0,0,SpriteSheet.ally_down);
        
        
        // Constructors
        
        protected Sprite (SpriteSheet sheet, int width, int height){
        	SIZE = (width == height) ?  width :  -1;
        	this.width = width;
        	this.height = height;
        	this.sheet = sheet;
        	
        }
        
        public Sprite(int size, int x, int y, SpriteSheet sheet) {
                SIZE = size;
                this.width = size;
                this.height = size;
                pixels = new int[SIZE * SIZE]; // size of sprite
 
                // Set target location for sprite on sprite sheet
                this.x = x * size;
                this.y = y * size;
                this.sheet = sheet;
                load();
        }
 
        public Sprite(int width, int height, int colour) {
                SIZE = -1;
                this.width = width;
                this.height = height;
                pixels = new int[width * height];
                setColour(colour);
        }
 
        public Sprite(int size, int colour) {
                SIZE = size;
                this.width = size;
                this.height = size;
                pixels = new int[SIZE * SIZE];
                setColour(colour);
        }
 
        public Sprite(int[] pixels, int width, int height) {
        	SIZE = (width == height) ?  width :  -1; 
        	this.width = width;
        	this.height = height;
        	this.pixels = pixels;
		}

		private void setColour(int colour) {
                for (int i = 0; i < width * height; i++) {
                        pixels[i] = colour;
                }
 
        }
 
        public int getWidth() {
                return width;
        }
 
        public int getHeight() {
                return height;
        }
 
        private void load() {
                // access sprite sheets pixels to add
                for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                                // cycle through each pixel and set it to location in sprite
                                // sheet
                                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y)
                                                * sheet.WIDTH];
                        }
                }
        }
 
}