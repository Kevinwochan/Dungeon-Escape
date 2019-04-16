package project.dungeon.actors.player;

import java.io.IOException;
import java.util.ArrayList;

import project.dungeon.Dungeon;
import project.dungeon.Dungeon.DungeonState;
import project.dungeon.actors.statuses.Status;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.ObjectCategory;
import project.dungeon.dungeonObjects.items.Item;
import project.dungeon.dungeonObjects.items.ItemCategory;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.dungeonoperations.Remove;
import project.dungeon.puzzles.TreasureHunt;
import project.dungeon.tiles.MazeTile;
import project.dungeon.tiles.TileCategories;

public class Player extends DungeonObject{
	String sprite;
	ArrayList<Item> inventory;
	ArrayList<Status> statuses;
	MapCoordinate currCo;
	MapCoordinate prevCo;

	public Player() throws IOException {
		super("Player", false, ObjectCategory.PLAYER);
		inventory = new ArrayList<Item>();
		statuses = new ArrayList<Status>();
		currCo = new MapCoordinate(-1,-1);
		prevCo = new MapCoordinate(-1,-1);
		sprite = "assets/player.png";
	}

	public MapCoordinate getCoordinates() {
		return currCo;
	}

	public MapCoordinate getPrevCoordinates() {
		return prevCo;
	}

	public ArrayList<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(ArrayList<Status> statuses) {
		this.statuses = statuses;
	}

	public void setCoordinates(MapCoordinate coordinates) {
		prevCo = currCo;
		currCo = coordinates;
	}

	@Override
	public String toString () {
		return currCo.toString() + "\n" + inventory.toString();
	}

	public void addStatus(Status status) {
		statuses.add(status);
	}

	public void removeStatus(Status status) {
		statuses.remove(status);
	}
	@Override
	public void interact(DungeonObject o, Dungeon d) {
		 if (o.getCategory() == ObjectCategory.NPC) {
			d.setStatus(DungeonState.LOST);
		// picks up all items
		}else if(o.getCategory() == ObjectCategory.ITEM) {
			Item item = (Item) o;
			inventory.add(item);
			if (d.getPuzzle() instanceof TreasureHunt) {
				TreasureHunt th = (TreasureHunt) d.getPuzzle();
				th.collectedTreasure();
			}
			d.queueAction(new Remove(o, currCo));
		}
		return;
	}
	
	public void interact(MazeTile t, Dungeon d) {
		// interact with the tile
		if (t.getTileCategory() == TileCategories.EXIT) {
			d.setStatus(DungeonState.WON);
		}
	}
	
	public void move(Dungeon dungeon, MapCoordinate newCo) {
		// move the player from one tile to the other
		dungeon.getPuzzle().move(this, newCo);	
		// interact with the objects
		MazeTile tile = dungeon.getPuzzle().getTile(newCo);
		tile.interact(this, dungeon);
		interact(tile, dungeon);
		for (DungeonObject o: tile.getObjects()) {
			o.interact(this, dungeon);
			this.interact(o, dungeon);
		}
	    System.out.println("dungeon updated: " + dungeon.getPlayerCoordinates().getX() + " " + dungeon.getPlayerCoordinates().getY());
	}

	/*
	 * Inventory handlers
	 */
	// handles item category searches
	public boolean has(ItemCategory cat) {
		for (Item i: inventory) {
			if (i.getItemId() == cat) return true;
		}
		return false;
	}

	public boolean has(Item item) {
		if(inventory.contains(item)) return true;
		return false;
	}

	/*
	 * Use an item from inventory with an object
	 */
	public void useWith(Item item, DungeonObject o) {
		item.useWith(o);
		if (item.getUses() == 0) inventory.remove(item);
	}

	/*
	 * Drop an object onto a tile
	 */
	public void drop (Dungeon dungeon, int inventoryIndex) throws IOException {
		MazeTile tile = dungeon.getPuzzle().getTile(currCo);
		// objects cannot be placed on doors
		if (tile.getTileCategory() == TileCategories.DOOR) return;
		Item item = getItemAt(inventoryIndex);
		item.dropped(dungeon, currCo, tile);
	}

	/*
	 * Removes an item from the game
	 */
	public void consume(Item item) {
		// apply any status effects
		item.consume(this);
		// remove from game
		inventory.remove(item);
	}

	@Override
	public String getSprite() {
		return sprite;
	}
	
	public void pickUp(Item item) {
		inventory.add(item);
	}

	public ArrayList <Item> getItems() {
		return inventory;
	}

	/*
	 * Inventory handlers
	 */
	public void removeItem(ItemCategory cat) {
		if (! hasItem(cat)) return;
		// create a reference to the item to deal with comodification of list
		Item toRemove = null;
		for (Item i: inventory) {
			if (i.getItemId() == cat) {
				toRemove = i;
				break;
			}
		}
		inventory.remove(toRemove);
	}
	/*
	 * does the player inventory contain an item
	 */
	boolean hasItem(ItemCategory id) {
		for (Item i: getItems()) {
			if (i.getItemId() == id) return true;
		}
		return false;
	}

	public Item getItem(ItemCategory itemId) {
		for (Item i: getItems()) {
			if (i.getItemId() == itemId) return i;
		}
		return null;
	}

	public void removeItem(Item item) {
		inventory.remove(item);
	}

	public Item getItem(Item item) {
		for (Item i: getItems()) {
			if (i.equals(item)) return i;
		}
		return null;
	}

	public Item getItemAt(int inventoryIndex) {
		return inventory.get(inventoryIndex);
	}

}
