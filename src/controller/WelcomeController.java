package controller;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Core;
import model.menus.DesignState;
import model.menus.PuzzleState;

public class WelcomeController extends Controller{
	Core core;
	
	public WelcomeController(Stage s) {
		super(s);
		this.core = Core.getCore();
	}
	
	@FXML
    public void designButton(ActionEvent event) {
		//change state
		DesignState state = new DesignState(core) ;
		core.setState(state);
		// load screen
    	Screen load = new Screen(super.getS(), "Design a Dungeon", "view/LoadOrNew.fxml");
    	LoadOrNewController controller = new LoadOrNewController(super.getS(), core, false);
        load.start(controller);
	}
	
	@FXML
    public void puzzleButton(ActionEvent event) {
		//change state
		PuzzleState state = new PuzzleState(core) ;
		core.setState(state);
		// load screen 
    	Screen load = new Screen(super.getS(), "Design a Dungeon", "view/LoadOrNew.fxml");
    	LoadOrNewController controller = new LoadOrNewController(super.getS(), core, true);
        load.start(controller);	
	}
	
	@FXML
    public void exitButton(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	public void handleSettings(ActionEvent event) {
		Screen load = new Screen(super.getS(), "Display Settings", "view/Settings.fxml");
		settingsController controller = new settingsController(super.getS());
		load.start(controller);
	}
}
