package model.dungeon.tiles;
import java.util.ArrayList;
import java.util.LinkedList;

import model.dungeon.Dungeon;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;
//import sun.awt.image.BufferedImageGraphicsConfig;

public interface MazeTile {
	// default MazeTile is floor
	// is the tile current able to be walked on by an object
	public boolean isWalkable(DungeonObject o);
	// does this tile contain objects
	public boolean hasObjects();
	// retries the enum of the tile name
	public TileCategories getTileCategory();
	// what type of tile is this
	public String getTileName();
	// get the image of the tile
	public String getSprite();
	// get the first object (if any) 
	public String displayObject();
	// what the tile looks like on the map
	public String toString();
	// are two tiles equivalent
	public boolean equals(Object o);
	// how does the tile behave with other objects
	public void interact(DungeonObject o, Dungeon d);
	public void interact(MazeTile t, Dungeon d);
	// add an object to a tile
	public void add(DungeonObject o);
	// remove an object from a tile
	public void remove(DungeonObject o);
	// get a list of objects on the tile
	public ArrayList <DungeonObject> getObjects();
	// does the tile contain an object
	public boolean contains(DungeonObject o);
	public boolean contains(ObjectCategory category);
	public void removeAll(LinkedList<DungeonObject> toDestroy);
}