package model.dungeon.dungeonObjects.items.potions;

import java.io.IOException;

import model.dungeon.actors.player.Player;
import model.dungeon.actors.statuses.Invincibility;
import model.dungeon.dungeonObjects.items.Item;
import model.dungeon.dungeonObjects.items.ItemCategory;

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
