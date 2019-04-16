package project.dungeon.dungeonObjects;

import project.dungeon.Dungeon;
import project.dungeon.actors.player.Player;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.dungeonoperations.Move;
import project.dungeon.dungeonoperations.MovePlayer;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.tiles.MazeTile;
import project.dungeon.tiles.Switch;
import project.dungeon.tiles.TileCategories;

/*
 * 
 */
public class Boulder extends DungeonObject {
	String sprite;
	
	public Boulder(){
		super("Boulder", false, ObjectCategory.BOULDER);
		this.sprite = "assets/boulder.png"; 
		
	}
	
	@Override
	public void interact(DungeonObject o, Dungeon d) {
		if (o.getCategory() == ObjectCategory.PLAYER) {
			// find where to move the boulder
			Player p = (Player) o;
			MapCoordinate currCo = p.getCoordinates();
			MapCoordinate newCo = newCoordinates(p);
			
			// if the boulder cant move to this coordinate, move the player back
			if (! checkTile(d, currCo, newCo) ) { 
				d.queueAction(new MovePlayer(p, p.getPrevCoordinates()));
			// else move the boulder
			}else {
				d.queueAction(new Move(this, p.getCoordinates(), newCo));
			}
		}
	}

	/*
	 * Checks if it is possible for a boulder to move to a coordinate
	 */
	private boolean checkTile(Dungeon d, MapCoordinate currCo, MapCoordinate newCo) {
		// if the Boulder cannot move to this new coordinate, move the player back
		Puzzle puzzle = d.getPuzzle();
		if (! puzzle.isWalkable(this, currCo, newCo)) {
			return false;
		}
		// if the new coordinate's tile already has a boulder
		MazeTile tile = puzzle.getTile(newCo);
		if (tile.contains(ObjectCategory.BOULDER)) {
			return false;
		}		
		return true;
	}
	
	/*
	 * Finds the new coordinate of the boulder
	 */
	MapCoordinate newCoordinates(Player p) {
		MapCoordinate currCo = p.getCoordinates();
		MapCoordinate prevCo = p.getPrevCoordinates();
		MapCoordinate moveDirection = (currCo.subtract(prevCo));
		return moveDirection.add(currCo);
	}
	
	
	@Override
	public void interact(MazeTile t, Dungeon d) {
		if (t.getTileCategory() == TileCategories.SWITCH) {
			Switch s = (Switch)t;
			s.setActive(true);
		}
	}

	@Override
	public String getSprite() {
		return sprite;
	}
}
