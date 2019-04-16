package project.dungeon.puzzles.winconditions;

import project.dungeon.dungeonObjects.ObjectCategory;
import project.dungeon.tiles.Exit;

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
