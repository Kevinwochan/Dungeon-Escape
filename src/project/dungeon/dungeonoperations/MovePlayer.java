package project.dungeon.dungeonoperations;

import project.dungeon.Dungeon;
import project.dungeon.actors.player.Player;

public class MovePlayer extends DungeonOperation{
	private Player p;
	private MapCoordinate newCo;
	public MovePlayer (Player p, MapCoordinate newCo) {
		this.p = p;
		this.newCo = newCo;
	}
	public void act(Dungeon dungeon) {
		dungeon.getPuzzle().move(p, newCo);
	}

}
