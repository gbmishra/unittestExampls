package com.garageplug.unittest;

import java.math.BigDecimal;
import java.math.MathContext;

public class TaxCalculator {

    public BigDecimal calculateTax(BigDecimal income, BigDecimal taxRate) {
        // if taxRate is -ve then throw exception
        // if tax is null throw exception
        // calculate % and return
        return income.multiply(taxRate).divide(BigDecimal.valueOf(100), MathContext.DECIMAL32);
    }

}
