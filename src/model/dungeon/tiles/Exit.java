package model.dungeon.tiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.dungeon.Dungeon;
import model.dungeon.Dungeon.DungeonState;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;

public class Exit implements MazeTile{
	protected ArrayList<DungeonObject> objects;
	private String sprite;	
	public Exit() throws IOException {
		objects = new ArrayList<>();
		sprite = "assets/exit.png";
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
	
	@Override
	public String getTileName() {
		return "Exit";
	}

	public String helpText() {
		return "Dungeon Exit, hurry and reach this to escape the dungeon!";
	}
	
	@Override
	public String toString() {
		return "@";
	}

	@Override
	public ArrayList<DungeonObject> getObjects() {
		return objects;
	}

	@Override
	public TileCategories getTileCategory() {
		return TileCategories.EXIT;
	}

	@Override
	public boolean contains(DungeonObject o) {
		if (objects.contains(o)) return true;
		return false;	
	}

	@Override
	public boolean contains(ObjectCategory category) {
		for (DungeonObject o: objects) {
			if (o.getCategory() ==  category) return true;
		}
		return false;
	}

	@Override
	public void interact(DungeonObject o, Dungeon d) {
		if (o.getCategory() == ObjectCategory.PLAYER) {
			d.setStatus(DungeonState.WON);
		}
	}

	@Override
	public void interact(MazeTile t, Dungeon d) {
	}
	@Override
	public void removeAll(LinkedList<DungeonObject> objects) {
		objects.removeAll(objects);
	}
	@Override
	public String getSprite() {
		return sprite;
	}
	@Override
	public String displayObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
