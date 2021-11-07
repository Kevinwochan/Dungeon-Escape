package model.dungeon.tiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.dungeon.Dungeon;
import model.dungeon.actors.player.Player;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;
import model.dungeon.dungeonObjects.items.Key;
import model.dungeon.dungeonoperations.MovePlayer;

public class Door implements MazeTile{
	private Key key;
	private boolean unlocked;
	private ArrayList<DungeonObject> objects;
	private String sprite;
	private String unlockedSprite;
	
	public Door(Key key) throws IOException{
		this.objects = new ArrayList<>();
		this.key = key;
		this.sprite = "assets/closed-door.png";
		this.unlockedSprite = "assets/unlocked-door.png";
	}
	/*
	 * Adds an object to a tile 
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
	// is the tile current able to be walked on
	public boolean isWalkable(DungeonObject o) {
		// when acting like an open door, the tile acts like a floor
		if (unlocked) {
			for (DungeonObject os : objects) {
				if (os.isBlocking(o)) return false;
			}			
		// when acting as a closed door, the tile can only be walked on
		// if the object is a player with a key
		}else{
			if (o.getCategory() == ObjectCategory.PLAYER) {
				Player p = (Player)o;
				if (p.has(key)) return true;
			}
			return false;
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
		return "Door";
	}
	
	@Override
	public ArrayList<DungeonObject> getObjects() {
		return objects;
	}
	public DungeonObject getObject(int i) {
		if (i >= objects.size() || i<0) return null;
		return objects.get(i);
		
	}
	public TileCategories getTileCategory() {
		return TileCategories.DOOR;
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
		
		if (unlocked) {
			// act like a floor tile
		}else {
			if (o.getCategory() == ObjectCategory.PLAYER) {
				// if the player has the key
				Player p = (Player)o;
				if (p.has(key)) {
					// consume the key and unlock the door
					p.consume(key);
					unlocked = true;
				}
				d.queueAction(new MovePlayer(p, p.getPrevCoordinates()));
			}
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
		if (hasObjects()) return objects.get(0).getSprite();
		return null;
	}	
}
