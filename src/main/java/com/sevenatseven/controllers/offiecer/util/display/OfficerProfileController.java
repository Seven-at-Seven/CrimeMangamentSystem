package com.sevenatseven.controllers.offiecer.util.display;

import com.sevenatseven.utils.SceneManager;
import javafx.event.ActionEvent;

public class OfficerProfileController {
    public void goToHome(ActionEvent event)  {
        try {
            SceneManager.switchScene("officer-home");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
}
