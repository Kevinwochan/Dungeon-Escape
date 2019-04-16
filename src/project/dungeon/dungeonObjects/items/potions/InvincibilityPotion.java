package project.dungeon.dungeonObjects.items.potions;

import java.io.IOException;

import project.dungeon.actors.player.Player;
import project.dungeon.actors.statuses.Invincibility;
import project.dungeon.dungeonObjects.items.Item;
import project.dungeon.dungeonObjects.items.ItemCategory;

public class InvincibilityPotion extends Item {
	String sprite;
	
	public InvincibilityPotion() throws IOException {
		super("Hover Potion", ItemCategory.HOVERPOTION, false, 1);
		sprite = "assets/invincibility-potion.png";
	}

	public void consume(Player player) { 
		player.addStatus(new Invincibility());
	}

	@Override
	public String getSprite() {
		return sprite;
	}

}
