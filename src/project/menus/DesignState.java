package project.menus;

import java.io.IOException;

import project.Core;

public class DesignState implements State{
	private Core main;
	
	public DesignState(Core main) {
		this.main = main;
	}

	@Override
	public void display() {
		System.out.println("=== Design Mode ===");	
		// 
		System.out.println("Would you like to load a design or a new dungeon");	
		// print input options
		System.out.println("load	new    back");	
	}

	@Override
	public void back() {
		main.setState(main.getMenu());
	}

	@Override
	public void handleInput(String input) throws IOException {
		// Ask the user for what object they want to place
		if (input.equals("back")) {
			this.back();
			return;
		} else if (input.equals("load")) {
			main.setState(main.getLoadDesign());
			return;
		} else if (input.equals("new")) {
			main.setState(main.getDesignDungeon());
			return;
		} else {
			this.help();
			return;
		}
	}

	public void help() {
		System.out.println("please chose either");
		System.out.println("load	new		back");	
	
	}
}
