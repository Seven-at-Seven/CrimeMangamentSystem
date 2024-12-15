package com.sevenatseven.controllers.auth;

import com.sevenatseven.controllers.auth.BaseLoginController;
import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.mainEntities.PoliceOfficer;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class OfficerLoginController extends BaseLoginController {

    private PoliceOfficer officer;


    public void goToHome(ActionEvent event) throws IOException {
        SceneManager.switchScene("officer-home");
    }


    public void handleLogin(ActionEvent event) throws IOException {
        try {
            this.officer = Shared.getStation().getOfficerByEmail(emailField.getText());
        } catch (RecordNotFoundException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email not found");
            alert.showAndWait();
            return;
        }

        if (!this.officer.getPassword().equals(passwordField.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid password");
            alert.showAndWait();
            return;
        }


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Login successful");
        alert.showAndWait();

        Shared.setCurrentOfficer(this.officer);
        SceneManager.switchScene("officer-home");
    }
}
