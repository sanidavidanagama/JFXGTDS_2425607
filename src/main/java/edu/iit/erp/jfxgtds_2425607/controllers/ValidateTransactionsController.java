package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import edu.iit.erp.jfxgtds_2425607.models.InvalidTransaction;
import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.service.TransactionDataStore;
import edu.iit.erp.jfxgtds_2425607.service.ValidateTransactionsManager;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.List;

public class ValidateTransactionsController {

    @FXML
    private TableColumn<InvalidTransaction, Void> delete;

    @FXML
    private TableColumn<InvalidTransaction, Void> edit;

    @FXML
    private TableColumn<InvalidTransaction, Integer> invalidChecksum;

    @FXML
    private TableColumn<InvalidTransaction, Double> invalidDiscount;

    @FXML
    private TableColumn<InvalidTransaction, Double> invalidInternalPrice;

    @FXML
    private TableColumn<InvalidTransaction, String> invalidItemCode;

    @FXML
    private TableColumn<InvalidTransaction, Integer> invalidQuantity;

    @FXML
    private TableColumn<InvalidTransaction, Double> invalidSalePrice;

    @FXML
    private TableView<InvalidTransaction> invalidTable;

    @FXML
    private TableColumn<Transaction, Integer> validChecksum;

    @FXML
    private TableColumn<Transaction, Double> validDiscount;

    @FXML
    private TableColumn<Transaction, Double> validInternalPrice;

    @FXML
    private TableColumn<Transaction, String> validItemCode;

    @FXML
    private TableColumn<Transaction, Integer> validQuantity;

    @FXML
    private TableColumn<Transaction, Double> validSalePrice;

    @FXML
    private TableView<Transaction> validTable;

    @FXML
    private Label totalImportedLabel;

    @FXML
    private Label totalValidLabel;

    @FXML
    private Label totalInvalidLabel;

    @FXML
    private Label invalidRecordsTableCaption;

    @FXML
    private Label validRecordsTableCaption;

    @FXML
    void onCalculateProfitButtonClick(ActionEvent event) {
        if (manager.getTotalInvalid() == 0) {
            ScreenLoader.loadCalculateProfit();
        }
        else {
            System.out.println("Validate all invalid records");
        }
    }

    private final ValidateTransactionsManager manager = new ValidateTransactionsManager();

    public void initialize() {
        validate();
        setupTableColumns();
        addActionButtonsToTable();
        setupValidationResults();
    }

    private void setupTableColumns() {
        invalidItemCode.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getItemCode()));
        invalidInternalPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getInternalPrice()));
        invalidDiscount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getDiscountPrice()));
        invalidSalePrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getSalePrice()));
        invalidQuantity.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getQuantity()));
        invalidChecksum.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getChecksum()));

        validItemCode.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getItemCode()));
        validInternalPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getInternalPrice()));
        validDiscount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDiscountPrice()));
        validSalePrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSalePrice()));
        validQuantity.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));
        validChecksum.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getChecksum()));
    }

    private void addActionButtonsToTable() {
        delete.setCellFactory(col -> new TableCell<InvalidTransaction, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.getStyleClass().add("action-button");
                deleteButton.setOnAction(event -> {
                    InvalidTransaction transaction = (InvalidTransaction) getTableView().getItems().get(getIndex());
                    showDeleteConfirmationDialog(transaction);
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });

        edit.setCellFactory(col -> new TableCell<InvalidTransaction, Void>() {
            private final Button editButton = new Button("Edit");
            {
                editButton.getStyleClass().add("action-button");
                editButton.setOnAction(event -> {
                    InvalidTransaction transaction = (InvalidTransaction) getTableView().getItems().get(getIndex());
                    openEditPopup(transaction);
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
        });
    }

    private void showDeleteConfirmationDialog(InvalidTransaction transaction) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to remove this transaction?");
        alert.setContentText("This action cannot be undone.");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                manager.deleteInvalidTransaction(transaction);
                initialize();
            }
        });
    }

    private void openEditPopup(InvalidTransaction transaction) {
        ScreenLoader.openEditTransactionDialog(transaction);
        initialize();
    }

    public void validate() {
        manager.validateTransactions();
        displayTables(manager.getValidTransactionsList(), manager.getInvalidTransactionList());
    }

    public void displayTables(List<Transaction> validList, List<InvalidTransaction> invalidList) {
        if (!validList.isEmpty()) {
            validTable.setItems(FXCollections.observableArrayList(validList));
            validTable.setVisible(true);
            validTable.setManaged(true);
        }
        else if (validList.isEmpty()) {
            validTable.setVisible(false);
            validTable.setManaged(false);
        }

        if (!invalidList.isEmpty()) {
            invalidTable.setItems(FXCollections.observableArrayList(invalidList));
            invalidTable.setVisible(true);
            invalidTable.setManaged(true);
        }

        else if (invalidList.isEmpty()) {
            invalidTable.setVisible(false);
            invalidTable.setManaged(false);
        }
        setupTableCaptions();
    }


    public void setupTableCaptions() {
        int totalValid = manager.getTotalValid();
        int totalInvalid = manager.getTotalInvalid();
        int totalImported = manager.getTotalImported();

        boolean allValid = totalInvalid == 0;
        boolean allInvalid = totalValid == 0;

        validRecordsTableCaption.setVisible(!allInvalid);
        invalidRecordsTableCaption.setVisible(!allValid);

        if (allValid) {
            validRecordsTableCaption.setText("All Transactions are Valid and Verified!");
        } else if (allInvalid) {
            invalidRecordsTableCaption.setText("Action Needed - All Transactions are Invalid!");
        } else {
            validRecordsTableCaption.setText("Valid Transactions");
            invalidRecordsTableCaption.setText("Action Needed - " + totalInvalid + " Invalid Transactions Found!");
        }
    }


    public void setupValidationResults() {
        totalImportedLabel.setText("Total Imported Records: " + manager.getTotalImported());
        totalValidLabel.setText("Total Valid Records: " + manager.getTotalValid());
        totalInvalidLabel.setText("Total Invalid Records: " + manager.getTotalInvalid());
    }
}
