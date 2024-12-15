package com.sevenatseven;

import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.mainEntities.Department;
import com.sevenatseven.mainEntities.PoliceOfficer;
import com.sevenatseven.mainEntities.PoliceStation;
import com.sevenatseven.utils.Model;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    private void loadData() throws IOException {
        Model model = new Model("policeStation");
        try {
            Shared.setStation(new PoliceStation(model.getRecordAt("1")));
        } catch (RecordNotFoundException e) {
            System.out.println(e);
        }
        // loop on deparments in the station
        int i = 0;
        for (Department department : Shared.getStation().getDepartments()) {
            System.out.println("Department " + i + " : " + department.getName());
            for (PoliceOfficer officer : department.getOfficers()) {
                System.out.println("Officer " + i + " : " + officer.getName());
            }
            i++;
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
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
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}