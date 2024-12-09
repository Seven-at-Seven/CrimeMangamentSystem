package com.sevenatseven.controllers.auth;

import com.sevenatseven.utils.SceneManager;
import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminLoginController {
    
    public void goToHome(ActionEvent event) throws IOException {
        SceneManager.switchScene("entry");
    }
}
