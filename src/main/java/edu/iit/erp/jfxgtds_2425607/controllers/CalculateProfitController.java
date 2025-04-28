package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import edu.iit.erp.jfxgtds_2425607.service.CalculateProfitManager;
import edu.iit.erp.jfxgtds_2425607.model.TransactionDataStore;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;

public class CalculateProfitController {

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
    private Label deletedCountLabel;

    @FXML
    private Label totalProfitsLabel;

    @FXML
    private Button deleteZeroProfitsButton;

    @FXML
    private Label validatedTransactionsCountLabel;

    private final CalculateProfitManager manager = new CalculateProfitManager();


    @FXML
    void onCalculateTaxButtonClick(ActionEvent event) {
        TransactionDataStore.getInstance().setTotalProfits(manager.getProfits());
        TransactionDataStore.getInstance().setTotalLoss(manager.getLoss());
        TransactionDataStore.getInstance().setProfitOrLoss(manager.getProfitOrLoss());
    }

    @FXML
    void onDeleteZeroProfitButtonClick(ActionEvent event) {
        Integer deletedCount = manager.deleteZeroProfitTransactions();
        deletedCountLabel.setText(deletedCount  + " zero profit transactions have been deleted." );
        deleteZeroProfitsButton.setDisable(true);
        initialize();
    }

    public void initialize() {
        setupTableColumns();
        List<Transaction> transactions = TransactionDataStore.getInstance().getTransactionList();
        profitTable.setItems(FXCollections.observableArrayList(transactions));
        setupProfitInfo();
    }


    public void setupProfitInfo() {
        validatedTransactionsCountLabel.setText("Total Validated Transactions : "+ String.valueOf(TransactionDataStore.getInstance().getTransactionList().size()));
        totalProfitsLabel.setText("Total Profits : " + manager.findProfits());
        totalLossLabel.setText("Total Loss : " + manager.findLosses());
        finalProfitsLabel.setText("Final PnL : " + manager.findTotalProfitOrLoss());
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
