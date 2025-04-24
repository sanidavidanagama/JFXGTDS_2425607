package edu.iit.erp.jfxgtds_2425607.service;

public class CalculateTaxManager {

    private Double tax;

    private final Double profitOrLoss = TransactionDataStore.getInstance().getProfitOrLoss();

    private final Double totalProfit = TransactionDataStore.getInstance().getTotalProfits();

    private final Double totalLoss = TransactionDataStore.getInstance().getTotalLoss();

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getProfitOrLoss() {
        return profitOrLoss;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public Double getTotalLoss() {
        return totalLoss;
    }

    public String validateTaxInput(String tax) {
        if (tax.isEmpty()) {
            return "Field is empty";
        }
        try {
            Double taxDouble = Double.parseDouble(tax);
            if (taxDouble < 0) {
                return "Tax cannot be negative";
            }
            else {
                taxDouble *= 0.01;
                setTax(taxDouble);
                return "";
            }
        }
        catch (NumberFormatException e) {
            return "Invalid tax amount. Cannot contain text";
        }
    }
}
