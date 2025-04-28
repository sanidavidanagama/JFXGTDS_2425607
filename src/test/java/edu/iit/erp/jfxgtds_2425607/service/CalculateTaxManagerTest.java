package edu.iit.erp.jfxgtds_2425607.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculateTaxManagerTest {

    private CalculateTaxManager calculateTaxManager;

    @Before
    public void setUp() {
        calculateTaxManager = new CalculateTaxManager();
    }

    @Test
    public void testValidateTaxInput_EmptyField() {
        String result = calculateTaxManager.validateTaxInput("");
        assertEquals("Field is empty", result);
    }

    @Test
    public void testValidateTaxInput_NegativeTax() {
        String result = calculateTaxManager.validateTaxInput("-5");
        assertEquals("Tax cannot be negative", result);
    }

    @Test
    public void testValidateTaxInput_InvalidTextInput() {
        String result = calculateTaxManager.validateTaxInput("abc123");
        assertEquals("Invalid tax amount. Cannot contain text", result);
    }

    @Test
    public void testValidateTaxInput_ValidTax() {
        String result = calculateTaxManager.validateTaxInput("15.5");
        assertEquals("", result);  // Valid input should return empty string
    }

    @Test
    public void testValidateTaxInput_ZeroTax() {
        String result = calculateTaxManager.validateTaxInput("0");
        assertEquals("", result);
    }
}
