package com.sevenatseven.controllers.admin;

import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminController {
    @FXML
    private Label WelcomingLabel;
    public void initialize() {
        // will be changed when everything is implemented (policeStation.current_user)
        WelcomingLabel.setText("Hello " + "Bal7a" + "!");
    }
    @FXML
    public void SwitchToAddScene(ActionEvent event) throws IOException {
        SceneManager.switchScene("add-entity");
    }
    @FXML
    public void SwitchToUpdateScene(ActionEvent event) throws IOException {
        SceneManager.switchScene("update-officer");
    }
}
