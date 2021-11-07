package model.menus;

import model.Core;
import model.dungeon.Dungeon;

public class PuzzleLoad implements State{
	private Core main;

	public PuzzleLoad(Core main) {
		this.main = main;
	}
	
	@Override
	public void display() {
		System.out.println("\n=== Load Puzzle Mode ===");	
		if (main.getSaves().size() == 0) {
			System.out.println("No saves found");
			this.back();
			return;
		}
		System.out.println("Please pick a saved puzzle");	
		for (Dungeon d: main.getSaves()) {
			System.out.println(main.getSaves().indexOf(d));
			System.out.println(d);
		}
	}
	
	@Override
	public void back() {
		main.setState(main.getMenu());
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
		Dungeon dungeon = main.load(index);
		main.setState(new PuzzleGame(main, dungeon));
	}

	public void deleteDungeon(int index) {
		main.delete(index);
	}
	
	@Override
	public void help() {
		System.out.println("please pick a number between " + main.getSaves().size() + " and 0");
		System.out.println("e.g \"load 2 \" or \"delete 2\"");
	}

}
