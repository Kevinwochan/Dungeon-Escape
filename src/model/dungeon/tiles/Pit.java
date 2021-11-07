package model.dungeon.tiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.dungeon.Dungeon;
import model.dungeon.Dungeon.DungeonState;
import model.dungeon.actors.npcs.Npc;
import model.dungeon.actors.player.Player;
import model.dungeon.actors.statuses.Dead;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;

public class Pit implements MazeTile{
	String sprite;
	
	public Pit() throws IOException {
		 this.sprite = "assets/pit.png";
	}

	@Override
	public String getTileName() {
		return "Pit";
	}

	@Override
	public String toString() {
		return "8";
	}
	
	@Override
	public boolean hasObjects() {
		return false;
	}

	@Override
	public void add(DungeonObject o) {
		if (o.getCategory() ==  ObjectCategory.PLAYER) {
			Player p = (Player) o;
			p.addStatus(new Dead());
		}
		return;
	}

	@Override
	public void remove(DungeonObject o) {
	}

	@Override
	public ArrayList<DungeonObject> getObjects() {
		return null;
	}

	@Override
	public TileCategories getTileCategory() {
		return TileCategories.PIT;
	}

	@Override
	public boolean contains(DungeonObject o) {
		return false;
	}
	
	@Override
	public boolean contains(ObjectCategory category) {
		return false;
	}

	@Override
	public boolean isWalkable(DungeonObject o) {
		return true;
	}

	@Override
	public void interact(DungeonObject o, Dungeon d) {
		ObjectCategory cat = o.getCategory();
		switch (cat) {
			case NPC:
				Npc n = (Npc)o;
				n.addStatus(new Dead());
			case PLAYER:
				d.setStatus(DungeonState.LOST);
				Player p = (Player)o;
				p.addStatus(new Dead());
			default:
		}
	}

	@Override
	public void interact(MazeTile t, Dungeon d) {
	}

	@Override
	public void removeAll(LinkedList<DungeonObject> toDestroy) {
	}

	@Override
	public String getSprite() {
		return sprite;
	}

	@Override
	public String displayObject() {
		return null;
	}
}
