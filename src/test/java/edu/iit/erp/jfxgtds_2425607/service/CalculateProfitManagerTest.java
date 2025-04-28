package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import edu.iit.erp.jfxgtds_2425607.model.TransactionDataStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalculateProfitManagerTest {

    private CalculateProfitManager calculateProfitManager;

    @Before
    public void setUp() {
        calculateProfitManager = new CalculateProfitManager();
        TransactionDataStore.getInstance().setTransactionList(Arrays.asList());
    }

    @Test
    public void testDeleteZeroProfitTransactions() {
        // Sample data for transactions
        Transaction t1 = new Transaction("item001", 50.0, 10.0, 60.0, 1, 123);
        Transaction t2 = new Transaction("item002", 30.0, 5.0, 40.0, 1, 124);
        Transaction t3 = new Transaction("item003", 25.0, 5.0, 20.0, 1, 125);  // profit = 0

        List<Transaction> transactions = new ArrayList<>(Arrays.asList(t1, t2, t3));

        // Deleting transactions with zero profit
        transactions.removeIf(t -> t.getProfit() == 0);

        // After removal, the list should contain only two transactions
        assertEquals(2, transactions.size());
    }


    @Test
    public void testFindProfits() {
        // Sample data for transactions with profit
        Transaction t1 = new Transaction("item001", 50.0, 10.0, 60.0, 1, 123);  // profit = 10
        Transaction t2 = new Transaction("item002", 30.0, 5.0, 40.0, 1, 124);   // profit = 5
        Transaction t3 = new Transaction("item003", 20.0, 5.0, 30.0, 1, 125);   // profit = 5

        List<Transaction> transactions = new ArrayList<>(Arrays.asList(t1, t2, t3));

        // Expected Logic
        List<Transaction> profits = new ArrayList<>();
        double profitAmount = 0;
        for (Transaction t : transactions) {
            if (t.getProfit() > 0.0) {
                profitAmount += t.getProfit();
                profits.add(t);
            }
        }

        // Actual
        TransactionDataStore.getInstance().setTransactionList(transactions);
        Double profit = calculateProfitManager.findProfits();

        // Assert that the total profit matches the expected value
        assertEquals(profitAmount, profit, 0.01);
    }



    @Test
    public void testFindLosses() {
        // Sample data for transactions with loss
        Transaction t1 = new Transaction("item001", 50.0, 10.0, 60.0, 1, 123);  // profit = 10
        Transaction t2 = new Transaction("item002", 40.0, 5.0, 30.0, 1, 124);   // loss = -15
        Transaction t3 = new Transaction("item003", 60.0, 20.0, 40.0, 1, 125);  // loss = -20

        List<Transaction> transactions = new ArrayList<>(Arrays.asList(t1, t2, t3));

        // Expected Logic
        List<Transaction> losses = new ArrayList<>();
        double lossAmount = 0;
        for (Transaction t : transactions) {
            if (t.getProfit() < 0.0) {
                lossAmount += t.getProfit();
                losses.add(t);
            }
        }
        // Actual
        TransactionDataStore.getInstance().setTransactionList(Arrays.asList(t1, t2, t3));
        Double loss = calculateProfitManager.findLosses();
        assertEquals(loss, lossAmount, 0.01);
    }


    @Test
    public void testFindTotalProfitOrLoss() {
        calculateProfitManager.setProfits(200.0);
        calculateProfitManager.setLoss(-50.0);

        double total = calculateProfitManager.findTotalProfitOrLoss();

        assertEquals(150.0, total, 0.001);
    }
}
