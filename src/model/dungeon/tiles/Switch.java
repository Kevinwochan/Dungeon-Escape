package model.dungeon.tiles;

import java.io.IOException;

import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;

/*
 * A MazeTile, if all switches are active a dungeon is complete
 * switches are activated by pushing boulders onto these tiles
 */
public class Switch extends Floor{
	private String sprite;
	private boolean active;
	
	public Switch() throws IOException{
		super();
		sprite = "assets/switch.png";

	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	// what type of tile is this
	@Override
	public String getTileName() {
		return "Switch";
	}
	
	@Override
	public void add(DungeonObject o) {
		super.add(o);
		if (o.getCategory() == ObjectCategory.BOULDER) {
			setActive(true);
		}
	}

	@Override
	public void remove(DungeonObject o) {
		super.remove(o);
		if (o.getCategory() == ObjectCategory.BOULDER) {
			setActive(false);
		}
	}
	
	@Override
	public String getSprite() {
		return sprite;
	}
}
