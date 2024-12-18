package com.sevenatseven;

import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.mainEntities.*;
import com.sevenatseven.utils.Model;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import java.util.ArrayList;

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
        // debug
        System.out.println("Admins: ");
        ArrayList<Admin> admins = Shared.getStation().getAdmins();
        for(Admin admin : admins) {
            System.out.println(admin.getName());
        }
        System.out.println("Deps: ");
        ArrayList<Department> departments = Shared.getStation().getDepartments();
        for(Department department : departments){
            System.out.println("Officers of dep" + department.getId());
            ArrayList<PoliceOfficer> officers = department.getOfficers();
            for (PoliceOfficer officer : officers){
                System.out.println(officer.getName());
            }
            System.out.println("Cases of dep" + department.getId());
            ArrayList<com.sevenatseven.mainEntities.Case> cases = department.getCases();
            for (com.sevenatseven.mainEntities.Case crimeCase : cases){
                System.out.println(crimeCase.getDescription());
                for(Criminal criminal : crimeCase.getCriminals()){
                    System.out.println(criminal.getDangerLevel());
                }
            }
        }
    }
}