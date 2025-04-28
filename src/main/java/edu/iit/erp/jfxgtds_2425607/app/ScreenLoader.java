package edu.iit.erp.jfxgtds_2425607.app;

import edu.iit.erp.jfxgtds_2425607.controllers.EditTransactionsController;
import edu.iit.erp.jfxgtds_2425607.model.InvalidTransaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenLoader {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    private static void loadScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenLoader.class.getResource("/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1280, 720);
            scene.getStylesheets().add(ScreenLoader.class.getResource("/styles.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("JFXGTDS | " + title);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load " + fxmlFile);
            System.out.println(e.getMessage());
        }
    }

    public static void loadHomePage() {
        loadScene("home-page.fxml", "Home");
    }

    public static void loadImportTransactions() {
        loadScene("import-transactions.fxml", "Import Transactions");
    }

    public static void loadViewTransactions() {
        loadScene("view-transactions.fxml", "View Transactions");
    }

    public static void loadValidateTransactions() {
        loadScene("validate-transactions.fxml", "Validate Transactions");
    }

    public static void openEditTransactionDialog(InvalidTransaction transaction) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenLoader.class.getResource("/edit-transactions.fxml"));
            Parent root = loader.load();

            EditTransactionsController controller = loader.getController();
            controller.setInvalidTransaction(transaction);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Edit Transaction");
            Scene scene = new Scene(root, 600, 450);
            scene.getStylesheets().add(ScreenLoader.class.getResource("/styles.css").toExternalForm());
            popupStage.setScene(scene);
            popupStage.setResizable(false);
            popupStage.showAndWait();
        }
        catch (IOException e) {
            System.err.println("Failed to load edit-transactions.fxml");
            e.printStackTrace();
        }
    }

    public static void loadCalculateProfit() {
        loadScene("calculate-profits.fxml", "Calculate Profit");
    }

    public static void loadCalculateTax() {
        loadScene("calculate-tax.fxml", "Calculate Tax");
    }
}
