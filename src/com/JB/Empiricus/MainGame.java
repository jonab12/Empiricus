package com.JB.Empiricus;


/*--------------------------------------------------------------------------------------------------------
 * Currently finished Episode 73 ChernoProject

http://www.reddit.com/r/thecherno/

After you finish enemy entity look at network section at Killer Game Programming. Replace how to make
a game with 'how to make a multiplayer game' and explain all processes

                                              TIPS:
--------------------------------------------------------------------------------------------------------
Hold cntrl and press variable to see where it goes

To find where things get declared  (opposite of cntrl) right click on methods and press reference - workspace 

To find where things get REFERENCED hold cntrl-shift-g

To refract (change variable name in all rerfences) use 'refract'- workspace

Use Cntrl Shift T to search

If code is too small go to Preferences - text editor appearances

To reset window if messed go to Window - Open Perspective - then Java perspective

LEARN how to use debugging in Java. Not by yourself but right next
to laucnh there is a debugger button
--------------------------------------------------------------------------------------------------------
 

--------------------------------------------------------------------------------------------------------
*/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import com.JB.Empiricus.entity.mob.Player;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.graphics.Sprite;
import com.JB.Empiricus.graphics.SpriteSheet;
import com.JB.Empiricus.input.Keyboard;
import com.JB.Empiricus.input.Mouse;
import com.JB.Empiricus.level.Level;
import com.JB.Empiricus.level.SpawnLevel;
import com.JB.Empiricus.level.TileCoordinate;


