package com.JB.Empiricus.level;

import com.JB.Empiricus.util.Vector2i;

/* A* Pathfinding algorithm. Consider the tiles as blocks for the grid.
 * We use nodes and parent nodes to trace back paths for the entities to follow
 * 
 */

public class Node {

	
	public Vector2i tile; // = public int x, y
	public Node parent;
	public double fCost, gCost, hCost;
	
	/**CONSTRUCTOR
	 * 
	 * @param tile - x and y coordinates of tile
	 * @param parent - node that allows us to trace back the path
	 * @param gCost - Dijkstra's Cost (targets closest tile or node that leads properly to goal, scans walls)
	 * @param hCost -Heuristic Cost (greedy best line path - ignores walls, targets tile or node closest to goal)
	 * 
	 * A*, g(n) represents the exact cost of the path from the starting point to any vertex n,
	 * and h(n) represents the heuristic estimated cost from vertex n to the goal.
	 * 
	 * fCost = gCost + hCost
	 * 
	 * http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
	 */
	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
		
	}

	
	
	
}
