package edu.iit.erp.jfxgtds_2425607;

import edu.iit.erp.jfxgtds_2425607.controllers.HomePageController;
import edu.iit.erp.jfxgtds_2425607.controllers.ImportTransactionsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file for import-transactions
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home-page.fxml"));
        HBox root = loader.load();

        // Get the controller (optional if you need to use it)
        HomePageController controller = loader.getController();

        // Set up the scene and stage
        Scene scene = new Scene(root, 1280, 720);

        // Load the stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Home");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
