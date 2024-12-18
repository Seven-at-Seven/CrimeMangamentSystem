package com.sevenatseven.controllers.offiecer.util.display;

import com.sevenatseven.utils.SceneManager;
import com.sevenatseven.utils.Shared;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OfficerProfileController {
   @FXML
    public TextField firstName;
   @FXML
    public TextField lastName;
   @FXML
   public TextField email;
   @FXML
   public TextField phoneNumber;
   @FXML
   public TextField rank;
   @FXML
   public TextField salary;
   @FXML
   public TextField departmentId;

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
