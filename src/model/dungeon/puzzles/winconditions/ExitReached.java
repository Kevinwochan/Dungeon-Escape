package model.dungeon.puzzles.winconditions;

import model.dungeon.dungeonObjects.ObjectCategory;
import model.dungeon.tiles.Exit;

public class ExitReached implements WinCondition{
	Exit exit;
	
	public ExitReached(Exit e) {
		exit = e;
	}

	@Override
	public boolean hasWon() {
		if (exit.contains(ObjectCategory.PLAYER)) return true;
		return false;
	}
	
}
