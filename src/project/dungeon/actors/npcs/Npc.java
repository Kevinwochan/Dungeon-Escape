package project.dungeon.actors.npcs;

import java.util.ArrayList;
import java.util.LinkedList;

import project.dungeon.Dungeon;
import project.dungeon.Dungeon.DungeonState;
import project.dungeon.actors.Actor;
import project.dungeon.actors.ActorId;
import project.dungeon.actors.statuses.Dead;
import project.dungeon.actors.statuses.Status;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.ObjectCategory;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.tiles.MazeTile;
import project.utils.Graph;

/**
 * NPC template for all npcs
 * an Actor but with a plan that can be affected by statues
 * @author kevinwochan 
 *
 */
abstract public class Npc extends Actor{
	LinkedList<Status>statuses;
	MapCoordinate co;
	ArrayList<MapCoordinate> plan;
	
	/*
	 * Constructor class
	 */
	public Npc(String name, MapCoordinate co, ActorId id) {
		super(name, true, id);
		this.co = co;
		this.statuses = new LinkedList<Status>();
	}

	public Npc(String name, ActorId id) {
		super(name, true, id);
		this.statuses = new LinkedList<Status>();
		this.co =  new MapCoordinate();
	}

	/*
	 * Getters and Setters
	 */
	public MapCoordinate getCoordinates() {
		return co;
	}

	public void setCoordinates(MapCoordinate currentCoordinates) {
		this.co = currentCoordinates;
	}

	/*
	 * Updates the NPC's plans
	 * @param d The current dungeon being played
	 * @param g A graph of the dungeon's map
	 */
	public void update(Dungeon d, Graph g) { }

	/*
	 * Moves an NPC according to their next move
	 * @param dungeon The current dungeon in play
	 */
	@Override
	public void move(Dungeon dungeon) {
		// move the npc from one tile to the other
		dungeon.getPuzzle().move(this, plan.get(0));	
		// interact with the objects
		MazeTile tile = dungeon.getPuzzle().getTile(plan.get(0));
		tile.interact(this, dungeon);
		interact(tile, dungeon);
		for (DungeonObject o: tile.getObjects()) {
			o.interact(this, dungeon);
			this.interact(o, dungeon);
		}
		
	}
	
	/*
	 * Defines how the NPC reacts to other objects
	 * @param
	 */
	@Override
	public void interact(DungeonObject o, Dungeon d) {
		if (o.getCategory() == ObjectCategory.PLAYER) {
			d.setStatus(DungeonState.LOST);
		}
	}

	@Override
	public void interact(MazeTile t, Dungeon d) {
		// interact with objects on the tile
		if (! t.hasObjects()) return;
		for (DungeonObject o :t.getObjects()) {
			o.interact(this, d);
		}

	}

	public void addStatus(Status status) {
		statuses.add(status);
	}
	public void removeStatus(Status status) {
		statuses.remove(status);
	}

	public boolean isDead() {
		if (statuses.contains(new Dead())) return true;
		return false;
	}

	public void updateCoor(MapCoordinate co) {
		this.co = co;
	}
	
	abstract public String getSprite();
}
