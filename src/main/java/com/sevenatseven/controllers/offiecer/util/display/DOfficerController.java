package com.sevenatseven.controllers.offiecer.util.display;

import com.sevenatseven.mainEntities.PoliceOfficer;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class DOfficerController {
    @FXML
    public Label welcomeLabel;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome Officer " + "Omar Hasan");
    }

    @FXML
    public void displayCases(ActionEvent event) {
        try {
            SceneManager.switchScene("cases-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayCriminals(ActionEvent event) {
        try {
            SceneManager.switchScene("criminals-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}