package project.dungeon.actors.npcs;

import java.io.IOException;
import java.util.ArrayList;

import project.dungeon.Dungeon;
import project.dungeon.actors.ActorId;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.tiles.MazeTile;
import project.utils.Graph;

public class Strategist extends Npc{
	String sprite;
	
	public Strategist(MapCoordinate currentCoordinates) throws IOException {
		super("strategist", currentCoordinates, ActorId.STRATEGIST);
		this.plan = new ArrayList<MapCoordinate>();
		sprite = "assets/strategist.png";
	}
	
	public Strategist() {
		super("strategist", new MapCoordinate(-1,-1),ActorId.STRATEGIST);
	}
	
	// moves the npc to the nearest player objective
	public MapCoordinate nextMove(Dungeon d) throws IOException {
		MapCoordinate objective = getMostImportantObject(d, getCoordinates());
		// Calculate the shortest path to the player
		Graph g = new Graph(d.getPuzzle());
		plan = g.BFS(getCoordinates(), objective);
		MapCoordinate newCoordinate = plan.get(0);
		return newCoordinate;
	}
	
	/*
	 * @return a coordinate to the most important player objective
	 */
	public MapCoordinate getMostImportantObject(Dungeon d, MapCoordinate curr) throws IOException {
		ArrayList<DungeonObject> objects = findAllObjectives(d);
		ArrayList<MapCoordinate> objectives = new ArrayList<MapCoordinate>();
		// rank the list in order of priority
		Graph g = new Graph(d.getPuzzle());
		// dungeon exit
		objectives.addAll(0, filter("exit",objects));
		// treasure
		objectives.addAll(objectives.size(), filter("tresure",objects));
		// switches
		objectives.addAll(objectives.size(), filter("switches",objects));
		// find the closest of these to the player
		objectives = findClosestPath(g, objectives, d.getPlayerCoordinates());
		if (objectives == null)System.out.print("Error finding strategist path!!!!");
		return objectives.get(0);
	}
	
	private ArrayList<DungeonObject> findAllObjectives(Dungeon d){
		ArrayList<DungeonObject> objects = new ArrayList<DungeonObject>();
		// find a list of all dungeon objects and their coordinates
		Puzzle m = d.getPuzzle();
		for (int x= 0; x<m.getSize(); x++) {
			for (int y = 0; y< m.getSize(); y++) {
				MapCoordinate co = new MapCoordinate(x,y);
				MazeTile t = m.getTile(co);
				objects.addAll(t.getObjects());
			}
		}
		return objects;
	}
	
	private ArrayList<MapCoordinate> findClosestPath(Graph g, ArrayList<MapCoordinate> objectives,
			MapCoordinate player) {
		ArrayList<MapCoordinate> shortestPath = null;
		int shortestDistance = 99999;
		for (MapCoordinate m: objectives) {
			// find the shortest path to a coordinate
			ArrayList<MapCoordinate> path = g.BFS(m,player);
			if (path.size() < shortestDistance) {
				shortestPath = path;
				shortestDistance = path.size();
			}
		}
		return shortestPath;
	}
	
	public ArrayList<MapCoordinate> filter(String objectName, ArrayList<DungeonObject> objects) {
		ArrayList<MapCoordinate> filteredList = new ArrayList<MapCoordinate>();
		return null;
	}

	@Override
	public void update(Dungeon d, Graph g) {
		
	}

	@Override
	public String getSprite() {
		return sprite;
	}
}
