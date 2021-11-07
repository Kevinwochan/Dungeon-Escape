package model.dungeon.actors.npcs;

import model.dungeon.Dungeon;
import model.dungeon.actors.ActorId;
import model.dungeon.dungeonoperations.MapCoordinate;
import model.utils.Graph;

/*
 * Hunter Npc
 * Walks the shortest path to the player
 */
public class Hunter extends Npc{	
	String sprite;
	
	public Hunter(MapCoordinate co) {
		super("Hunter", co, ActorId.HUNTER);
		sprite = "assets/hunter.png";
	}
	
	public Hunter() {
		super("Hunter", new MapCoordinate(-1,-1),ActorId.HUNTER);
	}
	
	@Override
	public void update(Dungeon d, Graph g) {
		MapCoordinate player = d.getPlayerCoordinates();
		plan = g.BFS(getCoordinates(), player);
	}

	@Override
	public String getSprite() {
		return sprite;
	}	

}
