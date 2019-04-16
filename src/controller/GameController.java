package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import project.Core;
import project.dungeon.Dungeon;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.tiles.MazeTile;
import project.menus.PuzzleGame;

public class GameController extends Controller {
	@FXML 
	private GridPane map;
	
	@FXML 
	private GridPane inventory;
	
	Core core;
	PuzzleGame state;
	
	
	public GameController (Stage s, PuzzleGame state) {
		super(s);
		this.core = new Core();
		this.state = state;
	}
	@FXML
    public void initialize() {
		//render map
		Dungeon d = state.getDungeon();
		Puzzle puzz = d.getPuzzle();
		MazeTile[][] layout = puzz.getMap();
		for(int i =0; i < layout.length; i++) {
			for(int j =0; j < layout[i].length; j++) {
				MapCoordinate co = new MapCoordinate(i, j);
				MazeTile t = puzz.getTile(co);
//				BufferedImage img = t.getSprite();
//				Image image = SwingFXUtils.toFXImage(img, null);
//				
			}
		}
		
	}
	
	@FXML
	public void handleDirection() {
		
	}
	
	@FXML
	public void showContexMenu() {
		
	}
}
