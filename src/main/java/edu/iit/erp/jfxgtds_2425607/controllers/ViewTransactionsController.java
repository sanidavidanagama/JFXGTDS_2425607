package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.models.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewTransactionsController implements Initializable {

    @FXML
    private TableView<Bill> billTable;

    @FXML
    private TableColumn<Bill, String> billNumberColumn;

    @FXML
    private TableColumn<Bill, String> itemCodeColumn;

    @FXML
    private TableColumn<Bill, Double> internalPriceColumn;

    @FXML
    private TableColumn<Bill, Double> discountPriceColumn;

    @FXML
    private TableColumn<Bill, Double> salePriceColumn;

    @FXML
    private TableColumn<Bill, Integer> quantityColumn;

    @FXML
    private TableColumn<Bill, Double> lineTotalColumn;

    @FXML
    private TableColumn<Bill, Double> grandTotalColumn;

    @FXML
    private TableColumn<Bill, Integer> checksumColumn;


    private ObservableList<Bill> billData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Controller initialized!");
        // Bind columns to the Bill properties
        billNumberColumn.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        internalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("internalPrice"));
        discountPriceColumn.setCellValueFactory(new PropertyValueFactory<>("discountPrice"));
        salePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        lineTotalColumn.setCellValueFactory(new PropertyValueFactory<>("lineTotal"));
        grandTotalColumn.setCellValueFactory(new PropertyValueFactory<>("grandTotal"));
        checksumColumn.setCellValueFactory(new PropertyValueFactory<>("checksum"));

        // Set the data to the table
        billTable.setItems(billData);

        // Load initial data
        loadInitialData();

        System.out.println("Table items: " + billTable.getItems().size());
    }

    private void loadInitialData() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("2025_03_19_0001", "cake", 32.0, 32.0, 32.0, 4, 128.0, 784.0, 34));
        bills.add(new Bill("2025_03_19_0001", "lemon", 54.0, 54.0, 54.0, 4, 216.0, 784.0, 35));
        bills.add(new Bill("2025_03_19_0001", "coffee", 44.0, 44.0, 44.0, 10, 440.0, 784.0, 37));
        bills.add(new Bill("2025_03_19_0002", "coffee", 60.0, 60.0, 60.0, 4, 240.0, 522.0, 36));
        bills.add(new Bill("2025_03_19_0002", "latte", 43.0, 43.0, 43.0, 4, 172.0, 522.0, 35));
        bills.add(new Bill("2025_03_19_0002", "macrons", 22.0, 22.0, 22.0, 5, 110.0, 522.0, 37));
        bills.add(new Bill("2025_03_19_0003", "tea", 20.0, 20.0, 20.0, 3, 60.0, 315.0, 31));
        bills.add(new Bill("2025_03_19_0003", "brownie", 55.0, 55.0, 55.0, 2, 110.0, 315.0, 31));
        bills.add(new Bill("2025_03_19_0003", "croissant", 48.0, 48.0, 48.0, 3, 144.0, 315.0, 31));
        bills.add(new Bill("2025_03_19_0004", "cappuccino", 50.0, 50.0, 50.0, 6, 300.0, 674.0, 39));
        bills.add(new Bill("2025_03_19_0004", "tart", 62.0, 62.0, 62.0, 4, 248.0, 674.0, 39));
        bills.add(new Bill("2025_03_19_0004", "donut", 21.0, 21.0, 21.0, 6, 126.0, 674.0, 39));
        bills.add(new Bill("2025_03_19_0005", "mocha", 45.0, 45.0, 45.0, 5, 225.0, 400.0, 38));
        bills.add(new Bill("2025_03_19_0005", "muffin", 36.0, 36.0, 36.0, 4, 144.0, 400.0, 38));
        bills.add(new Bill("2025_03_19_0005", "sandwich", 65.0, 65.0, 65.0, 5, 325.0, 400.0, 38));
        bills.add(new Bill("2025_03_19_0006", "espresso", 30.0, 30.0, 30.0, 2, 60.0, 180.0, 30));
        bills.add(new Bill("2025_03_19_0006", "bagel", 25.0, 25.0, 25.0, 3, 75.0, 180.0, 30));
        bills.add(new Bill("2025_03_19_0006", "milkshake", 45.0, 45.0, 45.0, 1, 45.0, 180.0, 30));
        bills.add(new Bill("2025_03_19_0007", "iced_tea", 40.0, 40.0, 40.0, 3, 120.0, 345.0, 32));
        bills.add(new Bill("2025_03_19_0007", "pastry", 38.0, 38.0, 38.0, 5, 190.0, 345.0, 32));
        bills.add(new Bill("2025_03_19_0007", "smoothie", 35.0, 35.0, 35.0, 1, 35.0, 345.0, 32));
        bills.add(new Bill("2025_03_19_0008", "hot_chocolate", 42.0, 42.0, 42.0, 2, 84.0, 246.0, 40));
        bills.add(new Bill("2025_03_19_0008", "toast", 28.0, 28.0, 28.0, 3, 84.0, 246.0, 40));
        bills.add(new Bill("2025_03_19_0008", "tart", 26.0, 26.0, 26.0, 3, 78.0, 246.0, 40));

        billData.addAll(bills);

        System.out.println("Loaded " + bills.size() + " bills.");
    }

    // Method to add a new bill to the table
    public void addBill(Bill bill) {
        billData.add(bill);
        System.out.println("Added bill: " + bill.getBillNumber());
    }
}




