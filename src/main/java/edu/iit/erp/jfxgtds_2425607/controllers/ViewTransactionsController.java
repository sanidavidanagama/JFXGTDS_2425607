package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import edu.iit.erp.jfxgtds_2425607.model.TransactionDataStore;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewTransactionsController {
    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, String> itemCodeColumn;

    @FXML
    private TableColumn<Transaction, Double> internalPriceColumn;

    @FXML
    private TableColumn<Transaction, Double> discountColumn;

    @FXML
    private TableColumn<Transaction, Double> salePriceColumn;

    @FXML
    private TableColumn<Transaction, Integer> quantityColumn;

    @FXML
    private TableColumn<Transaction, Integer> checksumColumn;

    @FXML
    private Label fileNameLabelViewTransactions;

    public void initialize() {
        // Load data directly from TransactionDataStore
        TransactionDataStore dataStore = TransactionDataStore.getInstance();
        transactionTable.setItems(FXCollections.observableArrayList(dataStore.getTransactionList()));
        fileNameLabelViewTransactions.setText(dataStore.getFileName());

        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        internalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("internalPrice"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discountPrice"));
        salePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        checksumColumn.setCellValueFactory(new PropertyValueFactory<>("checksum"));
    }

    @FXML
    void onValidateTransactionsButtonClick(ActionEvent event) {
        ScreenLoader.loadValidateTransactions();
    }
}
