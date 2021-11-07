package model.dungeon.tiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.dungeon.Dungeon;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;

public class Wall implements MazeTile{
	private String sprite;

	public Wall() throws IOException {
		sprite = "assets/wall.png";
	}
	
	// does this tile contain objects
	public boolean hasObjects() {
		return false;
	}
	// what type of tile is this
	public String getTileName() {
		return "Wall";
	}
	// what the tile looks like on the map
	public String toString() {
		return "#";
	}

	public String helpText() {
		return "Wall, just a wall";
	}
	
	@Override
	public void add(DungeonObject o) {
	}
	
	@Override
	public void remove(DungeonObject o) {
	}
	@Override
	public ArrayList<DungeonObject> getObjects() {
		return null;
	}
	@Override
	public TileCategories getTileCategory() {
		return TileCategories.WALL;
	}
	@Override
	public boolean contains(DungeonObject o) {
		return false;
	}
	@Override
	public boolean contains(ObjectCategory category) {
		return false;
	}
	@Override
	public boolean isWalkable(DungeonObject o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void interact(DungeonObject o, Dungeon d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void interact(MazeTile t, Dungeon d) {
		// TODO Auto-generated method stub
		
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
		return null;
	}
}
