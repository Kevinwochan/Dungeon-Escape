package project.dungeon.puzzles;

import java.io.IOException;
import java.util.LinkedList;

import project.dungeon.actors.npcs.Npc;
import project.dungeon.actors.player.Player;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.ObjectCategory;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.tiles.Exit;
import project.dungeon.tiles.Floor;
import project.dungeon.tiles.MazeTile;
import project.dungeon.tiles.TileCategories;
import project.dungeon.tiles.Wall;

/**
 * A generic Puzzle Class
 * contains a 2D square matrix of MazeTiles 
 * and the width/height of the matrix
 * methods are mostly matrix manipulations
 * @author kevinwochan
 */
public class Puzzle {
	private MazeTile[][] map;
	private int size;
	
	/*
	 * Constructor Class
	 * creates an empty map of just floors surrounded by walls
	 * @param the size of the new map 
	 */
	public Puzzle(int size) throws IOException {
		this.size = size;
		this.map = new MazeTile[size][size];
		this.generateMap();
	}
	
	/*
	 * Getters and Setters
	 */
	public MazeTile[][] getMap() {
		return map;
	}
	
	public String getType() {
		return "EMPTY";
	}
	
	/*
	 * @param map, a 2D square matrix of MazeTiles representing the level
	 */
	public void setMap( MazeTile[][] map ) {
		this.map = map;
	}
	
	/*
	 * Replaces tile at a coordinates co
	 * @param co
	 * @param tile
	 */
	public void setTile(MapCoordinate co, MazeTile tile) {
		map[co.getX()][co.getY()] = tile;
	}
	
	/*
	 * @param co
	 * @return the tile at coordinates co 
	 */
	public MazeTile getTile(MapCoordinate co) {
		if (isValidCoordinate (co)) {
			return map[co.getX()][co.getY()];
		}else {
			return null;
		}
	}	
	/*
	 * @param coOrds, a list of coordinates to convert into tiles
	 * @return a list of valid tiles from a list of coordinates
	 */
	public LinkedList<MazeTile> getTiles(LinkedList<MapCoordinate> coOrds) {
		LinkedList<MazeTile> list = new LinkedList<>();
		for (MapCoordinate co: coOrds) {
			MazeTile tile = getTile(co);
			if (tile != null) list.add(getTile(co));
		}
		return list;
	}	
	/*
	 * @return size the size of the maze
	 */
	public int getSize() {
		return size;
	}
	
	/*
	 * Object Placement
	 */
	public void placeObject(DungeonObject object, MapCoordinate co){
		MazeTile tile = getTile(co);
		tile.add(object);
	}
	/*
	 *  Places an exit somewhere on the map
	 */
	public void placeExit() throws IOException {
		MapCoordinate co = new MapCoordinate(size-1);
		MazeTile t = this.getTile(co);
		Exit e = new Exit();
		while(!(t.getTileCategory() == TileCategories.FLOOR)) {
			co.random(size-1);
			t = this.getTile(co);
		}
		setTile(co,e);
	}
	/*
	 *  Places the player object onto a random tile
	 */
	public void placeRandom(Player player) {
		MapCoordinate co = new MapCoordinate(size-1);
		MazeTile t = this.getTile(co);
		while(!t.isWalkable(player)) {
			co.random(size-1);
			t = this.getTile(co);
		}
		t.add(player);
		player.setCoordinates(co);
		return;
	}	
	public void placeRandom(DungeonObject o) {
		MapCoordinate co = new MapCoordinate(size-1);
		MazeTile t = this.getTile(co);
		while(!t.isWalkable(o)) {
			co.random(size-1);
			t = this.getTile(co);
		}
		t.add(o);
		return;
	}
	/*
	 * Replaces a random floor tile with a new tile
	 * @param newTile the tile to replace a random floor tile
	 */
	public void placeRandom(MazeTile newTile) {
		MapCoordinate co = new MapCoordinate(size-1);
		MazeTile t = this.getTile(co);
		while(!(t.getTileCategory() == TileCategories.FLOOR)) {
			co.random(size-1);
			t = this.getTile(co);
		}
		setTile(co, newTile);
	}	
	/*
	 *  Places the npc onto a random tile
	 */
	public void placeRandom(Npc c) {
		MapCoordinate co = new MapCoordinate(size-1);
		MazeTile t = this.getTile(co);
		while(!t.isWalkable(c)) {
			co.random(size-1);
			t = this.getTile(co);
		}
		// add to tile
		t.add( (DungeonObject) c);
		// update npc
		c.setCoordinates(co);
	}

	/*
	 * Object Movement
	 */
	/*
	 * @param player, player object for them game
	 * @param newCo, the player's newCoordinate
	 */
	public void move(Player player, MapCoordinate newCo) {
		// add the player to the new tile
		MazeTile newTile = getTile(newCo);
		newTile.add(player);
		player.setCoordinates(newCo);
		// remove player from old tile
		if (player.getPrevCoordinates() != null) {
			MazeTile oldTIle = getTile(player.getPrevCoordinates());
			oldTIle.remove(player);
		}
	}	
	public void move(DungeonObject o, MapCoordinate oldCo, MapCoordinate newCo) {
		MazeTile newT = getTile(newCo);
		newT.add(o);
		MazeTile oldT = getTile(oldCo);
		oldT.remove(o);
	}
	
