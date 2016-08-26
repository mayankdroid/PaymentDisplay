package com.fdc.paymentdisplay.modal;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mgupta4 on 8/26/2016.
 */
public class Tender implements Serializable , PaymentDetailsInterface {

    @SerializedName("id")
    public String id;
    @SerializedName("editable")
    public Boolean editable;
    @SerializedName("labelKey")
    public String labelKey;
    @SerializedName("label")
    public String label;
    @SerializedName("opensCashDrawer")
    public Boolean opensCashDrawer;
    @SerializedName("enabled")
    public Boolean enabled;
    @SerializedName("visible")
    public Boolean visible;
}
