package com.sevenatseven.controllers.offiecer.util.display;

import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OfficerProfileController {
    @FXML
    public Label firstName;
    @FXML
    public Label lastName;
    @FXML
    public Label email;
    @FXML
    public Label phoneNumber;
    @FXML
    public Label rank;
    @FXML
    public Label salary;
    @FXML
    public Label departmentId;
    @FXML
public void initialize() {
email.setText(Shared.getCurrentOfficer().getEmail());
firstName.setText(Shared.getCurrentOfficer().getFirstName());
lastName.setText(Shared.getCurrentOfficer().getLastName());
phoneNumber.setText(Shared.getCurrentOfficer().getPhoneNumber());
rank.setText(Shared.getCurrentOfficer().getRank());
salary.setText(String.valueOf(Shared.getCurrentOfficer().getSalary()));
departmentId.setText(String.valueOf(Shared.getCurrentOfficer().getDepartment().getId()));
}
    public void goToHome(ActionEvent event)  {
        try {
            SceneManager.switchScene("officer-home");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
}
