package com.sevenatseven.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static final Map<String, String> SCENES = new HashMap<>();
    private static Stage mainStage;

    static {
        // Initialize scenes map with FXML paths
        SCENES.put("entry", "/com/sevenatseven/FXMLS/Entry.fxml");
        SCENES.put("admin-login", "/com/sevenatseven/FXMLS/auth/AdminLogin.fxml");
        SCENES.put("officer-login", "/com/sevenatseven/FXMLS/auth/OfficerLogin.fxml");
        SCENES.put("officer-home", "/com/sevenatseven/FXMLS/DOfficer.fxml");

    }

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void switchScene(String sceneName) throws IOException {
        if (!SCENES.containsKey(sceneName)) {
            throw new IllegalArgumentException("Scene " + sceneName + " not found!");
        }

        String fxmlPath = SCENES.get(sceneName);
        Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
        Scene scene = new Scene(root);

        if (mainStage == null) {
            throw new IllegalStateException("Main stage not set. Call setMainStage first!");
        }

        mainStage.setScene(scene);
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
