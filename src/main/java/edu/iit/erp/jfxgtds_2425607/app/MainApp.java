package edu.iit.erp.jfxgtds_2425607.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ScreenLoader.setPrimaryStage(primaryStage);
        ScreenLoader.loadHomePage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
