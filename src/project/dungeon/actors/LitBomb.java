package project.dungeon.actors;

import java.io.IOException;
import java.util.LinkedList;

import project.dungeon.Dungeon;
import project.dungeon.actors.npcs.Npc;
import project.dungeon.actors.player.Player;
import project.dungeon.actors.statuses.Dead;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.ObjectCategory;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.tiles.MazeTile;
import project.utils.Graph;

/*
 *  A lit bomb will explode after a few turns
 *  causing boulders, players and npcs to die
 */
public class LitBomb extends Actor {
	// the amount of turns before a bomb explodes
	int fuse;
	//
	String sprite;
	
	// the location of the bomb
	MapCoordinate co;
	
	public LitBomb(MapCoordinate co) throws IOException {
		super("Lit Bomb", false, ActorId.LITBOMB);
		this.fuse = 3;
		this.co = co;
		sprite = "file://assets/bomb_lit_1.png";
	}
	
	public void interact(DungeonObject o, Dungeon d) {
		//
	}

	@Override
	public void interact(MazeTile t, Dungeon d) {
		// 
	}

	@Override
	public void move(Dungeon d) {
		if (fuse == 0) {
			explode(d);
		}else {
			fuse--;
		}
	}

	/*
	 *  An exploding bomb destroys 
	 *  boulders, npcs and the player
	 *  in adjacent tiles
	 */
	private void explode(Dungeon d) {
		Puzzle puzzle = d.getPuzzle();
		LinkedList<MapCoordinate>adjacentCos = co.adjCoordinates();
		LinkedList<MazeTile>adjacentTiles = puzzle.getTiles(adjacentCos);
		for (MazeTile tile: adjacentTiles) {
			LinkedList<DungeonObject> toDestroy = new LinkedList<>();
			for (DungeonObject o : tile.getObjects()) {
				ObjectCategory cat = o.getCategory();
				switch(cat) {
					case BOULDER:
						toDestroy.add(o);
						break;
					case PLAYER:
						Player player = (Player)o;
						player.addStatus(new Dead());
						break;
					case NPC:
						toDestroy.add(o);
						Npc n = (Npc)o;
						n.addStatus(new Dead());
						break;
					default:
						break;
				}
			}
			tile.removeAll(toDestroy);
		}
	}

	
	/*
	 * 
	 * 
	 */
	public void update(Dungeon dungeon, Graph graph) {}

	@Override
	public String getSprite() {
		return sprite;
	};
	
}
