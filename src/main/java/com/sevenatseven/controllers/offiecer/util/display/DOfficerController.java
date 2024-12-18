package com.sevenatseven.controllers.offiecer.util.display;

import com.sevenatseven.mainEntities.PoliceOfficer;
import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class DOfficerController {

    public void goToCases(ActionEvent event) {
        try {
            SceneManager.switchScene("officer-cases");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void goToCriminals(ActionEvent event) {
        try {
            SceneManager.switchScene("officer-criminals");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToProfile(ActionEvent event) {
        try {
            SceneManager.switchScene("officer-profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}