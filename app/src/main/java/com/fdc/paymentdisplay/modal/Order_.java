package com.fdc.paymentdisplay.modal;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mgupta4 on 8/26/2016.
 */
public class Order_ implements PaymentDetailsInterface ,Serializable {

    @SerializedName("id")
    public String id;
    @SerializedName("currency")
    public String currency;
    @SerializedName("employeeId")
    public String employeeId;
    @SerializedName("total")
    public Integer total;
    @SerializedName("taxRemoved")
    public Boolean taxRemoved;
    @SerializedName("isVat")
    public Boolean isVat;
    @SerializedName("state")
    public String state;
    @SerializedName("manualTransaction")
    public Boolean manualTransaction;
    @SerializedName("groupLineItems")
    public Boolean groupLineItems;
    @SerializedName("testMode")
    public Boolean testMode;
    @SerializedName("createdTime")
    public Long createdTime;
    @SerializedName("clientCreatedTime")
    public Long clientCreatedTime;
    @SerializedName("modifiedTime")
    public Long modifiedTime;
    @SerializedName("deviceId")
    public String deviceId;
}
