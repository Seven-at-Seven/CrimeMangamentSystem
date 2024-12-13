package com.sevenatseven.controllers.admin.util;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddController {
    @FXML
    public VBox Layout;
    @FXML
    public ChoiceBox<String> EntityAddBox;
    @FXML
    public Button AddButton;
    @FXML
    public GridPane DetailsGrid;

    public void initialize() {
        EntityAddBox.getItems().addAll(
                "Department",
                "Police Officer",
                "Criminal",
                "Case"
        );
        DetailsGrid.setHgap(10);
        DetailsGrid.setVgap(10);

        EntityAddBox.setOnAction(event -> {
            DetailsGrid.getChildren().clear();
            String selectedChoice = EntityAddBox.getValue();

            switch (selectedChoice) {
                case "Department":
                    getDepartmentGrid(DetailsGrid);
                    break;
                case "Police Officer":
                    getPoliceOfficerGrid(DetailsGrid);
                    break;
                case "Case":
                    getCaseGrid(DetailsGrid);
                    break;
                case "Criminal":
                    getCriminalGrid(DetailsGrid);
                    break;
            }
        });

        AddButton.setOnAction(event -> {
            if (validateInputs()) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Entry added successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please correct the highlighted fields.");
            }
        });
    }

    private GridPane getDepartmentGrid(GridPane dataGrid) {
        dataGrid.add(new Label("Department Name:"), 0, 0);
        dataGrid.add(new TextField(), 1, 0);

        dataGrid.add(new Label("Department Activation Date:"), 0, 1);
        dataGrid.add(new TextField(), 1, 1);

        return dataGrid;
    }

    private GridPane getPoliceOfficerGrid(GridPane dataGrid) {
        dataGrid.add(new Label("Police Officer First Name:"), 0, 0);
        dataGrid.add(new TextField(), 1, 0);

        dataGrid.add(new Label("Police Officer Last Name:"), 0, 1);
        dataGrid.add(new TextField(), 1, 1);

        dataGrid.add(new Label("Police Officer Salary:"), 0, 5);
        dataGrid.add(new TextField(), 1, 5);

        dataGrid.add(new Label("Police Officer Phone:"), 0, 2);
        dataGrid.add(new TextField(), 1, 2);

        dataGrid.add(new Label("Police Officer Email:"), 0, 3);
        dataGrid.add(new TextField(), 1, 3);

        dataGrid.add(new Label("Police Officer Rank:"), 0, 4);
        dataGrid.add(new TextField(), 1, 4);

        return dataGrid;
    }

    private GridPane getCriminalGrid(GridPane dataGrid) {
        dataGrid.add(new Label("Criminal First Name:"), 0, 0);
        dataGrid.add(new TextField(), 1, 0);

        dataGrid.add(new Label("Criminal Last Name:"), 0, 1);
        dataGrid.add(new TextField(), 1, 1);

        dataGrid.add(new Label("Criminal Current Cell:"), 0, 2);
        dataGrid.add(new TextField(), 1, 2);

        dataGrid.add(new Label("Criminal Number of Crimes:"), 0, 3);
        dataGrid.add(new TextField(), 1, 3);

        dataGrid.add(new Label("Criminal Psychological State:"), 0, 4);
        dataGrid.add(new TextField(), 1, 4);

        return dataGrid;
    }

    private GridPane getCaseGrid(GridPane dataGrid) {
        dataGrid.add(new Label("Case Name:"), 0, 0);
        dataGrid.add(new TextField(), 1, 0);

        dataGrid.add(new Label("Case Start Date:"), 0, 1);
        dataGrid.add(new TextField(), 1, 1);

        dataGrid.add(new Label("Case Last Update Date:"), 0, 2);
        dataGrid.add(new TextField(), 1, 2);

        return dataGrid;
    }

    private boolean validateInputs() {
        boolean isValid = true;

        for (int i = 0; i < DetailsGrid.getChildren().size(); i++) {
            if (DetailsGrid.getChildren().get(i) instanceof TextField) {
                TextField textField = (TextField) DetailsGrid.getChildren().get(i);
                if (!isFieldValid(textField)) {
                    textField.setStyle("-fx-border-color: red; -fx-border-width: 2;");
                    isValid = false;
                } else {
                    textField.setStyle(null);
                }
            }
        }
        return isValid;
    }

    private boolean isFieldValid(TextField textField) {
        String text = textField.getText().trim();
        return !text.isEmpty();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}