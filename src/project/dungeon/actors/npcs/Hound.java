package project.dungeon.actors.npcs;

import java.io.IOException;

import project.dungeon.Dungeon;
import project.dungeon.actors.ActorId;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.utils.Graph;

public class Hound extends Npc{
	String sprite;
	
	public Hound(MapCoordinate currentCoordinates) throws IOException {
		super("hound", currentCoordinates, ActorId.HOUND);
		sprite = "assets/hound.png";
	}
	public Hound() {
		super("Hound", new MapCoordinate(-1,-1),ActorId.HOUND);
	}

	@Override
	public void update(Dungeon d, Graph g) {
		Npc hunter = (Npc)d.getActor(ActorId.HUNTER);
		MapCoordinate hunterCo = hunter.getCoordinates();
		MapCoordinate player = d.getPlayerCoordinates();
		
		MapCoordinate dst = hunterCo.reflect(player);
		plan = g.BFS(getCoordinates(), dst);
	}

	@Override
	public String getSprite() {
		return sprite;
	}


}
