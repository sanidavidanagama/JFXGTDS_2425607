package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import edu.iit.erp.jfxgtds_2425607.service.FileImportManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImportTransactionsController {

    @FXML
    private Label fileNameLabel;

    @FXML
    private TextField filePathInput;

    @FXML
    private Label statusMessageLabel;

    @FXML
    private Label viewTransactionsErrorLabel;

    private final FileImportManager manager = new FileImportManager();

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
        }
    }

    @FXML
    void onImportButtonClick(ActionEvent event) {
        statusMessageLabel.setText("");
        fileNameLabel.setText("");
        manager.setAbsoluteFilePath(filePathInput.getText());
        manager.validateImportedFile();
        statusMessageLabel.setText(manager.getStatusMessage());
        fileNameLabel.setText(manager.getFileName());
    }

    public void initialize() {
        manager.loadData();
        fileNameLabel.setText(manager.getFileName());
        statusMessageLabel.setText(manager.getStatusMessage());
        fileNameLabel.setText(manager.getFileName());
        filePathInput.setText(manager.getAbsoluteFilePath());
    }

    @FXML
    void onViewTransactionsButtonClick(ActionEvent event) {
        if(!manager.getTransactionsList().isEmpty()) {
            viewTransactionsErrorLabel.setText("");
            ScreenLoader.loadViewTransactions();
        } else {
            viewTransactionsErrorLabel.setText("Please import a file to continue");
        }
    }
}
