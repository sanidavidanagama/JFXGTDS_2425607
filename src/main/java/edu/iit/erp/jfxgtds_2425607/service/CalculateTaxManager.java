package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import edu.iit.erp.jfxgtds_2425607.model.TransactionDataStore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
                calculateTax(taxDouble);
                return "";
            }
        }
        catch (NumberFormatException e) {
            return "Invalid tax amount. Cannot contain text";
        }
    }

    private void calculateTax(Double tax) {
        tax = Math.round(tax * profitOrLoss) / 100.0;
        if (tax < 0) {
            tax = 0.0;
        }
        setTax(tax);
    }

    public void resetAll() {
        TransactionDataStore.getInstance().reset();
    }

    public void saveChanges() {
        String fileName = TransactionDataStore.getInstance().getAbsoluteFilePath();
        List<Transaction> transactions = TransactionDataStore.getInstance().getTransactionList();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Transaction t : transactions) {
                String line = String.format("%s,%s,%s,%s,%s,%s", t.getItemCode(), t.getInternalPrice(), t.getDiscountPrice(), t.getSalePrice(), t.getQuantity(), t.getChecksum());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
