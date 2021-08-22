package application;

import java.io.IOException;

import controller.Screen;
import controller.WelcomeController;
import javafx.application.Application;
import javafx.stage.Stage;
import project.Core;
import project.dungeon.Dungeon;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	
    	Core core = Core.getCore(); 
        // save some randomly generated dungeons
        for (int i = 0; i<5; i++) {
			Dungeon dungeon = new Dungeon();
			core.save(dungeon);
        }
    	// comment out the block of code you want to test
		Screen welcomeScreen = new Screen(primaryStage, "Welcome", "view/Welcome.fxml");
        WelcomeController welcomeController = new WelcomeController(primaryStage );
        welcomeScreen.start(welcomeController);
    	
    	/*
        // testing the display of a lsit of saved games
        Core core = Core.getCore(); 
        // save some randomly generated dungeons
        for (int i = 0; i<5; i++) {
			Dungeon dungeon = new Dungeon();
			core.save(dungeon);
			System.out.println(i);
        }
        PuzzleLoad pl = new PuzzleLoad(core);
    	core.setState(pl);
    	// transition the application to the lsit view
		Screen listDungeons = new Screen(primaryStage, "Welcome", "view/DungeonList.fxml");
        DungeonListController controller = new DungeonListController(primaryStage, pl, true, true, core);
        listDungeons.start(controller);
    	*/
    	
        /*
    	// test the game view
    	 // create the game to be loaded
    	Core core = Core.getCore(); // usualy create in a specific controller
    	PuzzleGame pg = new PuzzleGame(core);
    	core.setState(pg);
    	// transition the application to the gamecontroller
		Screen gameScreen = new Screen(primaryStage, "Welcome", "view/GamePlay.fxml");
        GameController gameController = new GameController(primaryStage, pg);
        gameScreen.start(gameController);
       */ 
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
