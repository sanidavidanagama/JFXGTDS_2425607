package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewTransactionsController implements Initializable {

    @FXML
    private TableView<Transaction> billTable;

    @FXML
    private TableColumn<Transaction, String> billNumberColumn;

    @FXML
    private TableColumn<Transaction, String> itemCodeColumn;

    @FXML
    private TableColumn<Transaction, Double> internalPriceColumn;

    @FXML
    private TableColumn<Transaction, Double> discountPriceColumn;

    @FXML
    private TableColumn<Transaction, Double> salePriceColumn;

    @FXML
    private TableColumn<Transaction, Integer> quantityColumn;

    @FXML
    private TableColumn<Transaction, Double> lineTotalColumn;

    @FXML
    private TableColumn<Transaction, Double> grandTotalColumn;

    @FXML
    private TableColumn<Transaction, Integer> checksumColumn;

    @FXML
    private Label fileNameLabelViewTransactions;

    private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();


    private String importedFilePath;

    public void setImportedFilePath(String importedFilePath) {
        this.importedFilePath = importedFilePath;
        System.out.println("importedFilePath: " + importedFilePath);

        File file = new File(importedFilePath);
        fileNameLabelViewTransactions.setText(file.getName());
        loadCSVData();
    }

    @FXML
    void onImportTransactionsButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/import-transactions.fxml"));
            Parent root = loader.load();

            root.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @FXML
    void onValidateTransactionsButtonClick(ActionEvent event) {

    }

    @FXML
    void onHomeButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/home-page.fxml"));
            Parent root = loader.load();

            root.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            System.out.println(e);

        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Controller initialized!");
        // Bind columns to the Transaction properties
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
        billTable.setItems(transactionData);
        // Load initial data

        System.out.println("Table items: " + billTable.getItems().size());
    }

    private void loadCSVData(){
        List<Transaction> transactions = new ArrayList<>();
        String filePath = importedFilePath;
        System.out.println("filePath: " + filePath);

        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String line;
            while ((line = br.readLine()) != null) {
                Transaction transaction = getBill(line);
                transactions.add(transaction);
            }

            transactionData.addAll(transactions);

        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private static Transaction getBill(String line) {
        String[] data = line.split(",");

        Transaction transaction = new Transaction(
                data[0],                        //Transaction Number
                data[1],                        // Item code
                Double.parseDouble(data[2]),    // Internal Price
                Double.parseDouble(data[3]),    // Discount Price
                Double.parseDouble(data[4]),    // Sale Price
                Integer.parseInt(data[5]),      // Quantity
                Double.parseDouble(data[6]),    // Line Total
                Double.parseDouble(data[7]),    // Grand Total
                Integer.parseInt(data[8])       // Checksum
        );
        return transaction;
    }

    // Method to add a new transaction to the table
    public void addBill(Transaction transaction) {
        transactionData.add(transaction);
        System.out.println("Added transaction: " + transaction.getBillNumber());
    }

}




