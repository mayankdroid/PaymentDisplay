package com.fdc.paymentdisplay.modal;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mgupta4 on 8/26/2016.
 */
public class CardTransaction implements Serializable ,PaymentDetailsInterface {

    @SerializedName("cardType")
    public String cardType;
    @SerializedName("entryType")
    public String entryType;
    @SerializedName("last4")
    public String last4;
    @SerializedName("type")
    public String type;
    @SerializedName("authCode")
    public String authCode;
    @SerializedName("referenceId")
    public String referenceId;
    @SerializedName("state")
    public String state;
}
