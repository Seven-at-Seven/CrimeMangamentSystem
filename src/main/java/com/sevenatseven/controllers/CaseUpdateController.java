package com.sevenatseven.controllers;

import Classes.Cases;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CaseUpdateController {
    @FXML private Label caseIdLabel;
    @FXML private TextField descriptionField;
    @FXML private TextField crimeTypeField;
    @FXML private TextField departmentIdField;
    @FXML private TextField officersIdField;
    @FXML private TextField criminalsIdField;

    private Cases currentCase;

    public void setCaseDetails(Cases caseToUpdate) {
        this.currentCase = caseToUpdate;

        caseIdLabel.setText(String.valueOf(caseToUpdate.getCaseID()));
        descriptionField.setText(caseToUpdate.getDescription());
        crimeTypeField.setText(caseToUpdate.getCrimeType());
        departmentIdField.setText(String.valueOf(caseToUpdate.getDepartmentID()));
        officersIdField.setText(caseToUpdate.getOfficersID().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
        criminalsIdField.setText(caseToUpdate.getCriminalsID().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    @FXML
    public void saveChanges() {
        try {
            String newDescription = descriptionField.getText();
            String newCrimeType = crimeTypeField.getText();
            int newDepartmentID = Integer.parseInt(departmentIdField.getText());

            ArrayList<Integer> newOfficersID = parseCommaSeparatedIds(officersIdField.getText());
            ArrayList<Integer> newCriminalsID = parseCommaSeparatedIds(criminalsIdField.getText());


            currentCase.editCaseDetails(
                    newDescription,
                    newCrimeType,
                    newDepartmentID,
                    newOfficersID,
                    newCriminalsID
            );


            Cases.updateCase(currentCase);


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

    private ArrayList<Integer> parseCommaSeparatedIds(String commaSeparatedIds) {
        if (commaSeparatedIds.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(commaSeparatedIds.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}