package project.dungeon.factories;

import java.io.IOException;

import project.dungeon.tiles.Exit;
import project.dungeon.tiles.Floor;
import project.dungeon.tiles.MazeTile;
import project.dungeon.tiles.Wall;

public class TileFactory extends AbstractFactory{
	
	public MazeTile generate(String objectName) throws IOException {
		switch(objectName) {
			case "Wall":
				return new Wall();
			case "Floor":
				return new Floor();
			case "Exit":
				return new Exit();
			default:
				return null;
		}
	}
}
