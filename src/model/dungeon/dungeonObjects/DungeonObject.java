package model.dungeon.dungeonObjects;

import model.dungeon.Dungeon;
import model.dungeon.tiles.MazeTile;

/*
 *  A generic class representing objects on the map
 *  contained in MazeTiles
 */

public abstract class DungeonObject {
	// object ID
	private ObjectCategory category; 
	//name of the object
	private String name;
	// if this object is on a tile, can characters still walk on it?
	private boolean blocking;
	String sprite;
	
	public DungeonObject(String name, boolean blocking, ObjectCategory c){
		this.name = name;
		this.setBlocking(blocking);
		this.setCategory(c);
	}	
	
	public String getName() {
		return name;
	}
	
	public boolean isBlocking(DungeonObject o) {
		return blocking;
	}

	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}
	

	/**
	 * @return the category
	 */
	public ObjectCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(ObjectCategory category) {
		this.category = category;
	}
	
	/*
	 * Defines how the object interacts with other objects
	 * and effects the dungeon
	 * @param o, a object to interact with
	 * @param d, the dungeon that is currently in play
	 */
	abstract public void interact(DungeonObject o, Dungeon d);
	/*
	 * Defined how the object interacts with tiles
	 * @param t, a tile to interact with
	 * @param d, the dungeon that is currently in play
	 */
	abstract public void interact(MazeTile t, Dungeon d);
	/*
	 * Objects are equivalent if they are of the same category
	 */
	public boolean equals (DungeonObject o) {
		if (o.getCategory() == this.getCategory()) return true;
		return false;
	}

	/*
	 * gets the image of the object 
	 */
	abstract public String getSprite();
}