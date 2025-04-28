package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.model.InvalidTransaction;
import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import edu.iit.erp.jfxgtds_2425607.model.TransactionDataStore;
import edu.iit.erp.jfxgtds_2425607.utils.Checksum;

import java.util.ArrayList;
import java.util.List;


public class ValidateTransactionsManager {

    private Integer totalImported = 0;
    private Integer totalValid = 0;
    private Integer totalInvalid = 0;
    private List<Transaction> validTransactionsList = new ArrayList<>();
    private List<InvalidTransaction> invalidTransactionList = new ArrayList<>();

    Checksum checksum = new Checksum();

    public Integer getTotalImported() {
        return totalImported;
    }

    public void setTotalImported(Integer totalImported) {
        this.totalImported = totalImported;
    }

    public Integer getTotalValid() {
        return totalValid;
    }

    public void setTotalValid(Integer totalValid) {
        this.totalValid = totalValid;
    }

    public Integer getTotalInvalid() {
        return totalInvalid;
    }

    public void setTotalInvalid(Integer totalInvalid) {
        this.totalInvalid = totalInvalid;
    }

    public List<Transaction> getValidTransactionsList() {
        return validTransactionsList;
    }

    public void setValidTransactionsList(List<Transaction> validTransactionsList) {
        this.validTransactionsList = validTransactionsList;
    }

    public List<InvalidTransaction> getInvalidTransactionList() {
        return invalidTransactionList;
    }

    public void setInvalidTransactionList(List<InvalidTransaction> invalidTransactionList) {
        this.invalidTransactionList = invalidTransactionList;
    }

    public void resetAll() {
        setTotalImported(0);
        setTotalValid(0);
        setTotalInvalid(0);
        setInvalidTransactionList(new ArrayList<>());
        setValidTransactionsList(new ArrayList<>());
    }


    public void validateTransactions() {
        resetAll();

        List<Transaction> transactionsList = TransactionDataStore.getInstance().getTransactionList();

        setTotalImported(transactionsList.size());
        for (Transaction transaction : transactionsList) {
            List<String> reasons = new ArrayList<>();

            if (!validateChecksum(transaction)) {
                reasons.add("Invalid Checksum");
            }
            if (validateItemCode(transaction.getItemCode())) {
                reasons.add("Invalid Item Code");
            }
            if (!validateItemPrice(transaction.getInternalPrice(), transaction.getDiscountPrice(), transaction.getSalePrice())) {
                reasons.add("Invalid price configuration");
            }

            if (reasons.isEmpty()) {
                validTransactionsList.add(transaction);
                totalValid++;
            } else {
                String combinedReason = String.join(" | ", reasons);
                invalidTransactionList.add(new InvalidTransaction(transaction, combinedReason));
                totalInvalid++;
            }
        }
    }

    private boolean validateChecksum(Transaction transaction) {
        String line = formatAsString(transaction);
        Integer calculatedChecksum = checksum.findChecksum(line);
        return calculatedChecksum == transaction.getChecksum();
    }

    private String formatAsString(Transaction t) {
        return String.format("%s%s%s%s%s", t.getItemCode(), t.getInternalPrice(), t.getDiscountPrice(), t.getSalePrice(), t.getQuantity());
    }

    private boolean validateItemCode(String itemCode) {
        return containsSpecialCharacter(itemCode);
    }

    private boolean containsSpecialCharacter(String str) {
        return !str.matches("[a-zA-Z0-9_]*");
    }

    private boolean validateItemPrice(Double internalPrice, Double discountPrice, Double salePrice) {
        return internalPrice >= 0 && discountPrice >= 0 && salePrice >= 0;
    }

    public void deleteInvalidTransaction(InvalidTransaction transaction) {
        TransactionDataStore.getInstance().deleteTransaction(transaction.getTransaction());
    }
}
