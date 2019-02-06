package com.garageplug.unittest;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaxCalculatorTest {

    private TaxCalculator taxCalculator = new TaxCalculator();

    // valid positive tax rate
    @Test
    public void calculateTaxWhenTaxIsPositive() {
        // If income is 1000 and tax rate is 10, I should get 100 as tax value
        BigDecimal actual = taxCalculator.calculateTax(new BigDecimal(1000), new BigDecimal(10));
        BigDecimal expected = new BigDecimal(100);
        // verify that we got correct value
        Assert.assertEquals(expected, actual);
    }

    // tax rate negative
    @Test
    public void calculateTaxWhenTaxIsNegative() {
        // Should throw error if tax rate is negative
    }

    // tax rate is null
    @Test
    public void calculateTaxWhenTaxIsNull() {
        // should throw error if tax rate is null
    }

    // income is null
    @Test
    public void calculateTaxWhenIncomeIsNull() {
        // should throw error when income is null
    }
}