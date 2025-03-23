package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.models.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
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

    @FXML
    private Label fileNameLabelViewTransactions;

    private ObservableList<Bill> billData = FXCollections.observableArrayList();


    private String importedFilePath;

    public void setImportedFilePath(String importedFilePath) {
        this.importedFilePath = importedFilePath;
        System.out.println("importedFilePath: " + importedFilePath);

        File file = new File(importedFilePath);
        fileNameLabelViewTransactions.setText(file.getName());
        loadInitialData();
    }

    public String getImportedFilePath() {
        return importedFilePath;
    }

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

        System.out.println("Table items: " + billTable.getItems().size());
    }

    private void loadInitialData(){
        List<Bill> bills = new ArrayList<>();
        String filePath = importedFilePath;
        System.out.println("filePath: " + filePath);

        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String line;
            while ((line = br.readLine()) != null) {
                Bill bill = getBill(line);
                bills.add(bill);
            }

            billData.addAll(bills);

        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private static Bill getBill(String line) {
        String[] data = line.split(",");

        Bill bill = new Bill(
                data[0],                        //Bill Number
                data[1],                        // Item code
                Double.parseDouble(data[2]),    // Internal Price
                Double.parseDouble(data[3]),    // Discount Price
                Double.parseDouble(data[4]),    // Sale Price
                Integer.parseInt(data[5]),      // Quantity
                Double.parseDouble(data[6]),    // Line Total
                Double.parseDouble(data[7]),    // Grand Total
                Integer.parseInt(data[8])       // Checksum
        );
        return bill;
    }

    // Method to add a new bill to the table
    public void addBill(Bill bill) {
        billData.add(bill);
        System.out.println("Added bill: " + bill.getBillNumber());
    }

}




