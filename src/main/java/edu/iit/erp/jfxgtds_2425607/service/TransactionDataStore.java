package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDataStore {
    private static TransactionDataStore instance;

    private List<Transaction> transactionList = new ArrayList<>();
    private String fileName;

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

    public boolean updateTransaction(int index, Transaction newTransaction) {
        if (index >= 0 && index < transactionList.size()) {
            transactionList.set(index, newTransaction);
            return true;
        }
        return false;
    }

    public int findTransactionIndex(Transaction transaction) {
        return transactionList.indexOf(transaction);
    }



}
