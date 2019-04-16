package project.menus;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import project.Core;
import project.dungeon.Dungeon;
import project.dungeon.dungeonoperations.MapCoordinate;

/*
 * Main Game Logic State
 */
public class PuzzleGame implements State{
	private Core main;
	private Dungeon dungeon;
	private Pattern format;
		
	/*
	 * Load a puzzle
	 */
	public PuzzleGame(Core main, Dungeon d) {
		this.main = main;
		this.dungeon = d;
		this.format = Pattern.compile("\\s+ \\d+");
	}
	/*
	 * generate a new dungeon at random
	 */
	public PuzzleGame(Core main) throws IOException {
		this.main = main;
		this.dungeon = new Dungeon();
		this.format = Pattern.compile("\\s+ \\d+");
	}
	
	@Override
	public void display() {
		System.out.println("=== Puzzle Game Mode ===");	
		// display the state of the dungeon
		System.out.println(dungeon);	
		// print our the input options
		this.help();
	}

	public Dungeon getDungeon() {
		return this.dungeon;
	}
	
	@Override
	public void back() {
		main.setState(main.getLoadPuzzle());
	}
	
	private void GameOver() {
		main.setState(main.getGameOver());
	}

	@Override
	public void handleInput(String input) throws IOException {
		Matcher matcher = format.matcher(input);	
		switch(input) {
			case "back":
				this.back();
			// movement
			case"w":
				dungeon.movePlayer(new MapCoordinate(0,1));
				break;
			case"s":
				dungeon.movePlayer(new MapCoordinate(0,-1));
				break;
			case"a":
				dungeon.movePlayer(new MapCoordinate(-1,0));
				break;
			case"d":
				dungeon.movePlayer(new MapCoordinate(1,0));
				break;
			// use an item
			default:
				String command = matcher.group(0);
				int inventoryIndex = Integer.valueOf(matcher.group(1));
				switch(command) {
					case "drop":
						dungeon.dropPlayerItem(inventoryIndex);
						break;
					case "use":
						dungeon.usePlayerItem(inventoryIndex);
						break;
					default:
						this.help();
						return;
				}
			}
		dungeon.Tick();
		checkDungeon();
	}

	public void checkDungeon() {
		switch (dungeon.getStatus()) {
			case WON:
				GameOver();
				return;
			case LOST:
				GameOver();
				return;
			default:
				return;
		}
	}
	@Override
	public void help() {
		System.out.println("Please Select:");
		System.out.println("Game: save 	exit");
		System.out.println("Move: w (Up)    s (Down)    a (Left)    d (Right)");
		System.out.println("Use Item: itemName (x,y)");
	}

	public static void main (String args[]) throws IOException {
		Core main = new Core();
		main.setState(new PuzzleGame(main));
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
