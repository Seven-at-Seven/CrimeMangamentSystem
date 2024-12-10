package com.sevenatseven.controllers.auth;

import com.sevenatseven.utils.Model;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminLoginController extends BaseLoginController {

    private String adminString;

    public void goToHome(ActionEvent event) throws IOException {
        SceneManager.switchScene("admin-home");
    }

    public void handleLogin(ActionEvent event) throws IOException {
        Model adminModel = new Model("admin");
        adminModel.getRecordByEmail(emailField.getText());
        if (adminModel.getRecordByEmail(emailField.getText()) == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email not found");
            alert.showAndWait();
            return;
        }
        this.adminString = adminModel.getRecordByEmail(emailField.getText());

        if (!this.getPassword(this.adminString).equals(passwordField.getText())) {
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

        Shared.setOfficerString(this.adminString);
        SceneManager.switchScene("entry");
    }
}

