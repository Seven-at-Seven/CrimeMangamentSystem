package com.sevenatseven.controllers;

import Classes.Cases;
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

import java.io.IOException;
import java.util.ArrayList;

public class CasesListController {
    @FXML private TableView<Cases> casesTable;
    @FXML private TableColumn<Cases, Integer> caseIdColumn;
    @FXML private TableColumn<Cases, String> descriptionColumn;
    @FXML private TableColumn<Cases, Void> actionsColumn;

    private final ArrayList<Cases> allCases = Cases.readCases("cases.csv");

    @FXML
    public void initialize() {

        caseIdColumn.setCellValueFactory(new PropertyValueFactory<>("caseID"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));


        actionsColumn.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Cases currentCase = getTableView().getItems().get(getIndex());

                    Button displayButton = new Button("Display");
                    displayButton.setOnAction(event -> openDisplayView(currentCase));

                    Button updateButton = new Button("Update");
                    updateButton.setOnAction(event -> openUpdateView(currentCase));

                    HBox buttons = new HBox(10, displayButton, updateButton);
                    setGraphic(buttons);
                }
            }
        });


        ObservableList<Cases> observableCases = FXCollections.observableArrayList(allCases);
        casesTable.setItems(observableCases);
    }
    private void openDisplayView(Cases selectedCase) {
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

    private void openUpdateView(Cases selectedCase) {
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