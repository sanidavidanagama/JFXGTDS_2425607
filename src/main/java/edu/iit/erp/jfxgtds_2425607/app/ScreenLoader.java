package edu.iit.erp.jfxgtds_2425607.app;

import edu.iit.erp.jfxgtds_2425607.controllers.ViewTransactionsController;
import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load " + fxmlFile);
            e.printStackTrace();
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

    public static void loadViewTransactions(List<Transaction> transactionsList, String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenLoader.class.getResource("/view-transactions.fxml"));
            Parent root = loader.load();

            // Pass data to the controller
            ViewTransactionsController controller = loader.getController();
            controller.setTransactionList(transactionsList);

            controller.setFileName(fileName);

            Scene scene = new Scene(root, 1280, 720);
            scene.getStylesheets().add(ScreenLoader.class.getResource("/styles.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("View Transactions");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load view-transactions.fxml");
            e.printStackTrace();
        }
    }

}
