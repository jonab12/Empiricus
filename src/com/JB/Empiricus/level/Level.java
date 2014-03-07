package com.JB.Empiricus.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.JB.Empiricus.entity.Entity;
import com.JB.Empiricus.entity.mob.Player;
import com.JB.Empiricus.entity.particle.Particle;
import com.JB.Empiricus.entity.projectile.Projectile;
import com.JB.Empiricus.entity.spawner.Spawner;
import com.JB.Empiricus.graphics.Screen;
import com.JB.Empiricus.level.tile.Tile;
import com.JB.Empiricus.util.Vector2i;

//Superclass primary level class. We want to reduce redundancy 
//so all the necessary base information for subclass levels 
//(e.g. - night level) use this template


public class Level { 

	//protected means it can be seen by package
	protected int width, height;
	//Contains IDs within diffrent indexes - diffrent objects
	protected int [] tilesInt;
	
	protected int [] tiles; // contains all level tiles on that level
	
	public static Level spawn = new SpawnLevel ("/textures/Levels/spawn.png");
	public static Level base = new SpawnLevel ("/textures/Levels/base.png");
	
	/*
	 An ordered collection (also known as a sequence). 
	 The user of this interface has precise control over where in the list each element is inserted. 
	 The user can access elements by their integer index (position in the list), and search for 
	 elements in the list.

     Unlike sets, lists typically allow duplicate elements. 
     More formally, lists typically allow pairs of elements e1 and e2 such that e1.equals(e2), 
     and they typically allow multiple null elements if they allow null elements at all.
	 */
	
	
	private List <Entity> entities = new ArrayList <Entity> ();
	private List <Projectile> projectiles = new ArrayList <Projectile> ();
	private List <Particle> particles = new ArrayList <Particle> ();
	private List <Player> players = new ArrayList<Player>();

	private Comparator<Node> nodeSorter = new Comparator<Node>(){

		//We want to find the shortest path available that we have
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return +1; // move it up the index of the list
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
		
	};
	//Constructor
	public Level (int width, int height)
	{
		this.width = width;
		this.height = height;
		tilesInt = new int [width * height];
		generateLevel ();
		
	}
	//Second Constructor for loading levels through files
	public Level (String path) 
	{
		
		loadLevel (path);
		generateLevel ();
		
		
	}

	protected void generateLevel() {
		
	}
	
	//Take image files
	protected void loadLevel (String path)
	{
		
	}
	
	
    public List<Player> getPlayers(){
		return players;
	}
    
    public Player getPlayerAt (int index){
    	return players.get(index);
    }
	
    public Player getClientPlayer() {
    	return players.get(0); // player on client side is always 0
    }
    
