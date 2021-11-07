package model.dungeon.actors;

import model.dungeon.Dungeon;
import model.dungeon.dungeonObjects.DungeonObject;
import model.dungeon.dungeonObjects.ObjectCategory;
import model.utils.Graph;

/*
 * Generic class for objects that require a turn
 */
public abstract class Actor extends DungeonObject{
	ActorId id;
	
	public Actor(String name, boolean b, ActorId id) {
		super(name, true, ObjectCategory.NPC);
		this.id = id;
	}

	public ActorId getActorId(){
		return id;
	}
	/*
	 * All actors must implement how they behave each turn
	 */
	public abstract void move(Dungeon d);

	// All actors must implement any special interacts between
	// other objects
	@Override
	public abstract void interact(DungeonObject o, Dungeon d);

	abstract public void update(Dungeon dungeon, Graph graph);
}