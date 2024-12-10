package com.sevenatseven.controllers.auth;

import com.sevenatseven.utils.Model;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class OfficerLoginController extends BaseLoginController {

    private String officerString;


    public void goToHome(ActionEvent event) throws IOException {
        SceneManager.switchScene("officer-home");
    }


    public void handleLogin(ActionEvent event) throws IOException {
        Model officerModel = new Model("officers");
        officerModel.getRecordByEmail(emailField.getText());
        if (officerModel.getRecordByEmail(emailField.getText()) == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email not found");
            alert.showAndWait();
            return;
        }
        this.officerString = officerModel.getRecordByEmail(emailField.getText());

        if (!this.getPassword(this.officerString).equals(passwordField.getText())) {
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

        Shared.setOfficerString(this.officerString);
        SceneManager.switchScene("entry");
    }
}