	/*
	 * Add an Object to a tile
	 * Interacts the new object with all objects on the tile
	 * @param o  
	 * @param co
	 */
	public void addObject(DungeonObject o, MapCoordinate co) {
		MazeTile t = getTile(co);
		t.add(o);
	}
	/*
	 * Remove an object from a tile
	 * Interacts the new object with all objects on the tile
	 * @param o  
	 * @param co
	 */
	public void removeObject(DungeonObject o, MapCoordinate co) {
		MazeTile t = getTile(co);
		t.remove(o);
	}
	
	/*
	 * @param npc, the NPC character to move
	 * @param newCo, MazeCoordinate to place the NPC on
	 */
	public void move(Npc npc, MapCoordinate newCo) {
		// validate the move
		if (! isValidCoordinate(newCo)) return;
		MazeTile newTile = getTile(newCo);
		if (! newTile.isWalkable(npc)) return;
		// remove npc from old tile
		MazeTile oldTIle = getTile(npc.getCoordinates());
		// if the tile is not walkable return
		oldTIle.remove(npc);
		// update new tile
		newTile.add(npc);
		// update the npc
		npc.setCoordinates(newCo);
	}
	/*
	 * Boolean Validators 
	 */
	public boolean isAdjacent (MapCoordinate c1, MapCoordinate c2){
		if ( !isValidCoordinate (c1) && !isValidCoordinate (c2)) return false;
		MapCoordinate co = c1.subtract(c2); 
		if (co.getX() == 0) {
			if (co.getY() == -1 || co.getY() == 1) return true;
		}else if (co.getY() == 0) {
			if (co.getX() == -1 || co.getX() == 1) return true;
		}
		return false;
	}
	
	/*
	 * Validates the movement of an object on the map
	 */
	public boolean isWalkable (DungeonObject o , MapCoordinate src, MapCoordinate dest){
		if (! isValidCoordinate (dest)) return false;
		if (! isAdjacent(src, dest)) return false;
		MazeTile tile = getTile(dest);
		if (! tile.isWalkable(o)) return false;
		return true;
	}
	
	private boolean isValidCoordinate(MapCoordinate co) {
		if (co.getX() >= size) {
			 return false;
		}else if (co.getX() < 0) {
			 return false;
		}else if (co.getY() >= size) {
			 return false;
		}else if (co.getY() < 0) {
			return false;
		}
		return true;
	}
	/*
	 * Graphical representation
	 */
	public void display(){
		// start from top left to top right
		for (int y = 0; y<size; y++){
			for (int x = 0; x<size; x++) {
				MapCoordinate co = new MapCoordinate(x,y);
				System.out.print(getTile(co));
			}
		}
	}
	
	public String toString(){
		String s = new String();
		// start from top left to top right
		for (int y = size-1; y>=0; y--){
			s = s.concat(String.valueOf(y));
			for (int x = 0; x<size; x++) {
				MapCoordinate co = new MapCoordinate(x,y);
				MazeTile t = getTile(co);
				s = s.concat(" " + t.toString());
			}
			s = s.concat("\n");
		}		
		s = s.concat("  ");
		for (int x = 0; x<size; x++) {
			s = s.concat(String.valueOf(x)+" ");
		}
		s.concat("\n");
		return s;
	}
	
	/*
	 *  Quick fill methods
	 */
	public void fillWithFloors() {
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				map[i][j] = new Floor();
			}		
		}
	}
	
	public void surroundByWalls() throws IOException {
		for (int i = 0; i<size; i++) {
			//fill the left side
			map[0][i] = new Wall();
			// fill the right side
			map[size-1][i] = new Wall();
			// fill the top side
			map[i][size-1] = new Wall();
			// fill the bottom
			map[i][0] = new Wall();
		}
	}

	public void generateMap() throws IOException {
		fillWithFloors();
		surroundByWalls();
	}

	public Player placePlayer() throws IOException {
		MapCoordinate co = new MapCoordinate(size-1);
		MazeTile t = this.getTile(co);
		Player player = new Player();
		while(!t.isWalkable(player) && !t.contains(ObjectCategory.BOULDER)) {
			co.random(size-1);
			t = this.getTile(co);
		}
		t.add(player);
		player.setCoordinates(co);
		return player;
	}
	

	public boolean hasWon() {
		return false;
	}

	public void placeExit(Exit e) {
		MapCoordinate co = new MapCoordinate(size-1);
		MazeTile t = this.getTile(co);
		while(!(t.getTileCategory() == TileCategories.FLOOR)) {
			co.random(size-1);
			t = this.getTile(co);
		}
		setTile(co,e);
	}

}


