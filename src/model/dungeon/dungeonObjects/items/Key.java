package model.dungeon.dungeonObjects.items;

import java.io.IOException;

import model.dungeon.Dungeon;
import model.dungeon.actors.player.Player;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;
import model.dungeon.dungeonoperations.Add;
/*
 * Unlocks doors, 
 * can be held in player inventory
 * a limit of 1 key can be held at any time
 * each key has only one unique door it can open
 * a key can only be used once
 * 
 */
public class Key extends Item {
	String sprite;
	
	public Key() throws IOException {
		super("Key", ItemCategory.KEY, true, 1);
		sprite = "assets/key.png";
	}

	@Override
	public void interact(DungeonObject o, Dungeon d) {
		// player auto picks up all items on a tile
		// if a player already has a key in inventory,
		if(o.getCategory() == ObjectCategory.PLAYER) {
			Player p = (Player)o;
			// add this object back to the tile
			d.queueAction(new Add(this, p.getCoordinates()));;
			// remove this item from the player inventory
			p.removeItem(this);
		}
	}

	@Override
	public void useWith(DungeonObject o) {
		super.uses--;
	}

	@Override
	public String getSprite() {
		return sprite;
	}
	
}
