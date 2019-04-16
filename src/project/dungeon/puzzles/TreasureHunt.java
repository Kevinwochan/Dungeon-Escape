package project.dungeon.puzzles;

import java.io.IOException;
import java.util.Scanner;

import project.Core;
import project.dungeon.Dungeon;
import project.dungeon.actors.npcs.Hunter;
import project.dungeon.dungeonObjects.items.Treasure;
import project.menus.PuzzleGame;

/*
 * Preset Maze Puzzle
 * Only completion requirement is for all the tresures to be collected
 */
public class TreasureHunt extends Puzzle{
	int treasuresUncollected;
	/*
	 * Constructor class 
	 * @param size, the size of the new maze puzzle
	 */
	public TreasureHunt(int size) throws IOException {
		super(size);
		this.generateMap();
		treasuresUncollected = size;
	}
	
	@Override
	public String getType() {
		return "TreasureHunt";
	}
	
	@Override 
	public boolean hasWon() {
		if (treasuresUncollected <= 0) return true;
		return false;
	}
	/*
	 * Generates a puzzle with pairs of boulders and switches
	 * @param puzzle, takes in the grid of tiles created by puzzle
	 * and adds the treasure
	 */
	@Override
	public void generateMap() throws IOException {
		super.fillWithFloors();
		super.surroundByWalls();
		// randomly place size pairs of boulders and switches
		int size = super.getSize();
		for (int i = 0; i<size; i++) {
			// place treasures
			super.placeRandom(new Treasure());
		}
		super.placeRandom(new Hunter());
	}
	
	public static void main (String args[]) throws IOException {
		// Start the game using treasure hunt map
		Core main = new Core();
		
		// Create structs to replace auto generated level
		Puzzle puzzle = new TreasureHunt(9);
		Dungeon dungeon = new Dungeon(puzzle);
		
		// insert structs into the current process
		main.setState(new PuzzleGame(main, dungeon));
		
		// Play game
		System.out.println("Welcome to my Dungeon");
		main.getState().display(); //menu
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			main.execute(input.nextLine());
			main.getState().display();
		}
		input.close();
		main.getState().back();	
	}

	public void collectedTreasure() {
		treasuresUncollected--;
	}
}
