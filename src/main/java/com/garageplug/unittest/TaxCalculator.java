package com.garageplug.unittest;

import java.math.BigDecimal;
import java.math.MathContext;

public class TaxCalculator {

    public BigDecimal calculateTax(BigDecimal income, BigDecimal taxRate) {
        return income.multiply(taxRate).divide(BigDecimal.valueOf(100), MathContext.DECIMAL32);
    }

}
