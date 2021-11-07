package model.dungeon.puzzles.winconditions;

import java.util.LinkedList;

import model.dungeon.tiles.Switch;

public class BoulderSwitchesWin implements WinCondition{
	LinkedList<Switch> switches;
	
	public BoulderSwitchesWin( LinkedList<Switch> switches ) {
		this.switches = switches;
	}
	@Override
	public boolean hasWon() {
		for (Switch s: switches) {
			if (! s.isActive()) return false;
		}
		return true;
	}
	
}
