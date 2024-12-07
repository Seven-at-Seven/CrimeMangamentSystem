package com.sevenatseven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        try {
            Image icon = new Image(getClass().getResource("/assets/Images/Police_icon.png").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + e.getMessage());
        }

        Image icon = new Image(getClass().getResource("/assets/Images/Police_icon.png").toExternalForm());
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 1080.0, 700.0);
        stage.setTitle("Crime Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}