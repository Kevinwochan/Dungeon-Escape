package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import project.dungeon.Dungeon;
import project.menus.DesignDungeon;
import project.menus.DesignLoad;
import project.menus.DesignState;
import project.menus.GameOver;
import project.menus.Menu;
import project.menus.PuzzleLoad;
import project.menus.PuzzleState;
import project.menus.State;

/**
 * The core class used to run the game
 * 
 * Main chooses the state the application runs in
 * e.g when selecting Puzzle state from the menu
 * Main will update the state then load the relevant
 * JavaFX for that state
 * 
 * @author kevinwochan
 */

public final class Core {
	// Singleton
	private static Core instance = null;
	// initalising all states to avoid creating states at run time
	private static State state;   
	// a list of saved dungeons
	private ArrayList <Dungeon> saves;

/**
 * Default Class constructor
 */
	public Core(){
		// default to main menu
		state = new Menu(this);   
		saves = new ArrayList <Dungeon>();
	}
	
	public synchronized static Core getCore() {
		if (instance == null) return new Core();
		return instance;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
		
	public State getDesign() {
		return new DesignState(this);
	}
	
	public State getPuzzle() {
		return new PuzzleState(this);
	}
		
	public State getDesignDungeon() throws IOException {
		return new DesignDungeon(this);
	}
	public State getLoadDesign() {
		return new DesignLoad(this);
	}

	public void setLoadDesign(State loadDesign) {
		state = loadDesign;
	}

	public State getLoadPuzzle() {
		return new PuzzleLoad(this);
	}

	public State getMenu() {
		return new Menu(this);
	}

	public State getGameOver() {
		return new GameOver(this);
	}

	public void back () {
		state.back();
	}
	
	public void execute(String line) throws IOException {
		state.handleInput(line);
	}

	public ArrayList <Dungeon> getSaves() {
		return saves;
	}
	
	public void save (Dungeon d) {
		saves.add(d);
	}

	public Dungeon load (int index) {
		return saves.get(index);
	}
	
	public void delete (int index) {
		saves.remove(index);
	}
	
	public static void main(String[] args) throws IOException {
		Core game = new Core();
		System.out.println("Welcome to my Dungeon");
		state.display(); //menu
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			game.execute(input.nextLine());
			state.display();
		}
		input.close();
		state.back();
	}

}
