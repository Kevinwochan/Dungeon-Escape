package project.dungeon;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import project.dungeon.actors.Actor;
import project.dungeon.actors.ActorId;
import project.dungeon.actors.npcs.Npc;
import project.dungeon.actors.player.Player;
import project.dungeon.actors.statuses.Dead;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonoperations.DungeonOperation;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.factories.AbstractFactory;
import project.dungeon.factories.DungeonFactory;
import project.dungeon.puzzles.Levels;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.puzzles.TreasureHunt;
import project.dungeon.tiles.MazeTile;
import project.utils.Graph;
/*
 * Describes a maze AND the player 
 * 
 * Handles game loop logic and interfaces with the player's input and oberservers
 * (player's input converted into objects in the PuzzleGame state)
 * 
 * This is instantiated when creating a puzzle or starting a new one
 * 
 */
public class Dungeon {
	// a hash map of observers for easy access
	private LinkedList<Actor> observers;
	// a queue of actions for the game to complete e.g move characters or generate objects
	// used to deal with concurrent modification of the map
	private LinkedList<DungeonOperation> actions;
	// a status of the dungeon
	private DungeonState status;
	// the player for easy access
	private Player player;
	private Puzzle puzzle;
	
	/*
	 * Stores the current state of the dungeon
	 */
	public enum DungeonState{
		PLAYING,
		WON,
		LOST;
	}
	/*
	 *  Dungeon constructor class
	 */
	public Dungeon() throws IOException{
		actions = new LinkedList<DungeonOperation>();
		observers = new LinkedList<Actor>();
		status = DungeonState.PLAYING;
		puzzle = generatePuzzle(9);
		player = puzzle.placePlayer();
	}
	
	public Dungeon(Puzzle puzzle) throws IOException {
		this.actions = new LinkedList<DungeonOperation>();
		this.observers =  new LinkedList<Actor>();
		this.puzzle = puzzle;
		this.player = puzzle.placePlayer();
		this.status = DungeonState.PLAYING;
	}

	/*
	 * Getters and Setters
	 */
	public Puzzle getPuzzle() {
		return puzzle;
	}
	
	public int getSize() {
		return puzzle.getSize();
	}
	/*
	 * @param the id of the Actor in the dungeon
	 * @return the object if it exists
	 */
	public Actor getActor(ActorId id) {
		for (Actor a: observers) {
			if (a.getActorId() == id) return a;
		}
		return null;
	}
	/*
	 * @return the player object of the dungeon
	 */
	public Player getPlayer() {
		return player;
	}
	
	public MapCoordinate getPlayerCoordinates() {
		return player.getCoordinates();
	}
	
	public DungeonState getStatus() {
		return status;
	}

	public void setStatus(DungeonState status) {
		this.status = status;
	}

	/*
	 * Prints the player and the map in STDIN
	 */
	public void displayDungeon() { 
		// display the current state of the map
		puzzle.display();
		// display the player inventory
		// player.display();
	}

	/*
	 *  Tick iterates the game's core logic, this function is called
	 *  to move the game forward
	 */
	public void Tick() throws IOException {
		if (puzzle.hasWon()) status = DungeonState.WON;
		// check is player is still alive
		if (player.getStatuses().contains(new Dead())) status = DungeonState.LOST;
		// update all the NPCs of the player's move and move them accordingly
		notifyAllNpcs(this);
	}
	/*
	 * Performs all actions queued in the actions list
	 */
	private void resolve() {
		while (actions.iterator().hasNext()) {
			DungeonOperation a = actions.iterator().next();
			a.act(this);
			actions.remove(a);
		}
	}
	/*
	 * Queues an actions to the list of things to perform
	 */
	public void queueAction(DungeonOperation action) {
		actions.add(action);
	}
	
	/*
	 *  Actors: Things that move each turn
	 *  Used Observer Design pattern
	 */

	/*
	 * Dungeon Manipulation
	 */
	/*
	 * Moves the player by a coordinate offset
	 * @param the coordinate to offset the player's current position
	 */
	public void movePlayer(MapCoordinate coOffset) {
		resolve();
		MapCoordinate newCo = player.getCoordinates().add(coOffset);
		System.out.println(puzzle.isWalkable(player, player.getCoordinates(), newCo));
		if (! puzzle.isWalkable(player, player.getCoordinates(), newCo)) return;
		System.out.println("player updatedL " + player.getCoordinates().getX() + player.getCoordinates().getY());
		// Validate the player's move
		
		//move the player object 
		player.move(this, newCo);

		System.out.println("player updatedL " + player.getCoordinates().getX() + player.getCoordinates().getY());
		resolve();
	}
	
	/*
	 * Notify all observers of the player's move
	 */
	private void notifyAllNpcs(Dungeon dungeon) throws IOException {
		Graph graph = new Graph(puzzle);
		for (Actor a: observers) {
			a.update(this, graph);
			resolve();
		}
	}

	/*
	 * Attach Game Observer
	 */
	public void attach(Actor a) {
		observers.add(a);
	}
	
	/*
	 * Dungeon Customizing
	 */
	/*
	 * Generates and adds and object to the dungeon
	 * used in Design Mode for manually adding objects
	 * utilises abstract factory dungeon 
	 */
	/*
	 * Instantiates factory design pattern
	 * and registers object into the dungeon
	 * @param DungeonObject must of the string listed in dungeonFactory
	 * @param MazeCoordinate contains the X and Y Coordinate for the new object to be placed
	 * 
	 */
	public void addObject(DungeonObject objectName, MapCoordinate co) {
		// if the object could not be created
		if (objectName == null) {
			System.out.println("invalid object");
			return;
		}else {
			puzzle.addObject(objectName, co);
		}
	}

	public void add(String objectType, String name, MapCoordinate co) throws IOException {
		AbstractFactory m = DungeonFactory.getFactory(objectType);
		switch (objectType) {
			case "tile":
				MazeTile t = (MazeTile) m.generate(name);
				puzzle.setTile(co, t);
			case "npc":
				Npc npc = (Npc) m.generate(name);
				npc.setCoordinates(co);
			default:
				return;
		}
	}
	
	/*
	 *  Lists all available objects the user can add to a design
	 */
	public void listObjects(DungeonFactory factory) {
		DungeonFactory.listObjects();
	}
	@Override
	public String toString() {
		return puzzle.toString() + "\n" + player.toString();
	}

	public Puzzle generatePuzzle(int size) throws IOException {
		Random r = new Random();
		int max = Levels.values().length-1;
		int pick = r.nextInt((max - 1) + 1) + 1; //r.nextInt(Levels.values().length);
		Levels level = Levels.values()[pick];
		return new TreasureHunt(size);
		/*
		switch(level) {
			case TREASUREHUNT:
				return new TreasureHunt(size);
			case BOULDERSWITCH:
				return new BoulderSwitch(size);
			case MAZE:
				return new Maze(size);
			default:
				return new Puzzle(size);
		}*/
	}
	
	public void setPlayer(Player player, int x, int y) {
		MapCoordinate co =  new MapCoordinate(x,y);
		puzzle.placeObject(player, co);
		player.setCoordinates(co);
		this.player = player;
	}

	public void dropPlayerItem(int inventoryIndex) throws IOException {
		player.drop(this, inventoryIndex);
	}

	public void usePlayerItem(int inventoryIndex) {
		// TODO Auto-generated method stub
	}

	public MapCoordinate getNpc(ActorId hunter) {
		return null;
	}
	
}