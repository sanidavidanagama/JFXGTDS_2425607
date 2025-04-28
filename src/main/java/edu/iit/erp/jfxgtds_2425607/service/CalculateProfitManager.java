package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import edu.iit.erp.jfxgtds_2425607.model.TransactionDataStore;

import java.util.List;

public class CalculateProfitManager {

    private Double profits = 0.0;
    private Double loss = 0.0;
    private Double profitOrLoss = 0.0;

    public Double getProfits() {
        return profits;
    }

    public void setProfits(Double profits) {
        this.profits = profits;
    }

    public Double getLoss() {
        return loss;
    }

    public void setLoss(Double loss) {
        this.loss = loss;
    }

    public Double getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(Double profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public Integer deleteZeroProfitTransactions() {
        List<Transaction> transactionList =  TransactionDataStore.getInstance().getTransactionList();
        Integer beforeCount = transactionList.size();
        transactionList.removeIf(t -> t.getProfit() == 0);
        Integer afterCount = transactionList.size();
        TransactionDataStore.getInstance().setTransactionList(transactionList);
        return beforeCount - afterCount;
    }

    public Double findProfits() {
        double profits = 0.0;
        List<Transaction> transactionList = TransactionDataStore.getInstance().getTransactionList();
        for (Transaction t : transactionList) {
            if (t.getProfit() > 0) {
                profits += t.getProfit();
            }
        }
        setProfits(profits);
        return profits;
    }

    public Double findLosses() {
        double losses = 0.0;
        List<Transaction> transactionList = TransactionDataStore.getInstance().getTransactionList();
        for (Transaction t : transactionList) {
            if (t.getProfit() < 0) {
                losses += t.getProfit();
            }
        }
        setLoss(losses);
        return losses;
    }

    public Double findTotalProfitOrLoss() {
        Double profitOrLoss = getProfits() + getLoss();
        setProfitOrLoss(profitOrLoss);
        return profitOrLoss;
    }


}
