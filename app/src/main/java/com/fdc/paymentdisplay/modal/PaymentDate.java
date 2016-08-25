package com.fdc.paymentdisplay.modal;

import com.fdc.paymentdisplay.PaymentDetailsInterface;

import java.io.Serializable;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class PaymentDate implements PaymentDetailsInterface , Serializable {

    public String getDate() {
        return date;
    }

    private String date ;

    public PaymentDate(String date) {
        this.date = date;
    }
}
