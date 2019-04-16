package project.menus;

import project.Core;
import project.dungeon.Dungeon;

public class DesignLoad implements State{
	private Core main;
	
	public DesignLoad(Core main) {
		this.main = main;
	}

	@Override
	public void display() {
		System.out.println("=== Design Mode ===");	
		// print input options
		if (main.getSaves().size() == 0) {
			System.out.println("No saves found");
			this.back();
			return;
		}
		System.out.println("Please pick a saved puzzle");	
		for (Dungeon d: main.getSaves()) {
			System.out.println(main.getSaves().indexOf(d));
			System.out.println(d + "\n\n\n");
		}
	}

	@Override
	public void back() {
		// going back from the design load menu goes back to design
		main.setState(main.getDesign());
	}

	@Override
	public void handleInput(String input) {
		int index = Integer.valueOf(input);
		int maxIndex =  main.getSaves().size();
		if (maxIndex == 0) {
			System.out.println("No saves found");
			return;
		}
		if (index < maxIndex && index >= 0) {
			// load the chosen save
			loadDungeon(index);
		}else {
			this.help();
		}
	}
	
	public void loadDungeon(int index) {
		main.load(index);
	}

	public void deleteDungeon(int index) {
		main.delete(index);
	}

	public void help() {
		System.out.println("please pick a number between " + main.getSaves().size() + " and 0");
		System.out.println("e.g \"load 2 \" or \"delete 2\"");
	}
}
