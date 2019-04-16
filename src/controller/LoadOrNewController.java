package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import project.Core;
import project.menus.DesignDungeon;
import project.menus.DesignLoad;
import project.menus.PuzzleGame;
import project.menus.PuzzleLoad;

/*
 * Pick between loading a puzzle or a new puzzle (for design mode and play mode)
 */
public class LoadOrNewController extends Controller{
	boolean isPuzzleMode;
	Core core;
	
	public LoadOrNewController(Stage s, Core core, boolean isPuzzleMode) {
		super(s);
		this.isPuzzleMode = isPuzzleMode;
		this.core  = core;
	}
	
	
	@FXML
	public void newDungeonButton(ActionEvent event) throws IOException{
		if (isPuzzleMode) {
			PuzzleGame pg = new PuzzleGame(Core.getCore());
			Core.getCore().setState(pg);
			Screen screen = new Screen(super.getS(), "New Dungeon", "view/GamePlay.fxml");
			PlayController controller = new PlayController(super.getS(), pg);
			screen.start(controller);	
		}else{
			Screen screen = new Screen(super.getS(), "New Dungeon", "view/Design.fxml");
			DesignController controller = new DesignController(super.getS(), core);
			screen.start(controller);	
			Core.getCore().setState(new DesignDungeon(Core.getCore()));
		}
	}
	
    @FXML
	public void loadDungeonButton(ActionEvent event){
    	Screen screen = new Screen(super.getS(), "Choose a saved dungeon", "view/DungeonList.fxml");
		if (isPuzzleMode) {
			Core.getCore().setState(new PuzzleLoad(Core.getCore()));
		}else{
			Core.getCore().setState(new DesignLoad(Core.getCore()));
		}
		DungeonListController controller = new DungeonListController(super.getS(), core.getState(), isPuzzleMode, core);
        screen.start(controller);	
	}
	

	@FXML
	public void exitButton(){
		System.exit(0);
	}
}