public class MainGame extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	//Window properties
	private static int width = 300;
	private static int height = width/16 * 9;
	private static int scale = 3;
	public static String title = "Empiricus";
	
	private Thread thread; // A subprocess - independent. Needs runnable to work
	private JFrame frame;
	private boolean running = false;
	
	//Sub-class/affiliant classes variables
	private Screen screen;
	private Keyboard key;
	private Level [] level = new Level [2];
	private Player player;
	
	public static int chosen_level = 0;
	public boolean flag = true;	
	
	private BufferedImage image  = new BufferedImage (width,height,BufferedImage.TYPE_INT_RGB); // rendered view image of game
	
	//Converting image object to an array of idsnt (raster)
	private int [] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	/* A class contains constructors that are invoked to create objects from the class blueprint.
	 *  If there is no constructor a default one is made.
	 *  
	 *  Constructors have one purpose in life: to create an instance of a class and putting it in a object acessible 
	 *  to the current class.
	 *  
	 *  Super- Calls the constructor of the parent class. 
	 * 
	 
	 */
	
	public MainGame () // Main Constructor- CODE IS NOT FIRST EXECUTED HERE.
	{
		//If we use a MainGame object its instructions will be executed from here
		Dimension size = new Dimension (width * scale, height * scale);
		setPreferredSize (size); // you can hover over the method to learn more about it (Canvas)
		
		//Your error for 2 days was that you spawned the character BEFORE you spawned the level. Methods work in a unique way.
		
		//here the screens constructor is called with its variables
		screen = new Screen (width,height); // build the screen, responsible for rendering map and entitities.
	    key = new Keyboard ();
		frame = new JFrame ();
		TileCoordinate playerSpawn = new TileCoordinate (19,25);
		
		level[0] = new SpawnLevel ("/textures/Levels/spawn.png");
		level [1] = new SpawnLevel ("/textures/Levels/base.png");
		player = new Player (playerSpawn.x(), playerSpawn.y(), key); //spawn point
		
		level[chosen_level].add(player);
		
		
		
		
		addKeyListener (key);	
		Mouse mouse = new Mouse ();
		addMouseListener (mouse); // if mouse was clicked
		addMouseMotionListener (mouse); // for position of mouse
	}
	
	public static int getWindowWidth (){
		return width * scale;
	}
	public static int getWindowHeight (){
		return height * scale;
	}
	
	public synchronized void start () {
		//sychnronized makes sure the this is the only thread running and nothing else in the class can interfear.
		
		running = true;
		thread = new Thread (this, "Display");
		thread.start(); // creates a new thread object
		
		/* Here is a good example explaining threads:
		  
		  If you were running an applet and stopped it but the music was still
		  running the thread wasnt properly closed.
		 */
	}
	public synchronized void stop() {
		
		running = false;
		try {
			thread.join (); // closes thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // stops the thread
	}
	
	
	//Since we implemented runnable this is implicitly called once thread is created
	public void run() {
		
		long lastTime = System.nanoTime(); // big number
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/ 60.0;
		double delta = 0;
		int frames = 0; // count frames per second to render
		int updates = 0;
	
		requestFocus (); // from component class that focuses game
		
		while (running) { //	 while running = true
			long now = System.nanoTime ();
			delta += (now - lastTime) /ns;
			
			lastTime = now;
			while (delta >= 1)// HERE IS THE RATE THE GAME UPDATES
			{
				update ();
				updates ++;
				delta --;
			}
			render (); // buffer strategy method that displays images onto screen
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
                frame.setTitle (title + "  |   " + updates + "ups, " + frames + " fps.");
				updates = 0;
                frames = 0;
			}
		}
		stop ();
	}
	

	// 60 updates/second
	public void update ()
	{
	/*
	while (flag == true){
		player.init(level[chosen_level]);	
		flag = false;
		
	}
	
	if ((player.y < 334) && (player.x > 273) && (player.x < 358)) {
		chosen_level = 1;
		flag = true;
	}
	*/
		
	key.update ();
	level[chosen_level].update();
	
	// Make a HUD class and put update method here.
	
	
	
	}
	
	public void render ()// set to 1/60 a sec
	{ // Buffer Strategy - a buffer is a temporary storage place. 
	
		//We are telling Java how to render the screen here simply
		//while the  Open GL is for graphics acceleration and is advanced
		
		BufferStrategy bs = getBufferStrategy ();
		if (bs == null){
			
			createBufferStrategy (3); // triple buffering.
		
		// In Double Buffering while a block of data executes one is 
		// held in reserve (back buffer) waiting to avoid flickering
		   
		return; // get out of this method for first time
		}
		
		screen.clear();
		
		double xScroll =player.getX() - screen.width /2;
		double yScroll =player.getY() - screen.height /2;
		
		level[chosen_level].render ((int)xScroll,(int)yScroll,screen);
		
	    addObjects (); // add external objects e.g. scenery 
		
	
		for (int i = 0; i < pixels.length; i++) {
		pixels [i] = screen.pixels [i];	
		}
		
		Graphics g = bs.getDrawGraphics(); // creates a link from graphics to buffer
		g.drawImage(image, 0,0,getWidth(), getHeight (), null);
		g.setColor(Color.WHITE);
		g.setFont (new Font ("Verdana", 0,20));
		//You can output graphics to screen here
		
	    g.drawString("Bullets | " + player.getAmmo(), 20, 450);
	   g.drawString(player.getX() + " " + player.getY(), 20, 400);
	   
	   //319,334
	   if ((player.getY() < 350) && (player.getY() > 300) && (player.getX() > 300) && (player.getX() < 350))  {
		   g.drawString("Press E to enter room", 320, 450);
	   }
		
		
	    //g.fillRect(Mouse.getX() - 32, Mouse.getY() -32, 64, 64);
		//if (Mouse.getButton() != -1) g.drawString("Button: " + Mouse.getButton() , 80, 80);
	    
		g.dispose();
		bs.show(); // make next available buffer visible
	}
	
	
	private void addObjects() {
		Sprite car = new Sprite (64, 0, 0, SpriteSheet.world_objects); // double declaration
		screen.renderSprite(80, 365, car, true);
		
	}

	public static synchronized void playThemeSong(final String url) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          MainGame.class.getResourceAsStream("/music/themesongs/" + url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
	
	public static void main (String [] args)
	{
		//playThemeSong("Full_Monster_Ending.wav");
		
		MainGame game= new MainGame (); // 
		
		game.frame.setResizable (false);
		game.frame.setTitle(game.title);
		game.frame.add (game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo (null);
		game.frame.setVisible (true);
		
		game.start (); 
	}
	
}
