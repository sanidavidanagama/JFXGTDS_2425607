package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.service.FileImportManager;
import edu.iit.erp.jfxgtds_2425607.utils.AppExceptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportTransactionsController {

    @FXML
    private Label fileNameLabel;

    @FXML
    private TextField filePathInput;


    @FXML
    private Label statusMessageLabel;

    @FXML
    private Label viewTransactionsErrorLabel;


    private String filePathForCSV;

    private String fileNameForCSV;

    public String getFileNameForCSV() {
        return fileNameForCSV;
    }

    public void setFileNameForCSV(String fileNameForCSV) {
        this.fileNameForCSV = fileNameForCSV;
    }

    public String getFilePathForCSV() {
        return filePathForCSV;
    }

    public void setFilePathForCSV(String filePathForCSV) {
        this.filePathForCSV = filePathForCSV;
    }

    private List<Transaction> transactions = new ArrayList<>();


    @FXML
    void onBrowseButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Tax File");

        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(csvFilter);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            filePathInput.setText(filePath);
            filePathForCSV = filePath;
        }
    }

    @FXML
    void onImportButtonClick(ActionEvent event) {
        FileImportManager fileImportManager = new FileImportManager();
        String filePath = filePathInput.getText();
        try {
            List<Transaction> transactions = fileImportManager.getCSVDatatoArray(filePath);
            statusMessageLabel.setText("File Imported Successfully");
            File file = new File(filePathForCSV);
            fileNameLabel.setText(file.getName());
            setFileNameForCSV(file.getName());
            setTransactions(transactions);
        }
        catch (AppExceptions.FileNotFoundErrorException e) {
            statusMessageLabel.setText("File not found. Please verify the file path.");
        }
        catch (AppExceptions.FileFormatErrorException e) {
            statusMessageLabel.setText("The file format appears to be incorrect. \nKindly select a valid CSV file that matches the required format.");
        }
    }

    @FXML
    void onViewTransactionsButtonClick(ActionEvent event) {
        if (!transactions.isEmpty()) {
            viewTransactionsErrorLabel.setText("");
            ScreenLoader.loadViewTransactions();
        } else {
            viewTransactionsErrorLabel.setText("Please import a file to continue");
        }
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) {
        ScreenLoader.loadHomePage();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
