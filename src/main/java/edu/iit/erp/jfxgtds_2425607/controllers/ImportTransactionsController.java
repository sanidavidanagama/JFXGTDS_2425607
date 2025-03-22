package edu.iit.erp.jfxgtds_2425607.controllers;

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
    private TextField fileName;

    @FXML
    private Label fileNameLabel;

    @FXML
    private TextField filePath;

    @FXML
    private Label filePathLabel;

    private String selectedFilePath;

    @FXML
    public void onBrowseButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(csvFilter);

        // Get the Stage from the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            selectedFilePath = selectedFile.getAbsolutePath();
            System.out.println(selectedFilePath);
        } else {
            System.out.println("No File Selected");
        }
    }






    public String getSelectedFilePath() {
        return selectedFilePath;
    }
}
