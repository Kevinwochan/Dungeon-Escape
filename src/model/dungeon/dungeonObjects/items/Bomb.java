package model.dungeon.dungeonObjects.items;

import java.io.IOException;

import model.dungeon.Dungeon;
import model.dungeon.actors.LitBomb;
import model.dungeon.dungeonoperations.MapCoordinate;
import model.dungeon.tiles.MazeTile;

/*
 * Bomb, when the player uses this item from the inventory
 * a lit bomb is placed on the player's current tile
 * this lit bomb is another class
 */
public class Bomb extends Item {
	String sprite;
	public Bomb() throws IOException {
		super("Bomb", ItemCategory.BOMB, true, 1);
		sprite = "assets/bomb_unlit.png";
	}
	
	// When dropped, this is converted into a lit bomb
	@Override
	public void dropped(Dungeon d, MapCoordinate co, MazeTile tile) throws IOException {
		LitBomb lb = new LitBomb(co);
		d.attach(lb);
		tile.add(lb);
	}

	@Override
	public String getSprite() {
		return sprite;
	}

}
