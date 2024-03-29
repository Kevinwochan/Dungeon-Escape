package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Screen {
    private Stage stage;
    private String screenTitle;
    private String fxmlFile;
    private FXMLLoader fxmlLoader;

    public Screen(Stage stage, String screenTitle, String fxmlFile) {
        this.stage = stage;
        this.screenTitle = screenTitle;
        this.fxmlFile = fxmlFile;
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    }

    public Stage getStage() {
        return stage;
    }

    public void start(Controller c) {
        stage.setTitle(screenTitle);
        fxmlLoader.setController(c);
        try {
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
