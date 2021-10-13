package controller;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import project.Core;
import project.menus.PuzzleGame;
import project.menus.PuzzleLoad;

/*
 * 
 */
public class LoadGameController extends Controller{

	Core core;
	
	public LoadGameController(Stage s) {
		super(s);
		this.core = new Core();
	}

	@FXML
    public void initialize() {
	}
	
	@FXML
	public void newDungeonButton(ActionEvent event) throws IOException{
		//change state
		PuzzleGame state = new PuzzleGame(core);
		core.setState(state);
		Screen screen = new Screen(super.getS(), "Escape the dungeon", "view/GamePlay.fxml");
    	PlayController controller = new PlayController(super.getS(), state);
        screen.start(controller);	
	}
	
	@FXML
    public void loadDungeonButton(ActionEvent event) {
		PuzzleLoad state = new PuzzleLoad(core);
		core.setState(state);
    	Screen load = new Screen(super.getS(), "Choose a Dungeon", "view/DungeonList.fxml");
    	DungeonListController controller = new DungeonListController(super.getS(), state, true, core);
        load.start(controller);
	}
	
	@FXML
	public void exitButton(){
		System.exit(0);
	}
	
}
