package com.sevenatseven;

import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.mainEntities.PoliceStation;
import com.sevenatseven.utils.Model;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    private void loadData() {
        Model model = new Model("policeStation");
        try {
            Shared.setStation(new PoliceStation(model.getRecordAt("1")));
        } catch (RecordNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
    @Override
    public void start(Stage stage) {
        loadData();
        // set the window dimensions
        stage.setTitle("Crime Management System");
        SceneManager.setMainStage(stage);

        try {
            Image icon = new Image(getClass().getResource("/assets/Images/Police_icon.png").toExternalForm());
            stage.getIcons().add(icon);
            SceneManager.switchScene("entry");
            stage.show();
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
@Override
public void stop() {
}

    public static void main(String[] args) {
        launch(args);
    }
}