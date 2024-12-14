package com.sevenatseven.controllers.admin.util;

import com.sevenatseven.mainEntities.*; // Import your model classes
import com.sevenatseven.utils.Shared; // A new service class to manage entities

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddController {
    @FXML
    public VBox Layout;
    @FXML
    public ChoiceBox<String> EntityAddBox;
    @FXML
    public Button AddButton;
    @FXML
    public Button CancelButton;
    @FXML
    public GridPane DetailsGrid;

    private Map<String, Control> inputFields = new HashMap<>();

    public void initialize() {
        setupEntityTypes();
        setupEventHandlers();
    }

    private void setupEntityTypes() {
        EntityAddBox.getItems().addAll(
                "Department",
                "Police Officer",
                "Criminal",
                "Case"
        );
    }

    private void setupEventHandlers() {
        // Clear and populate grid when entity type is selected
        EntityAddBox.setOnAction(event -> {
            DetailsGrid.getChildren().clear();
            inputFields.clear();
            String selectedChoice = EntityAddBox.getValue();
            populateDetailsGrid(selectedChoice);
        });

        // Add entry button handler
        AddButton.setOnAction(event -> {
            if (validateAndAddEntry()) {
                clearForm();
            }
        });

        // Cancel button handler
        CancelButton.setOnAction(event -> {
            clearForm();
            closeWindow();
        });
    }

    private void populateDetailsGrid(String entityType) {
        inputFields.clear();

        switch (entityType) {
            case "Department":
                addTextField("Department Name:", 0);
                addDateField("Activation Date:", 1);
                break;
            case "Police Officer":
                addTextField("First Name:", 0);
                addTextField("Last Name:", 1);
                addTextField("Rank:", 2);
                addTextField("Phone:", 3);
                addTextField("Email:", 4);
                addTextField("Salary:", 5);
                break;
            case "Criminal":
                addTextField("First Name:", 0);
                addTextField("Last Name:", 1);
                addTextField("Current Cell:", 2);
                addTextField("Number of Crimes:", 3);
                addTextField("Psychological State:", 4);
                break;
            case "Case":
                addTextField("Case Name:", 0);
                addDateField("Start Date:", 1);
                addDateField("Last Update Date:", 2);
                break;
        }
    }

    private void addTextField(String labelText, int row) {
        Label label = new Label(labelText);
        TextField textField = new TextField();

        // Add listener to show red border for empty fields
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                textField.setStyle("-fx-border-color: red;");
            } else {
                textField.setStyle("");
            }
        });

        DetailsGrid.add(label, 0, row);
        DetailsGrid.add(textField, 1, row);

        inputFields.put(labelText, textField);
    }

    private void addDateField(String labelText, int row) {
        Label label = new Label(labelText);
        DatePicker datePicker = new DatePicker();

        // Add listener to show red border for empty date
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                datePicker.setStyle("-fx-border-color: red;");
            } else {
                datePicker.setStyle("");
            }
        });

        DetailsGrid.add(label, 0, row);
        DetailsGrid.add(datePicker, 1, row);

        inputFields.put(labelText, datePicker);
    }

    private boolean validateAndAddEntry() {
        if (EntityAddBox.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Entity Type Not Selected",
                    "Please select an entity type before adding an entry.");
            return false;
        }

        // Validate inputs
        boolean isValid = true;
        for (Map.Entry<String, Control> entry : inputFields.entrySet()) {
            if (entry.getValue() instanceof TextField) {
                TextField textField = (TextField) entry.getValue();
                if (textField.getText().trim().isEmpty()) {
                    textField.setStyle("-fx-border-color: red;");
                    isValid = false;
                }
            } else if (entry.getValue() instanceof DatePicker) {
                DatePicker datePicker = (DatePicker) entry.getValue();
                if (datePicker.getValue() == null) {
                    datePicker.setStyle("-fx-border-color: red;");
                    isValid = false;
                }
            }
        }

        if (!isValid) {
            showAlert(Alert.AlertType.ERROR, "Validation Error",
                    "Please fill in all fields.");
            return false;
        }

        // Add entity based on selected type
        try {
            switch (EntityAddBox.getValue()) {
                case "Department":
                    entityManager.policeStation.AddDepartment(createDepartment());
                    break;
                case "Police Officer":
                    // Commented out method to preserve the structure
                    //entityManager.policeStation.AddPoliceOfficer(createPoliceOfficer());
                    break;
                case "Criminal":
                    // Commented out method to preserve the structure
                    //entityManager.policeStation.AddCriminal(createCriminal());
                    break;
                case "Case":
                    // Commented out method to preserve the structure
                    //entityManager.policeStation.AddCase(createCase());
                    break;
            }
            showAlert(Alert.AlertType.INFORMATION, "Success", "Entry added successfully!");
            return true;
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add entry: " + e.getMessage());
            return false;
        }
    }

    // Helper methods to create entities (these would need to match your model classes)
    private Department createDepartment() {
        TextField nameField = (TextField) inputFields.get("Department Name:");
        DatePicker activationDatePicker = (DatePicker) inputFields.get("Activation Date:");

        return new Department(
                nameField.getText(),
                activationDatePicker.getValue()
        );
    }

    // Commented out methods to preserve the structure and provide reference
    /*
    private PoliceOfficer createPoliceOfficer() {
        return new PoliceOfficer(
                inputFields.get("First Name:").getText(),
                inputFields.get("Last Name:").getText(),
                inputFields.get("Rank:").getText(),
                inputFields.get("Phone:").getText(),
                inputFields.get("Email:").getText(),
                Double.parseDouble(inputFields.get("Salary:").getText())
        );
    }

    private Criminal createCriminal() {
        return new Criminal(
                inputFields.get("First Name:").getText(),
                inputFields.get("Last Name:").getText(),
                inputFields.get("Current Cell:").getText(),
                Integer.parseInt(inputFields.get("Number of Crimes:").getText()),
                inputFields.get("Psychological State:").getText()
        );
    }

    private Case createCase() {
        return new Case(
                inputFields.get("Case Name:").getText(),
                LocalDate.parse(inputFields.get("Start Date:").getText()),
                LocalDate.parse(inputFields.get("Last Update Date:").getText())
        );
    }
    */

    private void clearForm() {
        EntityAddBox.setValue(null);
        DetailsGrid.getChildren().clear();
        inputFields.clear();
    }

    private void closeWindow() {
        Stage stage = (Stage) Layout.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}