package com.sevenatseven.controllers.offiecer.util.display.info;

import com.sevenatseven.mainEntities.Criminal;
import com.sevenatseven.sideentities.Crime;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CriminalInfoController implements Initializable {
    @FXML private Label nameLabel;
    @FXML private Label idLabel;
    @FXML private Label locationLabel;
    @FXML private Label dangerLevelLabel;
    @FXML private TextArea psychProfileArea;
    
    @FXML private TableView<Crime> crimesTable;
    @FXML private TableColumn<Crime, String> crimeTypeColumn;
    @FXML private TableColumn<Crime, String> crimeDateColumn;
    @FXML private TableColumn<Crime, String> crimeLocationColumn;
    @FXML private TableColumn<Crime, String> crimeDescriptionColumn;
    
    @FXML private TableView<CrimeStatEntry> statsTable;
    @FXML private TableColumn<CrimeStatEntry, String> crimeTypeStatsColumn;
    @FXML private TableColumn<CrimeStatEntry, Integer> crimeCountColumn;

    private Criminal criminal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupCrimesTable();
        setupStatsTable();
    }

    private void setupCrimesTable() {
        crimeTypeColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getCrimeType().toString()
            )
        );
        crimeDateColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getDateCommited().toString()
            )
        );
        // Note: Location and Description are not available in the Crime class
        crimeLocationColumn.setVisible(false);
        crimeDescriptionColumn.setVisible(false);
    }

    private void setupStatsTable() {
        crimeTypeStatsColumn.setCellValueFactory(new PropertyValueFactory<>("crimeType"));
        crimeCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    public void setCriminal(Criminal criminal) {
        this.criminal = criminal;
        updateUI();
    }

    private void updateUI() {
        if (criminal == null) return;

        // Update basic info
        nameLabel.setText(criminal.getFirstName() + " " + criminal.getLastName());
        idLabel.setText("ID: " + criminal.getId());
        locationLabel.setText(criminal.getCurrentLocation());
        dangerLevelLabel.setText(criminal.getDangerLevel().toString());
        psychProfileArea.setText(criminal.getPsychologicalProfile());

        // Update crimes table
        crimesTable.setItems(FXCollections.observableArrayList(criminal.getCrimes()));

        // Update statistics table
        var stats = criminal.getCrimeStatistics().entrySet().stream()
                .map(entry -> new CrimeStatEntry(entry.getKey().getCrimeType().toString(), entry.getValue()))
                .collect(Collectors.toList());
        statsTable.setItems(FXCollections.observableArrayList(stats));
    }

    // Helper class for crime statistics
    public static class CrimeStatEntry {
        private final String crimeType;
        private final int count;

        public CrimeStatEntry(String crimeType, int count) {
            this.crimeType = crimeType;
            this.count = count;
        }

        public String getCrimeType() { return crimeType; }
        public int getCount() { return count; }
    }
}
