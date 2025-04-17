package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;

import java.util.ArrayList;
import java.util.List;

/* Design Pattern - Singleton Design Pattern */

public class TransactionDataStore {
    private static TransactionDataStore instance;

    private List<Transaction> transactionList = new ArrayList<>();

    private String fileName;

    private Double totalProfits;

    private Double totalLoss;

    private Double profitOrLoss;


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

    public void clearTransactions() {
        transactionList.clear();
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








}
