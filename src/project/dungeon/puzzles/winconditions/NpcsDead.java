package project.dungeon.puzzles.winconditions;

import java.util.LinkedList;

import project.dungeon.actors.npcs.Npc;

public class NpcsDead implements WinCondition{
	LinkedList<Npc> npcs;
	
	@Override
	public boolean hasWon() {
		for (Npc n: npcs) {
			if (! n.isDead()) return false;
		}
		return true;
	}
	
}
