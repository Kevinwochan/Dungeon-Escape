package project.dungeon.dungeonoperations;
import project.dungeon.Dungeon;
import project.dungeon.dungeonObjects.DungeonObject;

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