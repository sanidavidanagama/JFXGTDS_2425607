package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomePageController {

    @FXML
    void onGetStartedButtonClick(ActionEvent event) {
        ScreenLoader.loadImportTransactions();
    }

}
