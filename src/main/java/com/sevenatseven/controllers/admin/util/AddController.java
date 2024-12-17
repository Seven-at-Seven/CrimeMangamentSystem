package com.sevenatseven.controllers.admin.util;

import com.sevenatseven.exceptions.AlreadyExistException;
import com.sevenatseven.mainEntities.Admin;
import com.sevenatseven.mainEntities.Department;
import com.sevenatseven.utils.Shared;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


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
                "Case",
                "Admin"
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
        });
    }

    private void populateDetailsGrid(String entityType) {

        switch (entityType) {
            case "Department":
                addTextField("Department ID:", 0);
                addTextField("Department Name:", 1);
                addDateField("Activation Date:", 2);
                break;
            case "Police Officer":
                addTextField("ID:", 0);
                addTextField("First Name:", 1);
                addTextField("Last Name:", 2);
                addTextField("Email:", 3);
                addTextField("Password:", 4);
                addTextField("Phone Number:",5);
                addTextField("Rank:", 6);
                addTextField("Salary:", 7);
                break;
            case "Criminal":
                addTextField("ID:", 0);
                addTextField("First Name:", 1);
                addTextField("Last Name:", 2);
                addTextField("Current Cell:", 3);
                addTextField("Number of Crimes:", 4);
                addTextField("Psychological State:", 5);
                addTextField("Cases Involved In (use \",\" to separate):", 6);
                break;
            case "Case":
                addTextField("Case Name:", 0);
                addDateField("Start Date:", 1);
                addDateField("Last Update Date:", 2);
                break;
            case "Admin":
                addTextField("ID:", 0);
                addTextField("First Name:", 1);
                addTextField("Last Name:", 2);
                addTextField("Email:", 3);
                addTextField("Password:", 4);
                addTextField("Confirm Password:", 5);
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
    public void addChoiceField(String labelText, int row, String[] choices){
        Label label = new Label(labelText);
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(choices);
        DetailsGrid.add(label, 0, row);
        DetailsGrid.add(choiceBox, 1, row);
        inputFields.put(labelText, choiceBox);
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
                    Shared.getStation().AddDepartment(createDepartment());
                    break;
                case "Police Officer":
                    // Commented out method to preserve the structure
                    //Shared.policeStation.AddPoliceOfficer(createPoliceOfficer());
                    break;
                case "Criminal":
                    // Commented out method to preserve the structure
                    //Shared.policeStation.AddCriminal(createCriminal());
                    break;
                case "Case":
                    // Commented out method to preserve the structure
                    //Shared.policeStation.AddCase(createCase());
                    break;
                case "Admin":
                    // Commented out method to preserve the structure
                    Shared.getStation().AddAdmin(createAdmin());
                    break;
            }
            showAlert(Alert.AlertType.INFORMATION, "Success", "Entry added successfully!");
            return true;
        }
        catch (NullPointerException e){
            return false;
        }
        catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add entry: " + e.getMessage());
            return false;
        }
    }

    // Helper methods to create entities (these would need to match your model classes)
    private Department createDepartment() {
        TextField idField = (TextField) inputFields.get("Department ID:");
        TextField nameField = (TextField) inputFields.get("Department Name:");
        DatePicker activationDatePicker = (DatePicker) inputFields.get("Activation Date:");
        try {
            return new Department(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    activationDatePicker.getValue()
            );
        }
        catch (NumberFormatException e){
            showAlert(Alert.AlertType.ERROR, "Error", "Department ID must be a number");
            return null;
        }
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
    private Admin createAdmin() {
        TextField idField = (TextField) inputFields.get("ID:");
        TextField firstNameField = (TextField) inputFields.get("First Name:");
        TextField lastNameField = (TextField) inputFields.get("Last Name:");
        TextField emailField = (TextField) inputFields.get("Email:");
        TextField passwordField = (TextField) inputFields.get("Password:");
        TextField confirmPasswordField = (TextField) inputFields.get("Confirm Password:");
        if(!validatingID(idField.getText(),"ad")){
            return null;
        }
        if(!passwordField.getText().equals(confirmPasswordField.getText())){
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match");
            return null;
        }
        return new Admin(
                idField.getText(),
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                passwordField.getText()
        );
    }
    private void clearForm() {
        EntityAddBox.setValue("");
        DetailsGrid.getChildren().clear();
        inputFields.clear();
    }


    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public boolean validatingID(String pID,String pref){
        if(pID.length() < 3){
            showAlert(Alert.AlertType.ERROR, "Error", "ID must be at least 3 characters long");
            return false;
        }
        String id = pID.substring(2);
        if(!pID.contains("ad"))
        {
            showAlert(Alert.AlertType.ERROR, "Error", "ID must start with '" + pref + "'");
            return false;
        }
        else if(!id.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Error", "ID must contain only numbers after '" + pref + "'");
            return false;
        }
        return true;
    }
}