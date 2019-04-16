package project.dungeon.actors.npcs;

import java.io.IOException;

import project.dungeon.Dungeon;
import project.dungeon.actors.ActorId;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.utils.Graph;

public class Coward extends Npc{
	String sprite;
	
	public Coward(MapCoordinate co) throws IOException {
		super("Coward",co,ActorId.COWARD);
		sprite = "assets/gnome.png";
	}

	public Coward() {
		super("Coward",new MapCoordinate(-1,-1),ActorId.COWARD);
	}

	@Override
	public void update(Dungeon d, Graph g) {
		// when the coward is within 3 moves of the player
		MapCoordinate player = d.getPlayerCoordinates();
		MapCoordinate src = getCoordinates();
		if (plan.size() <= 3) {
			MapCoordinate dst = player.reflect(8);
			plan = g.BFS(src, dst);
		}else {
			plan = g.BFS(src, player);
		}
	}

	@Override
	public String getSprite() {
		return sprite;
	}	

}
