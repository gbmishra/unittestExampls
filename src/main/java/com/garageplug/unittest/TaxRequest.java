package com.garageplug.unittest;

import java.math.BigDecimal;

public class TaxRequest {

    private String email;
    private String phone;
    private BigDecimal yearlyIncome;
    private BigDecimal taxRate;

    public TaxRequest(String email, String phone, BigDecimal yearlyIncome, BigDecimal taxRate) {
        this.email = email;
        this.phone = phone;
        this.yearlyIncome = yearlyIncome;
        this.taxRate = taxRate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public BigDecimal getYearlyIncome() {
        return yearlyIncome;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
