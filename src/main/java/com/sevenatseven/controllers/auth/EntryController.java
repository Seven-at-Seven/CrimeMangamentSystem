package com.sevenatseven.controllers.auth;

import com.sevenatseven.utils.SceneManager;
import java.io.IOException;

public class EntryController {
    
    public void goToAdminLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        SceneManager.switchScene("admin-login");
    }
    public void goToOfficerLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        SceneManager.switchScene("officer-login");
    }
}
