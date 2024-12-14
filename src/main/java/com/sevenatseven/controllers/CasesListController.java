package com.sevenatseven.controllers;

import com.sevenatseven.mainEntities.Case;
import com.sevenatseven.mainEntities.PoliceOfficer;
import com.sevenatseven.utils.Shared;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class CasesListController {
    @FXML private TableView<Case> casesTable;
    @FXML private TableColumn<Case, Integer> caseIdColumn;
    @FXML private TableColumn<Case, String> descriptionColumn;
    @FXML private TableColumn<Case, Void> actionsColumn;


    private PoliceOfficer officer;
    private ArrayList<Case> officerCases;

    @FXML
    public void initialize() {
        this.officer = Shared.getCurrentOfficer();
        this.officerCases = this.officer.getCases();

        caseIdColumn.setCellValueFactory(new PropertyValueFactory<>("caseID"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));


        actionsColumn.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Case currentCase = getTableView().getItems().get(getIndex());

                    Button displayButton = new Button("Display");
                    displayButton.setOnAction(event -> openDisplayView(currentCase));

                    Button updateButton = new Button("Update");
                    updateButton.setOnAction(event -> openUpdateView(currentCase));

                    HBox buttons = new HBox(10, displayButton, updateButton);
                    setGraphic(buttons);
                }
            }
        });


        ObservableList<Case> observableCases = FXCollections.observableArrayList(this.officerCases);
        casesTable.setItems(observableCases);
    }
    private void openDisplayView(Case selectedCase) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CaseDisplayView.fxml"));
            Parent root = loader.load();

            CaseDisplayController controller = loader.getController();
            controller.setCaseDetails(selectedCase);

            Stage stage = (Stage) casesTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Case Details");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdateView(Case selectedCase) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CaseUpdateView.fxml"));
            Parent root = loader.load();

            CaseUpdateController controller = loader.getController();
            controller.setCaseDetails(selectedCase);

            Stage stage = (Stage) casesTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Update Case");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}