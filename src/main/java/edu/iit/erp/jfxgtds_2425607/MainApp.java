package edu.iit.erp.jfxgtds_2425607;

import edu.iit.erp.jfxgtds_2425607.models.Bill;
import edu.iit.erp.jfxgtds_2425607.controllers.ViewTransactionsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view-transactions.fxml"));
        BorderPane root = loader.load();

        // Get the controller
        ViewTransactionsController controller = loader.getController();

        // Set up the scene and stage
        Scene scene = new Scene(root, 1280, 720);

        // Load the stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());


        primaryStage.setScene(scene);
        primaryStage.setTitle("Bill Table");
        primaryStage.show();

        // Example: Add a new bill dynamically
        controller.addBill(new Bill("2025_03_19_0003", "tea", 15.0, 15.0, 15.0, 2, 30.0, 100.0, 38));
    }

    public static void main(String[] args) {
        launch(args);
    }
}