
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

    public static class Orders {

        @SerializedName("payments")
        public List<Payment> payments;

        public static class Payment implements PaymentDetailsInterface , Serializable{
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

            public static class Order_ {
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

            public static class CardTransaction {
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


            public static class Tender {
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
        }
    }

}
