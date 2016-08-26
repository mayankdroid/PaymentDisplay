
package com.fdc.paymentdisplay.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.google.gson.annotations.SerializedName;
import com.fdc.paymentdisplay.util.Struct;

public class OrderModal implements Serializable{

    @SerializedName("orders")
    public List<Orders> orders ;

}
