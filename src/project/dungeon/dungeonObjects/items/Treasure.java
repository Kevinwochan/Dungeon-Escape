package project.dungeon.dungeonObjects.items;

import java.io.IOException;

/*
 * Treasure, collect all treasures to win the puzzle
 */
public class Treasure extends Item {
	String sprite;
	
	public Treasure() throws IOException {
		super("Treasure",ItemCategory.TREASURE, true, 1);
		sprite  = "assets/gold.png";
	}
	@Override
	public String getSprite() {
		return sprite;
	}
	
}