    public List<Node> findPath (Vector2i start, Vector2i goal){
		List<Node> openList = new ArrayList <Node> ();
		List<Node> closedList = new ArrayList <Node> ();
		Node current = new Node(start, null,0,getDistance(start,goal));
		
		openList.add(current);
		while(openList.size() > 0){ //continue searching for a path
			Collections.sort(openList, nodeSorter); // Sorts the specified list according to the order induced by the specified comparator.
			current = openList.get(0);
			if (current.tile.equals(goal)){
				List <Node> path = new ArrayList <Node>();
				while (current.parent != null){
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			//We dont want to look at the tile again
			closedList.add(current);
			
			//Check adjacent tiles (8) except walls, and last location
			for (int i = 0; i < 9; i++){
			 if (i == 4) continue; //current middle index
			 int x = current.tile.getX();
			 int y = current.tile.getY();
			 int xi = (i % 3) - 1; // |-1, 0, 1,|-1, null, 1,|-1, 0, 1| 
			 int yi = (i / 3) - 1; // |-1,-1,-1,|0, null, 0, |1, 1, 1 |
			 
			 Tile at = getTile (x + xi, y + yi);
             if (at == null) continue;
             if (at.solid()) continue;
             
             Vector2i a = new Vector2i (x + xi, y + yi);
             double gCost = current.gCost + (getDistance(current.tile,a) == 1 ? 1 : 0.95);
             double hCost = getDistance(a, goal);
             
             Node node = new Node (a,current, gCost, hCost);
             if ((vecInList (closedList,a) && gCost >= current.gCost)) continue;
             if (!vecInList(openList,a) || (gCost < current.gCost)) openList.add(node);
             
			 
			}
		}
		closedList.clear();
		return null; // no path possible
    	
    }
    
    private double getDistance (Vector2i tile, Vector2i goal){
    	double dx = tile.getX() - goal.getX();
    	double dy = tile.getY() - goal.getY();
    	return Math.sqrt(dx * dx + dy * dy); 

    }
    
    private boolean vecInList (List <Node> list, Vector2i vector){
    	for (Node n : list){
    		if (n.tile.equals(vector)) return true;
    	}
    	return false;
    }
    
    //Locate all entities within a certain area
    public List<Entity> getEntities (Entity e, int radius) {
    	List <Entity> result = new ArrayList <Entity>();
    	int ex = (int) e.getX();
    	int ey = (int) e.getY();
    	
    	for (int i = 0; i < entities.size(); i++) {
    		Entity entity = entities.get(i); 
    		int x = (int)entity.getX();
    		int y = (int)entity.getY();
    		int dx = Math.abs(x - ex);
    		int dy = Math.abs(y - ey);
    		double distance = Math.sqrt((dx*dx) + (dy*dy));
    		if (distance <= radius) result.add(entity);
    	}
    	return result;
    }
    
    public List<Player> getPlayers (Entity e, int radius) {
    	
    	List<Player> result = new ArrayList <Player>();
    	int ex = (int)e.getX();
    	int ey = (int)e.getY();
    	for (int i = 0; i < players.size();i++){
    		Player player = players.get(i);
    			int x = (int)player.getX();
        		int y = (int)player.getY();
        		int dx = Math.abs(x - ex);
        		int dy = Math.abs(y - ey);
        		double distance = Math.sqrt((dx*dx) + (dy*dy));
        		if (distance <= radius) result.add(player);
    	}
    	return result;
    }
    
	public void update () //For AI - bots and other entities they will have to be mentioned here to be put in the level.	
	{
		for (int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++){
			players.get(i).update();
		}
		remove();
	}
	
	private void remove (){
		
		for (int i = 0; i < entities.size(); i++){
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++){
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++){
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++){
			if (players.get(i).isRemoved()) players.remove(i);
		}
	}
	
	public List<Projectile> getProjectiles (){
		return projectiles;
	}
	
	// If you want time within every level
	private void time () 
	{
		
	}
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		// four corner collision algorithm
		for (int c = 0; c < 4; c++) {
			int xt = (x + c % 2 * size - xOffset) / 16;
			int yt = (y + c / 2 * size - yOffset) / 16;
			if (getTile (xt,yt).solid()) solid = true;
		}
		return solid;
	}
	
    public void render (int xScroll, int yScroll, Screen screen) // player is xScroll - where he is
    {
    screen.setOffset (xScroll, yScroll);	
   //Corner Pins   
    int x0 = xScroll >> 4; // For vertical asymphote (left side)
    // >> is shifted right, x0 is xScroll /16 (same thing) - so we can find pixel precision
    //+16 is for procedural rendering. So a tile is always put on the sides
    int x1 = (xScroll + screen.width + 16) >> 4; // For vertical asymphote (right side)
    int y0 = yScroll >> 4;
    int y1 = (yScroll + screen.height + 16) >> 4;
    
    for (int y = y0; y < y1; y++){//y = top of screen until bottom
    for (int x = x0; x < x1; x++){
    	 getTile (x,y).render(x, y, screen);  //We set tiles when player moves away from screen 
        //Calls render method from Tile.class because getTile is a constructor of tile
    	
       }
      }
    for (int i = 0; i < entities.size(); i++){
		entities.get(i).render(screen);
	}
    for (int i = 0; i < projectiles.size(); i++){
		projectiles.get(i).render(screen);
	}
    for (int i = 0; i < particles.size(); i++){
		particles.get(i).render(screen);
	}
    for (int i = 0; i < players.size(); i++){
		players.get(i).render(screen);
	}
    
    }

    public void add (Entity e){
    	e.init(this);
    	if (e instanceof Particle) {
    		particles.add((Particle) e);
    	}
    	else if (e instanceof Projectile){
    		projectiles.add((Projectile) e);
    	}
    	else if (e instanceof Player) {
    	players.add((Player) e);
    	}
    	else {
    		entities.add (e);	
    	}
    	
    }
    

    //See "Textures Information.txt" for list of colors taken from level picture
    
    public Tile getTile (int x, int y) // gets tile and returns tile past boundaries
    {
    if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
   
    if (tiles [x + y * width] == Tile.col_blockR) return Tile.blockR;
    if (tiles [x + y * width] == Tile.col_blockS) return Tile.blockS;
    if (tiles [x + y * width] == Tile.col_blockE) return Tile.blockE;
    if (tiles [x + y * width] ==Tile.col_blockW) return Tile.blockW;
    if (tiles [x + y * width] == Tile.col_blockN) return Tile.blockN;
    if (tiles [x + y * width] == Tile.col_blockNE) return Tile.blockNE;
    if (tiles [x + y * width] == Tile.col_blockNW) return Tile.blockNW;
    if (tiles [x + y * width] == Tile.col_blockSW) return Tile.blockSW;
    if (tiles [x + y * width] == Tile.col_blockSE) return Tile.blockSE;
    if (tiles [x + y * width] == Tile.col_grass) return Tile.grass;
    if (tiles [x + y * width] == Tile.col_pavement) return Tile.pavement;
    if (tiles [x + y * width] == Tile.col_patio) return Tile.patio;
    if (tiles [x + y * width] == Tile.col_road) return Tile.road;
    if (tiles [x + y * width] == Tile.col_roadH) return Tile.roadH;
    if (tiles [x + y * width] == Tile.col_roadV) return Tile.roadV;
    if (tiles [x + y * width] == Tile.col_water) return Tile.water;
   
	return Tile.voidTile; 
	//Note there is a random generator that generates tiles but the only tile there is now is concrete
    
    }
    
    
}
