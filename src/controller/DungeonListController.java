package controller;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import model.Core;
import model.dungeon.Dungeon;
import model.dungeon.puzzles.Puzzle;
import model.menus.PuzzleGame;
import model.menus.State;

/*
 * Pick a dungeon to play or design
 */
public class DungeonListController extends Controller{
	State state;
	boolean isPuzzle;
	Core core;
	
	@FXML
	private Label lb;
	
	@FXML
	private ListView<String> ListViewWindow;
	ObservableList<String> items = FXCollections.observableArrayList();

	public DungeonListController(Stage s, State state, boolean isGame, Core core) {
		super(s);
		this.isPuzzle = isGame;
		ListViewWindow = new ListView<>();
		this.state = state;
		this.core = core;
	}

	@FXML
    public void initialize() throws IOException {
		ListViewWindow.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//Core c = new Core();
		int i = 0;
		for (int j = 0; j<5; j++) {
			Puzzle p = new Puzzle(9);
			Dungeon d  = new Dungeon(p);
			core.save(d);
		}
		for (Dungeon save: core.getSaves() ) {
			items.add("Saved Dungeon: "+ String.valueOf(i++));
			System.out.println("did it");
		}
		ListViewWindow.setItems(items);
	}
	
	@FXML
	public void selectButton(ActionEvent event){
		int saveIndex = ListViewWindow.getSelectionModel().getSelectedIndex();
		if(saveIndex > 0) {
			// load the dungeon from the core class
			System.out.println("nolssbskacjj " + saveIndex );
			Dungeon dungeon = core.load(saveIndex); 
			System.out.println("nolssss");
			// move to either designing the game or playing a game
	    	if (isPuzzle) {
				// load the state to prepare the game
	    		Screen load = new Screen(super.getS(), "Dungeon", "view/GamePlay.fxml");
				PuzzleGame pg = new PuzzleGame(core, dungeon);
				core.setState(pg);
				PlayController controller = new PlayController(super.getS(), pg);
				load.start(controller);
	    	}else {
	    		 //load the state to prepare the design mode
//				DesignDungeon dd = new DesignDungeon(core, dungeon);
//				core.setState(dd);
//				DesignController controller = new DesignController(super.getS(), dd);
//				load.start(controller);
	    	}
		}else {
			//lb.setText("no saved ones!!!");
			System.out.println("no saved ones!!");
		}	
	}
}	