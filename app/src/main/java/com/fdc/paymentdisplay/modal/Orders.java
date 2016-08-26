package com.fdc.paymentdisplay.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mgupta4 on 8/26/2016.
 */
public class Orders {


    public List<Payment> getPayments() {
        return payments;
    }

    @SerializedName("payments")
    public List<Payment> payments;

}
