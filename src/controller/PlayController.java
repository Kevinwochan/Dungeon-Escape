package controller;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.text.html.ImageView;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import project.Core;
import project.dungeon.Dungeon;
import project.dungeon.actors.player.Player;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.items.Item;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.tiles.MazeTile;
import project.menus.DesignState;
import project.menus.PuzzleGame;

//import sun.awt.image.BufferedImageGraphicsConfig;


public class PlayController extends Controller{

	@FXML
	private GridPane layout;
	@FXML
	private GridPane objectsGrid;
	@FXML
	private GridPane inventoryGrid;
	
	private PuzzleGame maze;
	
	public PlayController(Stage s, PuzzleGame my_maze) {
		super(s);
		this.maze = my_maze; 
	}
	
	@FXML
    public void initialize() {
		//System.out.println(puzz.toString());
		//render the maze, first get the object per index
		Dungeon d = maze.getDungeon();
		displayGrid(d);
		displayInventory(d.getPlayer());
	}
	
	public void displayInventory(Player player) {
		inventoryGrid.getChildren().clear();
		int x = 0; int y = 0;
		for (Item i: player.getItems()) {
			x++;
		}
	}
	
	public void displayGrid(Dungeon d) {
		layout.getChildren().clear();
		Player me = d.getPlayer();	
		Puzzle puzz = d.getPuzzle();

		//System.out.println(puzz.toString());
		//render the maze, first get the object per index
		MazeTile[][] maze_layout = puzz.getMap();
		//System.out.println("issa " + puzz.getType());
		for(int i =0; i < maze_layout.length; i++) {
			for(int j =0; j < maze_layout[i].length; j++  ) {
				MapCoordinate co = new MapCoordinate(i, j);
		    	MazeTile tile = puzz.getTile(co);
				if(tile.getSprite() != null) {
					System.out.println("Its a " + tile.getSprite() + " at " + i + j);
					Image img = new Image(tile.getSprite());
			    	ImageView imgView = new ImageView(img);
			    	imgView.setFitHeight(40);
			    	imgView.setFitWidth(40);
			    	layout.add(imgView, i,  j);
				}
				
				if(tile.hasObjects()) {
					for (DungeonObject o: tile.getObjects() ) {
						if (o.getSprite() != null) {
							System.out.println("Object a " + tile.getTileName()  + " "+ o.getSprite() + " at " + i + j);
							Image img = new Image(o.getSprite());
					    	ImageView imgView = new ImageView(img);
					    	imgView.setFitHeight(40);
					    	imgView.setFitWidth(40);
					    	layout.add(imgView, i,  j);
						}
						if(o.getName().contentEquals("Player")) {
							me.interact(o, d);
							if(tile.getTileName().contentEquals("Exit")) {
								DesignState pg = new DesignState(Core.getCore());
								Core.getCore().setState(pg);
								Screen screen = new Screen(super.getS(), "End", "view/End.fxml");
								endController controller = new endController(super.getS(), "YOU WIN!");
								screen.start(controller);
							}
						}
					}
				}
		}
		}
	}
		
	@FXML
    public void UpBtn(ActionEvent event) throws IOException {
		Dungeon d = maze.getDungeon();
		d.movePlayer(new MapCoordinate(0,-1));
		System.out.println("move player up");
		System.out.println("PLayer co: " + d.getPlayerCoordinates());
		// get the coordinate to move
		d.Tick();
		displayGrid(d);
		System.out.println("co?? : " + d.getPlayer().getCoordinates().getX() + " " + d.getPlayer().getCoordinates().getY());
	}
	
	@FXML
    public void DownBtn(ActionEvent event) throws IOException {
		Dungeon d = maze.getDungeon();
		d.movePlayer(new MapCoordinate(0,1));
		System.out.println("move player down");
		System.out.println("PLayer co: " + d.getPlayerCoordinates());
		// get the coordinate to move
		d.Tick();
		displayGrid(d);
		System.out.println("co?? : " + d.getPlayer().getCoordinates().getX() + " " + d.getPlayer().getCoordinates().getY());
	}
	
	@FXML
    public void LeftBtn(ActionEvent event) throws IOException {
		Dungeon d = maze.getDungeon();
		d.movePlayer(new MapCoordinate(-1,0));
		System.out.println("move player left");
		System.out.println("PLayer co: " + d.getPlayerCoordinates());
		// get the coordinate to move
		d.Tick();
		displayGrid(d);
		System.out.println("co?? : " + d.getPlayer().getCoordinates().getX() + " " + d.getPlayer().getCoordinates().getY());
	}
	
	@FXML
    public void RightBtn(ActionEvent event) throws IOException {
		Dungeon d = maze.getDungeon();
		d.movePlayer(new MapCoordinate(1,0));
		System.out.println("move player right");
		System.out.println("PLayer co: " + d.getPlayerCoordinates());
		// get the coordinate to move
		d.Tick();
		displayGrid(d);
		System.out.println("co?? : " + d.getPlayer().getCoordinates().getX() + " " + d.getPlayer().getCoordinates().getY());
		
	}
	
	
	@FXML
    public void exitButton(ActionEvent event) {
		System.exit(0);
	}
}