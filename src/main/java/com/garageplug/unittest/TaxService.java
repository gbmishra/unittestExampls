package com.garageplug.unittest;

import java.math.BigDecimal;

public class TaxService {

    private static final String NOTIFICATION = "Your tax returns have been filed.";

    private final TaxCalculator taxCalculator;
    private final EmailSender emailSender;
    private final SmsSender smsSender;

    public TaxService(TaxCalculator taxCalculator, EmailSender emailSender, SmsSender smsSender) {
        this.taxCalculator = taxCalculator;
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    public void calculateTaxAndNotifyCustomer(TaxRequest taxRequest) {
        BigDecimal tax = taxCalculator.calculateTax(taxRequest.getYearlyIncome(), taxRequest.getTaxRate());
        emailSender.sendEmail(taxRequest.getEmail(), NOTIFICATION, tax);
        smsSender.sendSms(taxRequest.getPhone(), NOTIFICATION, tax);
    }

}
