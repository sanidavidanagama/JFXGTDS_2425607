package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.app.ScreenLoader;
import edu.iit.erp.jfxgtds_2425607.service.CalculateTaxManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculateTaxController {

    @FXML
    private Label finalTaxLabel;

    @FXML
    private Label profitOrLossLabel;

    @FXML
    private TextField taxRateInput;

    @FXML
    private Label totalLossLabel;

    @FXML
    private Label totalProfitLabel;

    @FXML
    private Label taxRateErrorLabel;

    private final CalculateTaxManager manager = new CalculateTaxManager();

    @FXML
    void onCalculateButtonClick(ActionEvent event) {
        taxRateErrorLabel.setText(manager.validateTaxInput(taxRateInput.getText()));
        if (taxRateErrorLabel.getText().equals("")) {
            finalTaxLabel.setText(manager.getTax().toString());
        }
        else {
            finalTaxLabel.setText("");
        }
    }

    @FXML
    void onCompleteButtonClick(ActionEvent event) {
        manager.resetAll();
        ScreenLoader.loadHomePage();
    }

    public void initialize() {
        setupProfitInfo();
    }

    public void setupProfitInfo() {
        totalProfitLabel.setText(manager.getTotalProfit().toString());
        totalLossLabel.setText(manager.getTotalLoss().toString());
        profitOrLossLabel.setText(manager.getProfitOrLoss().toString());
    }

}
