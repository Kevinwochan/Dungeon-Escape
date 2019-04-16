package project.dungeon.dungeonoperations;
import project.dungeon.Dungeon;
import project.dungeon.dungeonObjects.DungeonObject;

/*
* Remove an object from a coordinate
*/
public class Remove extends DungeonOperation{
	private DungeonObject object;
	private MapCoordinate co;
	public Remove (DungeonObject o, MapCoordinate d) {
		this.object = o;
		this.co = d;
	}
	public void act(Dungeon dungeon) {
		dungeon.getPuzzle().removeObject(object, co);
	}
}