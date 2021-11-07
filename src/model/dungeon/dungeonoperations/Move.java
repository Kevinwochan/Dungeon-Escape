package model.dungeon.dungeonoperations;

import model.dungeon.Dungeon;
import model.dungeon.dungeonObjects.DungeonObject;

public class Move extends DungeonOperation{
	private DungeonObject object;
	private MapCoordinate oldCo;
	private MapCoordinate newCo;
	
	public Move(DungeonObject object, MapCoordinate oldCo, MapCoordinate co) {
		this.object = object;
		this.oldCo = oldCo;
		this.newCo = co;
	}
	public void act(Dungeon dungeon) {
		dungeon.getPuzzle().move(object, oldCo, newCo);
	}
}
