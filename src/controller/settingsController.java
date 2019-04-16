package controller;

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import project.Core;

public class settingsController extends Controller{
	Core core;
	defaultSettings def;

	private Text actionTarget;
	public settingsController(Stage s) {
		super(s);
		this.core = new Core();
		def = new defaultSettings();
	}
	
	@FXML
	public void handleMusicButton(ActionEvent event) {
		def.toggleMusic();
		/*
		if(def.musicOn()) {
			actionTarget.setText("Music On");
		} else {
			actionTarget.setText("Music On");
		}*/
	}

	/*
	@FXML
	public void handleSoundsButton(ActionEvent event) {
		def.toggleSound();
		if(def.soundOn()) {
			actionTarget.setText("Game Sounds On");
		}
		else {
			actionTarget.setText("Game Sounds off");
		}
	}
	*/

    @FXML
    public void handleBackButton(ActionEvent event) {
    	Screen screen = new Screen(super.getS(),"Go back", "view/Welcome.fxml");
    	WelcomeController controller = new WelcomeController(super.getS());
    	screen.start(controller);
    }	
}
