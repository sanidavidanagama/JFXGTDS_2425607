package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.model.InvalidTransaction;
import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import edu.iit.erp.jfxgtds_2425607.model.TransactionDataStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ValidateTransactionsManagerTest {

    private ValidateTransactionsManager validateTransactionsManager;

    @Before
    public void setUp() {
        validateTransactionsManager = new ValidateTransactionsManager();
    }

    @After
    public void tearDown() {
        TransactionDataStore.getInstance().setTransactionList(null);
    }

    @Test
    public void testValidateTransactions_AllValid() {
        Transaction validTransaction = new Transaction(
                "ITEM001", 100.0, 90.0, 95.0, 10,
                new edu.iit.erp.jfxgtds_2425607.utils.Checksum().findChecksum("ITEM001100.090.095.010")
        );
        List<Transaction> transactionList = List.of(validTransaction);
        TransactionDataStore.getInstance().setTransactionList(transactionList);

        validateTransactionsManager.validateTransactions();

        assertEquals(1, validateTransactionsManager.getTotalImported().intValue());
        assertEquals(1, validateTransactionsManager.getTotalValid().intValue());
        assertEquals(0, validateTransactionsManager.getTotalInvalid().intValue());
        assertEquals(1, validateTransactionsManager.getValidTransactionsList().size());
        assertEquals(0, validateTransactionsManager.getInvalidTransactionList().size());
    }

    @Test
    public void testValidateTransactions_WithInvalidChecksum() {
        Transaction invalidChecksumTransaction = new Transaction(
                "ITEM002", 200.0, 180.0, 190.0, 5,
                99999 // wrong checksum
        );
        List<Transaction> transactionList = List.of(invalidChecksumTransaction);
        TransactionDataStore.getInstance().setTransactionList(transactionList);

        validateTransactionsManager.validateTransactions();

        assertEquals(1, validateTransactionsManager.getTotalImported().intValue());
        assertEquals(0, validateTransactionsManager.getTotalValid().intValue());
        assertEquals(1, validateTransactionsManager.getTotalInvalid().intValue());
        assertEquals(0, validateTransactionsManager.getValidTransactionsList().size());
        assertEquals(1, validateTransactionsManager.getInvalidTransactionList().size());

        InvalidTransaction invalidTransaction = validateTransactionsManager.getInvalidTransactionList().get(0);
        assertTrue(invalidTransaction.getReason().contains("Invalid Checksum"));
    }

    @Test
    public void testValidateTransactions_WithInvalidItemCode() {
        Transaction invalidItemCodeTransaction = new Transaction(
                "ITEM#03", 300.0, 250.0, 275.0, 3,
                new edu.iit.erp.jfxgtds_2425607.utils.Checksum().findChecksum("ITEM#03300.0250.0275.03")
        );
        List<Transaction> transactionList = Arrays.asList(invalidItemCodeTransaction);
        TransactionDataStore.getInstance().setTransactionList(transactionList);

        validateTransactionsManager.validateTransactions();

        assertEquals(1, validateTransactionsManager.getTotalImported().intValue());
        assertEquals(0, validateTransactionsManager.getTotalValid().intValue());
        assertEquals(1, validateTransactionsManager.getTotalInvalid().intValue());
        assertEquals(1, validateTransactionsManager.getInvalidTransactionList().size());

        InvalidTransaction invalidTransaction = validateTransactionsManager.getInvalidTransactionList().get(0);
        assertTrue(invalidTransaction.getReason().contains("Invalid Item Code"));
    }

    @Test
    public void testValidateTransactions_WithInvalidPrices() {
        Transaction invalidPriceTransaction = new Transaction(
                "ITEM004", -100.0, 90.0, 95.0, 10,
                new edu.iit.erp.jfxgtds_2425607.utils.Checksum().findChecksum("ITEM004-100.090.095.010")
        );
        List<Transaction> transactionList = Arrays.asList(invalidPriceTransaction);
        TransactionDataStore.getInstance().setTransactionList(transactionList);

        validateTransactionsManager.validateTransactions();

        assertEquals(1, validateTransactionsManager.getTotalImported().intValue());
        assertEquals(0, validateTransactionsManager.getTotalValid().intValue());
        assertEquals(1, validateTransactionsManager.getTotalInvalid().intValue());
        assertEquals(1, validateTransactionsManager.getInvalidTransactionList().size());

        InvalidTransaction invalidTransaction = validateTransactionsManager.getInvalidTransactionList().get(0);
        assertTrue(invalidTransaction.getReason().contains("Invalid price configuration"));
    }
}
