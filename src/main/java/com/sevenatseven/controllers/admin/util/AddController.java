package com.sevenatseven.controllers.admin.util;

import com.sevenatseven.exceptions.AlreadyExistException;
import com.sevenatseven.exceptions.DoesNotExistException;
import com.sevenatseven.mainEntities.*;
import com.sevenatseven.sideentities.Crime;
import com.sevenatseven.sideentities.CrimeType;
import com.sevenatseven.utils.Shared;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;


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
                addTextField("Phone Number:",3);
                addTextField("Rank:", 4);
                addTextField("Salary:", 5);
                addTextField("Email:", 6);
                addTextField("Password:", 7);
                addTextField("Confirm Password:",8);
                addTextField("Department ID:", 9);
                break;
            case "Criminal":
                addTextField("First Name:", 0);
                addTextField("Last Name:", 1);
                addTextField("Current location:", 2);
                addTextField("Psychological State:", 3);
                addTextField("Cases Involved In IDs (use \",\" to separate):", 4);
                break;
            case "Case":
                addTextField("Case ID:", 0);
                addDateField("Start Date:", 1);
                addTextField("Department ID:", 2);
                addTextField("Officers handled IDs (use \",\" to separate):", 3);
                addChoiceField("Crime type:", 4, new String[] {"THEFT",
                        "ASSAULT",
                        "FRAUD",
                        "MURDER",
                        "VANDALISM",
                        "ROBBERY"});
                addTextField("Description:",5);
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
                    Pair<PoliceOfficer,Integer> policeAndDepID = createPoliceOfficer();
                    Shared.getStation().getDepartment(policeAndDepID.getValue()).addOfficer(policeAndDepID.getKey());
                    break;
                case "Criminal":
                    createCriminal();
                    break;
                case "Case":
                    createCase();
                    break;
                case "Admin":
                    Shared.getStation().AddAdmin(createAdmin());
                    break;
            }
            showAlert(Alert.AlertType.INFORMATION, "Success", "Entry added successfully!");
            return true;
        }
        catch (NullPointerException e) {
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
    private Pair<PoliceOfficer,Integer> createPoliceOfficer() {
        TextField idField = (TextField) inputFields.get("ID:");
        TextField firstNameField = (TextField) inputFields.get("First Name:");
        TextField lastNameField = (TextField) inputFields.get("Last Name:");
        TextField salaryField = (TextField) inputFields.get("Salary:");
        TextField emailField = (TextField) inputFields.get("Email:");
        TextField phoneField = (TextField) inputFields.get("Phone Number:");
        TextField rankField = (TextField) inputFields.get("Rank:");
        TextField passwordField = (TextField) inputFields.get("Password:");
        TextField confirmPasswordField = (TextField) inputFields.get("Confirm Password:");
        TextField depIDField = (TextField) inputFields.get("Department ID:");
        if(!validatingID(idField.getText(),"off")){
            return null;
        }
        if(salaryField.getText().charAt(0) == '0'){
            showAlert(Alert.AlertType.ERROR, "Error", "salary must not begin with 0");
        }
        if(!passwordField.getText().equals(confirmPasswordField.getText())){
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match");
            return null;
        }
        try {
            return new Pair<PoliceOfficer,Integer>(new PoliceOfficer(
                    idField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    rankField.getText(),
                    phoneField.getText(),
                    Integer.parseInt(salaryField.getText()),
                    Integer.parseInt(depIDField.getText())
            ),Integer.parseInt(depIDField.getText()));
        }
        catch (NumberFormatException e)
        {
            showAlert(Alert.AlertType.ERROR, "Error", "Department ID and salary must be numbers");
            return null;
        }
    }

    private void createCriminal() throws NullPointerException {
        TextField firstNameField = (TextField) inputFields.get("First Name:");
        TextField lastNameField = (TextField) inputFields.get("Last Name:");
        TextField locationField = (TextField) inputFields.get("Current location:");
        TextField profileField = (TextField) inputFields.get("Psychological State:");
        String casesFiled = ((TextField) inputFields.get("Cases Involved In IDs (use \",\" to separate):")).getText();
        boolean added = true;
        try {
            Map<Integer, Boolean> casesIds = new HashMap<>();
            for(int i = 0; i < casesFiled.split(",").length; i++){
                casesIds.put(Integer.parseInt(casesFiled.split(",")[i]),false);
            }
            Criminal criminal = new Criminal(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    locationField.getText(),
                    profileField.getText()
            );
            for(Department department : Shared.getStation().getDepartments()){
                for (Case c : department.getCases()){
                        if(casesIds.containsKey(c.getCaseID())) {
                            if(!casesIds.get(c.getCaseID()))
                            {
                                c.addCriminal(criminal);
                                criminal.addCrime(new Crime(c.getCrimeType(), c.getStartDate()));
                                casesIds.put(c.getCaseID(), true);
                            }
                        }
                }
            }
            for (Map.Entry<Integer, Boolean> entry : casesIds.entrySet()) {
                if (!entry.getValue()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Criminal was not added in Case: " + entry.getKey()
                            + " because it was not found");
                    added = false;
                }
            }
        }
        catch (NumberFormatException e){
            showAlert(Alert.AlertType.ERROR,"Error","Case IDs' must be numbers");
            added = false;
        }
        catch (Exception e){
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to create criminal: " + e.getMessage());
            added = false;
        }
        if(!added) throw new NullPointerException();
    }
    private void createCase() throws NullPointerException {
        TextField caseIDField = (TextField) inputFields.get("Case ID:");
        DatePicker startDatePicker = (DatePicker) inputFields.get("Start Date:");
        TextField depIDField = (TextField) inputFields.get("Department ID:");
        TextField officersField = (TextField) inputFields.get("Officers handled IDs (use \",\" to separate):");
        TextField descriptionField = (TextField) inputFields.get("Description:");
        CrimeType crimeType = CrimeType.valueOf(
                ((ChoiceBox<String>) inputFields.get("Crime type:"))
                        .getValue().toUpperCase());
        boolean added = true;
        try {
            Department department = Shared.getStation().getDepartment(Integer.parseInt(depIDField.getText()));
            int caseID = Integer.parseInt(caseIDField.getText());
            for(String officer : officersField.getText().split(",")){
                if(!officer.matches("off\\d+")){
                    showAlert(Alert.AlertType.ERROR, "Error", "Officer ID must be like 'off33'");
                    added = false;
                }
                if(!department.officerExist(officer)){
                    showAlert(Alert.AlertType.ERROR, "Error", "Officer with ID " + officer + " does not exist in this department");
                    added = false;
                }
            }
            if(added) {
                department.addCase(new Case(
                        caseID,
                        startDatePicker.getValue(),
                        crimeType,
                        descriptionField.getText(),
                        department.getId(),
                        officersField.getText().split(",")
                ));
            }
        }
        catch (DoesNotExistException e){
            showAlert(Alert.AlertType.ERROR, "Error", "Department with ID " + depIDField.getText() + " does not exist");
            added = false;
        }
        catch (NumberFormatException e){
            showAlert(Alert.AlertType.ERROR, "Error", "Department and Case ID must be a number");
            added = false;
        }
        if(!added) throw new NullPointerException();
    }
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
        if(pID.length() < pref.length()+1){
            showAlert(Alert.AlertType.ERROR, "Error", "ID must be at least " + (pref.length()+1) + " characters long");
            return false;
        }
        String id = pID.substring(pref.length());
        if(!pID.contains(pref))
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