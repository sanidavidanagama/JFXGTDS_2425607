package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.service.TransactionDataStore;
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

    @FXML
    void onCalculateButtonClick(ActionEvent event) {
        // Final Tax = [For all line items SUM of (Profit – Loss) ] * tax rate%.
        try {
            Double taxRate = Double.parseDouble(taxRateInput.getText());
            Double tax =  calculateTax(taxRate);
            finalTaxLabel.setText(Double.toString(tax));
        }
        catch (NumberFormatException e) {
            taxRateErrorLabel.setText("Invalid Tax Rate");
        }
    }

    private Double calculateTax(Double taxRate) {
        Double profitOrLoss = TransactionDataStore.getInstance().getProfitOrLoss();
        return profitOrLoss * taxRate;
    }

    public void initialize() {

    }


}
