package project.menus;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import project.Core;
import project.dungeon.Dungeon;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.factories.DungeonFactory;

public class DesignDungeon implements State{
	private Core main;
	private Dungeon dungeon;
	private DungeonFactory factory;
	private Pattern format;

	public DesignDungeon(Core main) throws IOException {
		this.main = main;
		this.dungeon = new Dungeon();
		this.factory = new DungeonFactory();
		this.format = Pattern.compile("\\s+ (\\d+,\\d+)");
	}
	public DesignDungeon(Core main, Dungeon dungeon) throws IOException {
		this.main = main;
		this.dungeon = dungeon;
		this.factory = new DungeonFactory();
		this.format = Pattern.compile("\\s+ (\\d+,\\d+)");
	}
	@Override
	public void display() {
		System.out.println("=== Design Mode ===");	
		// display the state of the dungeon
		System.out.println(dungeon);	
		// print our the input options
		this.help();
	}

	@Override
	public void back() {
		main.setState(main.getLoadDesign());
	}

	@Override
	public void handleInput(String input) throws IOException {
		// Ask the user for what object they want to place
		Matcher matcher = format.matcher(input);
		if (input.equals("back")) {
			this.back();
			return;
		} else if (input.equals("help")) {
			this.help();
			return;
		} else if (input.equals("list objects")) {
			this.listObjects();
			return;
		} else if (input.equals("save")) {
			saveDungeon();
			return;
		} else if (matcher.matches()) {
				String objectType = matcher.group(0);
				String name = matcher.group(1);
				int x = Integer.valueOf(matcher.group(2));
				int y = Integer.valueOf(matcher.group(3));
				MapCoordinate co = new MapCoordinate(x,y);
				dungeon.add(objectType,name,co);
		} else {
			this.help();
			return;
		}
		
	}

	public void help() {
		System.out.println("add to the dungeon using the format");	
		System.out.println("objectId x,y");	
		System.out.println("e.g 5 (5,8)");	
		System.out.println("for a full list of object ID's use \"list objects\"");	
	}

	public void listObjects() {
		factory.listObjects();
	}
	
	public void saveDungeon() {
		main.save(dungeon);
	}
}

