package project.dungeon.factories;

import project.dungeon.actors.npcs.Coward;
import project.dungeon.actors.npcs.Hunter;
import project.dungeon.actors.npcs.Npc;

public class ItemFactory extends AbstractFactory {

	public Npc generate(String npcType) {
		switch(npcType){
			case "coward":
				return new Coward();
			case "hunter":
				return new Hunter();
			default:
				return null;
		}
	}

}
