package com.sevenatseven.controllers.caseUtil;

import com.sevenatseven.mainEntities.Case;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class CaseDisplayController {
    @FXML private Label caseIdLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label startDateLabel;
    @FXML private Label lastUpdateDateLabel;
    @FXML private Label crimeTypeLabel;
    @FXML private Label departmentIdLabel;
    @FXML private Label officersIdLabel;
    @FXML private Label criminalsIdLabel;

    private Case currentCase;

    public void setCaseDetails(Case caseToDisplay) {
        this.currentCase = caseToDisplay;

        caseIdLabel.setText(String.valueOf(caseToDisplay.getCaseID()));
        descriptionLabel.setText(caseToDisplay.getDescription());
        startDateLabel.setText(caseToDisplay.getStartDate().toString());
        lastUpdateDateLabel.setText(caseToDisplay.getLastUpdateDate().toString());
        crimeTypeLabel.setText(caseToDisplay.getCrimeType());
        departmentIdLabel.setText(String.valueOf(caseToDisplay.getDepartmentID()));
        officersIdLabel.setText(caseToDisplay.getOfficersIds().toString());
        criminalsIdLabel.setText(caseToDisplay.getCriminalsIds().toString());
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