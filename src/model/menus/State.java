package model.menus;

import java.io.IOException;

/**
  * Interface for how the game works under certain states
  * @author kevinwochan
  */

public abstract interface State {
	// Displays the current state in STDOUT or in the UI 
	public void display();
	// when a the user wants to exit the mode
	void back();
	// Handle input depending on the state
	void handleInput(String input) throws IOException;
	// displays a list of possible actions the user can do
	void help();
}
