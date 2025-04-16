package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.service.TransactionDataStore;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CalculateProfitController implements Initializable {

    @FXML
    private TableColumn<Transaction, Integer> checksum;

    @FXML
    private TableColumn<Transaction, Double> discountPrice;

    @FXML
    private Label finalProfitsLabel;

    @FXML
    private TableColumn<Transaction, Double> internalPrice;

    @FXML
    private TableColumn<Transaction, String> itemCode;

    @FXML
    private TableColumn<Transaction, Double> profitOrLoss;

    @FXML
    private TableColumn<Transaction, Integer> quantity;

    @FXML
    private TableColumn<Transaction, Double> salePrice;

    @FXML
    private TableView<Transaction> profitTable;

    @FXML
    private Label totalLossLabel;

    @FXML
    private Label totalProfitsLabel;

    @FXML
    void onCalculateTaxButtonClick(ActionEvent event) {
        System.out.println("onCalculateTaxButtonClick");

    }

    @FXML
    void onDeleteZeroProfitButtonClick(ActionEvent event) {
        System.out.println("onDeleteZeroProfitButtonClick");

    }

    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        List<Transaction> transactions = TransactionDataStore.getInstance().getTransactionList();

        System.out.println("Loaded transactions: " + transactions.size());
        transactions.forEach(System.out::println); // Log the data

        profitTable.setItems(FXCollections.observableArrayList(transactions));
    }

    private void setupTableColumns() {
        itemCode.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getItemCode()));
        internalPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getInternalPrice()));
        discountPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDiscountPrice()));
        salePrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSalePrice()));
        quantity.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));
        checksum.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getChecksum()));
        profitOrLoss.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProfit()));
    }

}
