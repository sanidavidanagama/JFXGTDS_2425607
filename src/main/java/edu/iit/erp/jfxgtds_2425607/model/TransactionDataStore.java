package edu.iit.erp.jfxgtds_2425607.model;

import java.util.ArrayList;
import java.util.List;

/* Singleton Class */

public class TransactionDataStore {
    private static TransactionDataStore instance;

    private List<Transaction> transactionList = new ArrayList<>();

    private String fileName = "";

    private String absoluteFilePath = "";

    private Double totalProfits = 0.0;

    private Double totalLoss = 0.0;

    private Double profitOrLoss = 0.0;

    private TransactionDataStore() {}

    public static TransactionDataStore getInstance() {
        if (instance == null) {
            instance = new TransactionDataStore();
        }
        return instance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> list) {
        this.transactionList = list;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getTotalProfits() {
        return totalProfits;
    }

    public void setTotalProfits(Double totalProfits) {
        this.totalProfits = totalProfits;
    }

    public Double getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(Double totalLoss) {
        this.totalLoss = totalLoss;
    }

    public Double getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(Double profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public void setAbsoluteFilePath(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }

    public boolean deleteTransactionByIndex(int index) {
        if (index >= 0 && index < transactionList.size()) {
            transactionList.remove(index);
            return true;
        }
        return false;
    }

    public void deleteTransaction(Transaction transaction) {
        transactionList.remove(transaction);
    }

    public void updateTransaction(int index, Transaction newTransaction) {
        if (index >= 0 && index < transactionList.size()) {
            transactionList.set(index, newTransaction);
        }
    }

    public int findTransactionIndex(Transaction transaction) {
        return transactionList.indexOf(transaction);
    }

    public void reset() {
        transactionList.clear();
        fileName = "";
        totalProfits = 0.0;
        totalLoss = 0.0;
        profitOrLoss = 0.0;
    }

}
