package model.dungeon.puzzles;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import model.Core;
import model.dungeon.Dungeon;
import model.dungeon.dungeonObjects.Boulder;
import model.dungeon.puzzles.winconditions.BoulderSwitchesWin;
import model.dungeon.puzzles.winconditions.WinCondition;
import model.dungeon.tiles.Pit;
import model.dungeon.tiles.Switch;
import model.menus.PuzzleGame;

/*
 * Preset Maze Puzzle
 * Only completion requirement is to move all boulders onto switches
 */
public class BoulderSwitch extends Puzzle{
	WinCondition condition;
	/*
	 * Constructor class 
	 * @param size, the size of the new maze puzzle
	 */
	public BoulderSwitch(int size) throws IOException {
		// size must be an odd number for the maze to be generated
		super(size);
		this.generateMap();
	}
	
	@Override
	public String getType() {
		return "BoulderSwitch";
	}
	
	/*
	 * Generates a puzzle with pairs of boulders and switches
	 */
	@Override
	public void generateMap() throws IOException {
		super.fillWithFloors();
		super.surroundByWalls();
		int size = super.getSize();
		LinkedList<Switch> switches = new LinkedList<Switch> ();
		for (int i = 0; i<size; i++) {
			super.placeRandom(new Boulder());
			Switch s = new Switch();
			switches.add(s);
			super.placeRandom(s);
			if (size%2 == 0) super.placeRandom(new Pit());
		}
		this.condition  = new BoulderSwitchesWin(switches);
	}
	
	@Override 
	public boolean hasWon() {
		return (condition.hasWon());
	}
	public static void main (String args[]) throws IOException {
		// Start the game using treasure hunt map
		Core main = new Core();
		// create structs to replace auto generated
		// level
		Puzzle puzzle = new BoulderSwitch(9);
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
}
