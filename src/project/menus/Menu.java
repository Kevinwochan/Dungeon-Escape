package project.menus;

import project.Core;

public class Menu implements State {
	private Core main;
	
	public Menu(Core main) {
		this.main = main;
	}
	
	public void display() {
		System.out.println("\n=== Main Menu ===");	
		System.out.println("Please Select:");
		System.out.println("Puzzle		Design		exit");
	}

	@Override
	public void back() {
		System.out.println("Thank you for playing!");
		System.out.println("Exiting game...");
		System.exit(0);
	}

	@Override
	public void handleInput(String input) {
		if (input.equals("Puzzle")) {
			main.setState(main.getPuzzle());
			return;
		} else if (input.equals("Design")) {
			main.setState(main.getDesign());
			return;
		} else if( input.equals("exit") ) {
			this.back();
			return;
		} else {
			this.help();
			return;
		}
	}

	@Override
	public void help() {
		System.out.println("Please choose either \"Design\" or \"Puzzle\"");
		
	}
	

}
