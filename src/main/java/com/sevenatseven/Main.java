package com.sevenatseven;

import com.sevenatseven.utils.SceneManager;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // set the window dimensions
        stage.setTitle("Crime Management System");
        SceneManager.setMainStage(stage);

        try {
            Image icon = new Image(getClass().getResource("/assets/Images/Police_icon.png").toExternalForm());
            stage.getIcons().add(icon);
            SceneManager.switchScene("entry");
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}