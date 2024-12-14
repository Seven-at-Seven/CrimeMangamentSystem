package com.sevenatseven.controllers.auth;

import com.sevenatseven.mainEntities.Admin;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminLoginController extends BaseLoginController {

  private Admin admin;

  public void goToHome(ActionEvent event) throws IOException {
    SceneManager.switchScene("admin-home");
  }

  public void handleLogin(ActionEvent event) throws IOException {
    this.admin = Shared.getStation().getAdminByEmail(emailField.getText());

    if (admin == null) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Email not found");
      alert.showAndWait();
      return;
    }

    if (!this.admin.getPassword().equals(passwordField.getText())) {
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

    Shared.setCurrentAdmin(this.admin);
    SceneManager.switchScene("entry");
  }
}
