package com.garageplug.unittest;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxServiceTest {

    // You define the behavior of dependent classes, and check if your class is working correctly
    // in all those behaviors

    // I don't have to do 'new TaxCalculator' as Mockito will take care of it
    @Mock
    TaxCalculator taxCalculator;

    @Mock
    SmsSender smsSender;

    @Mock
    EmailSender emailSender;

    // why don't we just initialize taxService here  as 'new TaxService() ??'
    TaxService taxService;

    // This method runs before each test case
    @Before
    public void setup() {
        taxService = new TaxService(taxCalculator, emailSender, smsSender);
        // create some file, which is to be read in each test case
    }

    // runs after every test case
    @After
    public void tearDown() {
        // delete any temporary files..
        // clean up tasks..
    }

    // Positive case when everything works fine
    /**
     * TaxService should call the calculator to get the tax amount
     * Then it should call emailService to send email
     * Then it should call smsSender to send sms
     */
    @Test
    public void calculateTaxAndNotifyCustomer() {
        TaxRequest taxRequest = new TaxRequest("someEmail",
            "1234",
            new BigDecimal(1000),
            new BigDecimal(20));

        // set the behavior of the mocks -- all working fine
        // when taxCalcultion is done with these values
        // then return 200
        when(taxCalculator.calculateTax(eq(new BigDecimal(1000)), eq(new BigDecimal(20))))
            .thenReturn(new BigDecimal(200));

        // when email sending is called, with these inputs
        // then return xyz
        when(emailSender.sendEmail(eq("someEmail"), anyString(), any()))
            .thenReturn("xyz");

        when(smsSender.sendSms(eq("1234"), anyString(), any()))
            .thenReturn("123331");

        // Now make the actual call
        taxService.calculateTaxAndNotifyCustomer(taxRequest);

        // verify that taxService worked as expected in the given conditions
        // it should have called tax calculator once with correct arguments
        verify(taxCalculator, times(1)).calculateTax(eq(new BigDecimal(1000)), eq(new BigDecimal(20)));
        // it should have called email sender once with correct arguments
        verify(emailSender).sendEmail(eq("someEmail"), eq("Your tax returns have been filed."), eq(new BigDecimal(200)));
        // it should have called sms sender once with correct arguments
        verify(smsSender).sendSms(eq("1234"), eq("Your tax returns have been filed."), eq(new BigDecimal(200)));
    }

    /**
     * If email service fails, it should still go ahead and send sms
     */
    @Test
    public void calculateTaxAndNotifyCustomerWhenEmailFails() {
        // set the behavior of emailSender to throw exception
        //when(emailSender.sendEmail(eq("someEmail"), anyString(), any()))
        //     .thenThrow(new RuntimeException(""));
    }

    /**
     * If sms service fails, it should just ignore
     */
    @Test
    public void calculateTaxAndNotifyCustomerWhenSmsFails() {

    }
}