package project.dungeon.factories;

public class DungeonFactory {
	
	public static AbstractFactory getFactory(String factory) {
		switch (factory) {
			case "npc":
				return new NpcFactory();
			case "tile":
				return new TileFactory();
			case "item":
				return new ItemFactory();
			default :
				return null;
		
		}
	}
	
	public static void listObjects() {
		System.out.println("Wall");
		System.out.println("Floor");
		System.out.println("hunter");
		System.out.println("Coward");
	}

}
