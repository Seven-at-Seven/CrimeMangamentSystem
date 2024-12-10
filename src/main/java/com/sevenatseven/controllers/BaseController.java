package com.sevenatseven.controllers;

import com.sevenatseven.utils.SceneManager;
import java.io.IOException;
import javafx.event.ActionEvent;

public class BaseController {
    public void signOut(ActionEvent event) throws IOException {
        SceneManager.switchScene("entry");
        // singout logic
    }
    public void goBack(ActionEvent event) throws IOException { };
}