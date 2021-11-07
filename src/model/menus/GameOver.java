package model.menus;

import model.Core;

public class GameOver implements State {
	Core main;
	
	public GameOver(Core main) {
		this.main = main;
	}

	@Override
	public void display() {
		System.out.println("=== Game Over ===");
		help();
	}

	@Override
	public void back() {
		main.setState(main.getMenu());
	}

	@Override
	public void handleInput(String input) {
		if (input.equals("menu")) {
			this.back();
		} else if (input.equals("exit")){
			System.exit(0);
		} else if (input.equals("help")) {
			this.help();
		} else {
			this.help();
			return;
		}
	}
	
	@Override
	public void help() {
		System.out.println("Please Select:");
		System.out.println("menu    exit");
	}

}
