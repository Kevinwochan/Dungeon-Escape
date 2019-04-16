package project.dungeon.tiles;

import java.util.ArrayList;
import java.util.LinkedList;

import project.dungeon.Dungeon;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.ObjectCategory;

public class Floor implements MazeTile{
	protected ArrayList<DungeonObject> objects;
	private String sprite;
	
	/*
	 * @invariant objects =! null
	 */
	public Floor(){
		objects = new ArrayList<>();
	}
	
	/*
	 * Adds an object to a tile and interacts that object with all objects
	 * already on that tile
	 * @param DungeonObject o, an Npc or Item
	 * @pre object.length() >= 0
	 * @post objects.length() >= 0
	 */
	public void add(DungeonObject o) {
		objects.add(0, o);
	}
	/*
	 * @param DungeonObject o, an Npc or Item
	 * @pre object.length() >= 1
	 * @post objects.length() >= 0
	 */
	public void remove(DungeonObject o) {
		objects.remove(o);
	}
	// Does the tile have an object that is blocking movement to here
	public boolean isWalkable(DungeonObject o) {
		for (DungeonObject os : objects) {
			if (os.isBlocking(o)) return false;
		}
		return true;
	}
	
	// does this tile contain objects
	public boolean hasObjects() {
		if (objects.size() > 0){
			return true;
		}
		return false;
	}
	
	// what type of tile is this
	public String getTileName() {
		return "Floor";
	}
	// what the tile looks like on the map
	// dungeonObjects take priority
	public String getImage() {
		if (hasObjects()) {
			return objects.get(0).getSprite();
		}
		return null;
	}
	// what the tile looks like on the map
	// dungeonObjects take priority
	public String getSprite() {
		return sprite;
	}	
	public static void main (String[] argv ) {
		Floor f = new Floor();
		System.out.println(f);
	}
	@Override
	public ArrayList<DungeonObject> getObjects() {
		return objects;
	}
	public DungeonObject getObject(int i) {
		if (i >= objects.size() || i<0) return null;
		return objects.get(i);
	}
	@Override
	public TileCategories getTileCategory() {
		return TileCategories.FLOOR;
	}
	public boolean equals(MazeTile t) {
		if (t.getTileCategory() == this.getTileCategory()) return true;
		return false;
	}
	
	@Override
	public boolean contains(DungeonObject o) {
		if (objects.contains(o)) return true;
		return false;
	}
	
	@Override
	public boolean contains(ObjectCategory cat) {
		for (DungeonObject o: objects) {
			if (o.getCategory() ==  cat) return true;
		}
		return false;
	}
	@Override
	public void interact(DungeonObject o, Dungeon d) {
		
	}
	@Override
	public void interact(MazeTile t, Dungeon d) {
	}
	@Override
	public void removeAll(LinkedList<DungeonObject> objects) {
		objects.removeAll(objects);
	}

	@Override
	public String displayObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override 
	public String toString() {
		return ".";
	}
}
