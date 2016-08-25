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
    private String timeOfTransaction;

    public PaymentRowDetails(String paymentType, String paymentAmount, String transactionId, String employeeId, String currency, String totalAmount, String timeOfTransaction) {
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.transactionId = transactionId;
        this.employeeId = employeeId;
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.timeOfTransaction = timeOfTransaction;
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
}
