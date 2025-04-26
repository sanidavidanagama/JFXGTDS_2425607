package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.service.CalculateProfitManager;
import edu.iit.erp.jfxgtds_2425607.service.TransactionDataStore;
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
        ScreenLoader.loadCalculateTax();
    }

    @FXML
    void onDeleteZeroProfitButtonClick(ActionEvent event) {
        Integer deletedCount = manager.deleteZeroProfitTransactions();
        deletedCountLabel.setText(deletedCount == 0 ? deletedCount  + " zero profit transactions have been deleted." : "No transactions have been deleted.");
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
        totalProfitsLabel.setText("Total Loss : " + manager.findProfits());
        totalLossLabel.setText("Total Profits : " + manager.findLosses());
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

        // Set custom CellFactory for profitOrLoss column
        profitOrLoss.setCellFactory(column -> new TableCell<Transaction, Double>() {
            @Override
            public void updateItem(Double profit, boolean empty) {
                super.updateItem(profit, empty);

                setText(null);
                setStyle("");
                if (!empty && profit != null) {
                    setText(profit.toString());
                    if (profit > 0) {
                        setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;");
                    } else if (profit < 0) {
                        setStyle("-fx-background-color: lightcoral; -fx-text-fill: black;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
    }
}
