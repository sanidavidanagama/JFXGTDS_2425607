package edu.iit.erp.jfxgtds_2425607.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditTransactionManagerTest {

    private EditTransactionManager editTransactionManager;

    @Before
    public void setUp() {
        editTransactionManager = new EditTransactionManager();
    }

    @Test
    public void testValidateItemCode_ValidCode() {
        String result = editTransactionManager.validateItemCode("ITEM001");
        assertEquals("", result);
    }

    @Test
    public void testValidateItemCode_InvalidCode() {
        String result = editTransactionManager.validateItemCode("ITEM#001");
        assertEquals("Item code cannot contain special characters", result);
    }

    @Test
    public void testValidateItemPrice_ValidPrice() {
        String result = editTransactionManager.validateItemPrice("25.50");
        assertEquals("", result);
    }

    @Test
    public void testValidateItemPrice_NegativePrice() {
        String result = editTransactionManager.validateItemPrice("-10.00");
        assertEquals("Price can't be negative.", result);
    }

    @Test
    public void testValidateItemPrice_InvalidNumberFormat() {
        String result = editTransactionManager.validateItemPrice("abc");
        assertEquals("Enter a valid number.", result);
    }

    @Test
    public void testValidateItemPrice_NegativeSymbolButInvalid() {
        String result = editTransactionManager.validateItemPrice("-abc");
        assertEquals("Enter a valid number. Negative values are not allowed.", result);
    }

    @Test
    public void testValidateQuantity_ValidQuantity() {
        String result = editTransactionManager.validateQuantity("10");
        assertEquals("", result);
    }

    @Test
    public void testValidateQuantity_NegativeQuantity() {
        String result = editTransactionManager.validateQuantity("-5");
        assertEquals("Price can't be negative.", result);
    }

    @Test
    public void testValidateQuantity_InvalidNumberFormat() {
        String result = editTransactionManager.validateQuantity("abc");
        assertEquals("Enter a valid number.", result);
    }

    @Test
    public void testValidateQuantity_NegativeSymbolButInvalid() {
        String result = editTransactionManager.validateQuantity("-abc");
        assertEquals("Enter a valid number. Negative values are not allowed.", result);
    }

}
