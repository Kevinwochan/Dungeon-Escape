package model.dungeon.actors.npcs;

import java.io.IOException;

import model.dungeon.Dungeon;
import model.dungeon.actors.ActorId;
import model.dungeon.dungeonoperations.MapCoordinate;
import model.utils.Graph;

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
