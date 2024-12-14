package com.sevenatseven.controllers.offiecer.util.display.info;

import com.sevenatseven.mainEntities.PoliceOfficer;
import com.sevenatseven.utils.Shared;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OfficerInfoController {

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label rankLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label departmentIdLabel;

    @FXML
    public void initialize() {
        PoliceOfficer loggedOfficer = getLoggedOfficer();
        if (loggedOfficer != null) {
            firstNameLabel.setText(loggedOfficer.getFirstName());
            lastNameLabel.setText(loggedOfficer.getLastName());
            emailLabel.setText(loggedOfficer.getEmail());
            phoneNumberLabel.setText(loggedOfficer.getPhoneNumber());
            rankLabel.setText(loggedOfficer.getRank());
            salaryLabel.setText(String.valueOf(loggedOfficer.getSalary()));
            departmentIdLabel.setText(loggedOfficer.getDepartment().id);
        }
    }

    private PoliceOfficer getLoggedOfficer() {
        String officerData = Shared.getOfficerString();
        if (officerData != null && !officerData.isEmpty()) {
            return new PoliceOfficer(officerData);
        }
        return null;
    }
}