package com.fdc.paymentdisplay.modal;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/26/2016.
 */
public class Payment implements Serializable , PaymentDetailsInterface {

    @SerializedName("id")
    public String id;
    @SerializedName("amount")
    public Integer amount;
    @SerializedName("createdTime")
    public Long createdTime;
    @SerializedName("clientCreatedTime")
    public Long clientCreatedTime;
    @SerializedName("modifiedTime")
    public Long modifiedTime;
    @SerializedName("result")
    public String result;
    @SerializedName("orderId")
    public String orderId;
    @SerializedName("employeeId")
    public String employeeId;
    @SerializedName("cardTransaction")
    public CardTransaction cardTransaction;
    @SerializedName("order")
    public Order_ order;
    @SerializedName("tender")
    public Tender tender;
}
