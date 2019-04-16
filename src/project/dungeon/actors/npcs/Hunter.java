package project.dungeon.actors.npcs;

import project.dungeon.Dungeon;
import project.dungeon.actors.ActorId;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.utils.Graph;

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
