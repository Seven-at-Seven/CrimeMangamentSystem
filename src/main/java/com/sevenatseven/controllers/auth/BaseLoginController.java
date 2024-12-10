package com.sevenatseven.controllers.auth;

import com.sevenatseven.controllers.BaseController;
import com.sevenatseven.utils.SceneManager;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class BaseLoginController extends BaseController  {
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;

    @Override
    public void goBack(ActionEvent event) throws IOException {
        SceneManager.switchScene("entry");
    };
    protected String getPassword(String dataString) {
        return dataString.split(":")[2];
    }
}

