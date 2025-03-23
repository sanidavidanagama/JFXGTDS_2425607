package edu.iit.erp.jfxgtds_2425607.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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

    public String getFilePathForCSV() {
        return filePathForCSV;
    }

    public void setFilePathForCSV(String filePathForCSV) {
        this.filePathForCSV = filePathForCSV;
    }

    @FXML
    void onBrowseButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Tax File");

        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(csvFilter);

        // Get the Stage from the ActionEvent
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            filePathInput.setText(filePath);

        } else {
            System.out.println("No File Selected");
        }
    }


    @FXML
    void onImportTransactionsButtonClick(ActionEvent event) {
        String filePath = filePathInput.getText();

        if (filePath.isEmpty()) {
            statusMessageLabel.setText("File path is empty. Please enter the file path for .csv file");
            return;
        }

        File file = new File(filePath);

        if (!filePath.endsWith(".csv")) {
            statusMessageLabel.setText("Invalid format. Please enter the file path for .csv file");
        } else {

            if (file.exists() && file.isFile()) {
                statusMessageLabel.setText("File Successfully Imported");
                String fileName = file.getName();
                fileNameLabel.setText("Name : " + fileName);

            } else {
                statusMessageLabel.setText("File does not exists! Please try again.");
                fileNameLabel.setText("Name : ");
            }
        }
    }

    @FXML
    void onViewTransactionsButtonClick(ActionEvent event) {
        if (statusMessageLabel.getText().equals("File Successfully Imported")) {
            viewTransactionsErrorLabel.setText("");

            filePathForCSV = filePathInput.getText();
            setFilePathForCSV(filePathForCSV);
            System.out.println("File path for csv : " + getFilePathForCSV());

            // Redirect to View Transactions
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view-transactions.fxml"));
                Parent root = loader.load();

                ViewTransactionsController controller = loader.getController();
                controller.setImportedFilePath(getFilePathForCSV());

                root.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();


            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            viewTransactionsErrorLabel.setText("Please import a file to continue");
        }
    }

}
