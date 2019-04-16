package project.dungeon.dungeonObjects.items.weapons;
import java.io.IOException;

import project.dungeon.actors.npcs.Npc;
import project.dungeon.actors.statuses.Dead;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.ObjectCategory;
import project.dungeon.dungeonObjects.items.Item;
import project.dungeon.dungeonObjects.items.ItemCategory;

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
