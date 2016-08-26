package com.fdc.paymentdisplay.modal;

import com.fdc.paymentdisplay.PaymentDetailsInterface;

import java.io.Serializable;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class PaymentRowDetails implements PaymentDetailsInterface , Serializable{

    private String paymentType ;
    private String paymentAmount ;
    private String transactionId ;
    private String employeeId ;
    private String currency ;
    private String totalAmount;
    private String paymentMode ;
    private String cardNumber ;
    private String timeOfTransaction;

    public PaymentRowDetails(String paymentType, String paymentAmount, String transactionId, String employeeId, String currency, String totalAmount, String timeOfTransaction , String paymentMode , String cardNumber) {
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.transactionId = transactionId;
        this.employeeId = employeeId;
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.timeOfTransaction = timeOfTransaction;
        this.paymentMode = paymentMode;
        this.cardNumber =cardNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }


    public String getPaymentMode() {
        return paymentMode;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
