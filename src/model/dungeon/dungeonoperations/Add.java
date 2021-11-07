package model.dungeon.dungeonoperations;
import model.dungeon.Dungeon;
import model.dungeon.dungeonObjects.DungeonObject;

/*
* Add an object to a coordinate
*/
public class Add extends DungeonOperation{
	private DungeonObject object;
	private MapCoordinate co;
	public Add (DungeonObject o, MapCoordinate d) {
		this.object = o;
		this.co = d;
	}
	public void act(Dungeon dungeon) {
		dungeon.getPuzzle().addObject(object, co);
	}
}