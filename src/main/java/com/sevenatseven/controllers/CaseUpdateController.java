package com.sevenatseven.controllers;

import com.sevenatseven.mainEntities.Case;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CaseUpdateController {
    @FXML private Label caseIdLabel;
    @FXML private TextField descriptionField;
    @FXML private TextField crimeTypeField;
    @FXML private TextField departmentIdField;

    private Case currentCase;

    public void setCaseDetails(Case caseToUpdate) {
        this.currentCase = caseToUpdate;

        caseIdLabel.setText(String.valueOf(caseToUpdate.getCaseID()));
        descriptionField.setText(caseToUpdate.getDescription());
        crimeTypeField.setText(caseToUpdate.getCrimeType());
        departmentIdField.setText(String.valueOf(caseToUpdate.getDepartmentID()));
    }

    @FXML
    public void saveChanges() {
        try {
            String newDescription = descriptionField.getText();
            String newCrimeType = crimeTypeField.getText();
            int newDepartmentID = Integer.parseInt(departmentIdField.getText());


            currentCase.editCaseDetails(
                    newDescription,
                    newCrimeType,
                    newDepartmentID
            );

            Shared.getStation()
            .getDepartment(currentCase.getDepartmentID())
            .setCase(currentCase);



            showAlert("Case Updated", "The case has been successfully updated.");

            backToCasesList();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please check your input values.");
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void backToCasesList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CasesListView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) caseIdLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Case Management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}