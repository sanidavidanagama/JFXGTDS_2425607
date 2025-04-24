package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ViewTransactionsManager {

    private String fileName;

    private List<Transaction> transactionList= new ArrayList<>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void loadData() {
        setFileName(TransactionDataStore.getInstance().getFileName());
        setTransactionList(TransactionDataStore.getInstance().getTransactionList());
    }

}
