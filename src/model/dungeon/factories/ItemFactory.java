package model.dungeon.factories;

import model.dungeon.actors.npcs.Coward;
import model.dungeon.actors.npcs.Hunter;
import model.dungeon.actors.npcs.Npc;

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
