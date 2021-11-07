package model.dungeon.dungeonObjects.items.weapons;
import java.io.IOException;

import model.dungeon.actors.npcs.Npc;
import model.dungeon.actors.statuses.Dead;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;
import model.dungeon.dungeonObjects.items.Item;
import model.dungeon.dungeonObjects.items.ItemCategory;

public class Arrow extends Item{
	String sprite;
	
	public Arrow() throws IOException {
		super("Sword", ItemCategory.SWORD, false, 1);
		sprite = "assets/arrow.png";
	}
	
	public void useWith (DungeonObject o) {
		if (o.getCategory() == ObjectCategory.NPC) {
			Npc n = (Npc)o;
			n.addStatus(new Dead());
		}
	}

	@Override
	public String getSprite() {
		return sprite;
	}
}
