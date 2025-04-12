package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ViewTransactionsController {
    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, String> billNumberColumn;

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
    private TableColumn<Transaction, Double> lineTotalColumn;

    @FXML
    private TableColumn<Transaction, Double> grandTotalColumn;

    @FXML
    private TableColumn<Transaction, String> checksumColumn;

    @FXML
    private Label fileNameLabelViewTransactions;

    public void initialize() {
        billNumberColumn.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        internalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("internalPrice"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discountPrice"));
        salePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        lineTotalColumn.setCellValueFactory(new PropertyValueFactory<>("lineTotal"));
        grandTotalColumn.setCellValueFactory(new PropertyValueFactory<>("grandTotal"));
        checksumColumn.setCellValueFactory(new PropertyValueFactory<>("checksum"));
    }

    public void setTransactionList(List<Transaction> list) {
        transactionTable.setItems(FXCollections.observableArrayList(list));
    }

    public void setFileName(String fileName) {
        fileNameLabelViewTransactions.setText(fileName);
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) {
        ScreenLoader.loadHomePage();
    }

    @FXML
    void onImportTransactionsButtonClick(ActionEvent event) {
        ScreenLoader.loadImportTransactions();
    }

    @FXML
    void onValidateTransactionsButtonClick(ActionEvent event) {
        System.out.println("transactionTable.getSelectionModel().getSelectedItem()");
    }
}
