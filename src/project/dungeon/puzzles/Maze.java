package project.dungeon.puzzles;

import java.io.IOException;
import java.util.Scanner;

import project.Core;
import project.dungeon.Dungeon;
import project.dungeon.puzzles.winconditions.ExitReached;
import project.dungeon.tiles.Exit;
import project.menus.PuzzleGame;
import project.utils.MazeGenerator;

/*
 * Preset Maze Puzzle
 * Only completion requirement is to reach the exit
 * @invariant size = 2n+1
 */
public class Maze extends Puzzle{
	 ExitReached condition;
	/*
	 * Constructor class 
	 * @param size, the size of the new maze puzzle
	 */
	public Maze(int size) throws IOException {
		// size must be an odd number for the maze to be generated
		super(size + ((size + 1)% 2));
		this.generateMap();
		Exit e = new Exit();
		this.condition = new ExitReached(e);
		super.placeExit(e);
	}
	
	@Override
	public String getType() {
		return "Maze";
	}
	
	/*
	 * Generates a solvable maze 
	 * @param size, an integer that must be an odd number
	 */
	@Override
	public void generateMap() throws IOException {
		MazeGenerator maze = new MazeGenerator(super.getSize());
		maze.display();
		super.setMap(maze.generate());
	}
	
	@Override
	public boolean hasWon() {
		return condition.hasWon();
	}
	
	public static void main (String args[]) throws IOException {
		// Start the game using treasure hunt map
		Core main = new Core();
		// create structs to replace auto generated
		// level
		Puzzle puzzle = new Maze(9);
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
