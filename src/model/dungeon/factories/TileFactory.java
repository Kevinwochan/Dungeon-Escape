package model.dungeon.factories;

import java.io.IOException;

import model.dungeon.tiles.Exit;
import model.dungeon.tiles.Floor;
import model.dungeon.tiles.MazeTile;
import model.dungeon.tiles.Wall;

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
