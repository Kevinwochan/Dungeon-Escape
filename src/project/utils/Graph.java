package project.utils;
import java.io.IOException;
//Java program to print BFS traversal from a given source vertex.
//BFS(int s) traverses vertices reachable from s.
//https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import project.dungeon.actors.npcs.Hound;
import project.dungeon.actors.npcs.Npc;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.puzzles.Puzzle;

//This class represents a directed graph using adjacency list
//representation
public class Graph{
	private int V;	 // No. of vertices
	private Puzzle maze;
	private int mapSize;	 // gridSize
	private ArrayList<Integer> adj[]; //Adjacency Lists

	// Constructor
	public Graph(Puzzle map) throws IOException {
		this.maze = map;
		mapSize = map.getSize();
		V = mapSize * mapSize;
		adj = new ArrayList[V];
		 
		// fill adjacency list
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList();
		}
		connectAdjacentTiles();
	}
	
	// connects all tiles that are adjacnent (up down, left rigjt)
	void connectAdjacentTiles() throws IOException {
		int vertex = 0;
		for (int x = 0; x < mapSize; x++) {
			for (int y = 0; y < mapSize; y++) {
				addAdjacentEdge(vertex++);
			}
		}
	}
	
	// Function to add an edge into the graph
	void addAdjacentEdge(int vertex) throws IOException {
		 MapCoordinate co = vertexToCoordinate(vertex);
		 Npc n = new Hound(null);
		 // tile to the left
		 MapCoordinate left = co.add(new MapCoordinate(-1,0));
		 if (maze.isWalkable(n,co,left)) {
			 addEdge(vertex, vertex - mapSize);
		 }
		 // tile to the right
		 MapCoordinate right = co.add(new MapCoordinate(1,0));
		 if (maze.isWalkable(n,co,right)) {
			 addEdge(vertex, vertex + mapSize);
		 }
		 // tile above
		 MapCoordinate above = co.add(new MapCoordinate(0,1));
		 if (maze.isWalkable(n,co,above)) {
			 addEdge(vertex, vertex + 1);
		 }
		 // tile below
		 MapCoordinate below = co.add(new MapCoordinate(0,-1));
		 if (maze.isWalkable(n,co,below)) {
			 addEdge(vertex, vertex - 1);
		 }
	 }

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// BFS traversal for MST with early exit when dst node found
	// i.e finds the shortest path
	private ArrayList<Integer> BFS(int s, int d) {
		 ArrayList<Integer> MST = new ArrayList<>();
		 // Mark all the vertices as not visited(By default
		 // set as false)
		 boolean visited[] = new boolean[V];
		 // Create a queue for BFS
		 LinkedList<Integer> queue = new LinkedList<Integer>();
		 // Mark the current node as visited and enqueue it
		 visited[s] = true;
		 queue.add(s);
		 while (queue.size() != 0) {
			 // Dequeue a vertex from queue
			 s = queue.poll();
			 MST.add(s);
			 // early exit
			 if (s == d) return MST;
			 // Get all adjacent vertices of the dequeued vertex s
			 // If a adjacent has not been visited, then mark it
			 // visited and enqueue it
			 Iterator<Integer> i = adj[s].listIterator();
			 while (i.hasNext()) {
				 int n = i.next();
				 if (!visited[n]) {
					 visited[n] = true;
					 queue.add(n);
				 }
			 }
		}
		return MST;
	 }

	//DFS traversal for shortest path
/*	
	private ArrayList<Integer> DFS(int s, int d) {
		 ArrayList<Integer> shortestPath = new ArrayList<>();
		 int[] preD = new int[V]; 
		 int min = 999, nextNode = 0; // min holds the minimum value, nextNode holds the value for the next node. 
		 int[] distance = new int[V]; // the distance matrix 
		 ArrayList<Integer>[] matrix = adj; // the adjacency matrix 
		 int[] visited = new int[V]; // the visited array 
		   
		 for (int i = 0; i < distance.length; i++){ 
			 visited[i] = 0; //initialize visited array to zeros 
			 preD[i] = 0; 
   
			 for (int j = 0; j < distance.length; j++){ 
				 if (matrix[i][j] == 0) 
					 matrix[i][j] = 999; // make the zeros as 999 
			 } 
		 } 
		   
		 distance = matrix[0]; //initialize the distance array 
		 visited[s] = 1; //set the source node as visited 
		 distance[s] = 0; //set the distance from source to source to zero which is the starting point 
		   
		 for (int counter = 0; counter < V; counter++) { 
			 min = 999; 
			 for (int i = 0; i < 5; i++) { 
				 if (min > distance[i] && visited[i]!=1) { 
					 min = distance[i]; 
					 nextNode = i; 
				 } 
			 } 
   
			 visited[nextNode] = 1; 
			 for (int i = 0; i < V; i++) { 
				 if (visited[i]!=1) { 
					 if (min+matrix[nextNode][i] < distance[i]) { 
						 distance[i] = min+matrix[nextNode][i]; 
						 preD[i] = nextNode; 
					 } 
				 } 
			 } 
		 } 
		   
		 for(int i = 0; i < V; i++) System.out.print("|" + distance[i]); 
		 
		 System.out.println("|"); 
		 int j; 
		 for (int i = 0; i < V; i++) { 
			 if (i != 0) { 
				 System.out.print("Path = " + i); 
				 shortestPath.add(i);
				 j = i; 
				 do { 
					 j = preD[j]; 
					 System.out.print(" <- " + j); 
				 } 
				 while(j != 0); 
			 } 
			 System.out.println(); 
		 } 
		return shortestPath;
	}
*/
	// prints the graph
	public void displayGraph() {
		 for(int v = 0; v < V; v++) {
	            if (adj[v].size()<1) continue;
				MapCoordinate co = vertexToCoordinate(v);
	            System.out.println("Adjacency list of vertex "+ co);
	            System.out.print("head");
	            for(Integer pCrawl: adj[v]){
					MapCoordinate o = vertexToCoordinate(pCrawl);
	                System.out.print(" -> "+o);
	            }
	            System.out.println("\n");
	        }		 
	 }
	 
	// Original constructor 
	public Graph(int v) {
	        this.V = v;
	        adj = new ArrayList[V];
	        for (int i = 0; i < V; ++i) adj[i] = new ArrayList();
	}
	
	/*
	 * @param a coordinate
	 * @return a vertex number 
	 * 
	 */
	public int coordinateToVertex(MapCoordinate c) {
		return c.getX() * mapSize + c.getY();	
	}
	
	/*
	 * @param a vertex number from the graph
	 * @return a MazeCoordinate
	 */
	public MapCoordinate vertexToCoordinate(int v) {
		int y = v%mapSize;
		int x = (v-y)/mapSize;
		return new MapCoordinate (x, y);
	}	
	
	/*
	 * @param currentCo, the starting coordinate 
	 * @param destinationCo, the destination we want the shortest path to
	 * @return the shortest list of MazeCoordinates reach the destination 
	 */
	public ArrayList<MapCoordinate> BFS(MapCoordinate currentCo, MapCoordinate destinationCo) {
		ArrayList<MapCoordinate> shortestPath = new ArrayList<MapCoordinate>();
		int src = coordinateToVertex(currentCo);
		int dest = coordinateToVertex(destinationCo);
		for (int c : BFS(src, dest) ) shortestPath.add(vertexToCoordinate(c));
		return shortestPath;
	}

}