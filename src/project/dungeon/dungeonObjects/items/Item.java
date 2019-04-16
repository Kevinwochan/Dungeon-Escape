package project.dungeon.dungeonObjects.items;

import java.io.IOException;

import project.dungeon.Dungeon;
import project.dungeon.actors.player.Player;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.ObjectCategory;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.tiles.MazeTile;

public abstract class Item extends DungeonObject{
	int uses;
	boolean stackable;
	ItemCategory itemId;
	
	public Item(String name, ItemCategory c, boolean stackable, int uses) {
		super(name, false, ObjectCategory.ITEM);
		this.uses = uses;
		this.itemId = c;
		this.stackable = stackable;
	}
	/*
	 * Defined how the object interacts with a tile
	 * when added to one
	 */
	public void interact(MazeTile t, Dungeon d) {}
	/*
	 * Defines how the object interacts with other objects
	 * when added to a tile 
	 */
	public void interact(DungeonObject o, Dungeon d) {}
	
	/*
	 * @return uses, the amount of uses left on the item
	 */
	public int getUses() {
		return uses;
	}

	/*
	 * @return itemid, the unique enum for a type of item
	 */
	public ItemCategory getItemId() {
		return itemId;
	}
	/*
	 *  Defines item behaviour when used form inventory when
	 *  used with another object
	 *  default: nothing interesting
	 */
	public void useWith (DungeonObject o) { }
	
	@Override
	public String toString() {
		return super.getName() + " ("+uses+")";
	}
	/*
	 * Defines what happens when a player consumes an item
	 * from inventory
	 * default: nothing interesting;
	 */
	public void consume(Player player) { }
	/*
	 * When an item is dropped onto a tile, the item is
	 * added to a tile, the item can override any special behaviour
	 */
	public void dropped(Dungeon dungeon, MapCoordinate currCo, MazeTile tile) throws IOException {
		tile.add(this);
	}
}
