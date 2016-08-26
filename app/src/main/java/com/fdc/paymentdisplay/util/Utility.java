package com.fdc.paymentdisplay.util;

import com.fdc.paymentdisplay.modal.OrderModal;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class Utility {

    public static String loadJSONFromAsset(final Context context, String JSONFile) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(JSONFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static Serializable getObjectFromJSONString(String jsonStr,

                                                       Class<?> modalClass) throws JsonSyntaxException {
        Serializable pojoObject = null;
        Gson gson = new Gson();
        try {
            pojoObject = (Serializable) gson.fromJson(jsonStr,
                    modalClass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pojoObject;
    }


    public static String getDateByFormat(long date, String dateFromat) {

        Date dObj = new Date(date);
        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFromat);
        try {
            return sdfDate.format(dObj);
        } catch (Exception pe) {
            return "Date Error";
        }
    }

    public static boolean validateField(View view) {

        if (view != null && view instanceof EditText && ((EditText) view).getText() != null && !((EditText) view).getText().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean validPhoneNumber(View view) {
        if (view != null && view instanceof EditText && ((EditText) view).getText() != null && !((EditText) view).getText().equals("")) {
            return android.util.Patterns.PHONE.matcher(((EditText) view).getText()).matches();
        }
        return false;
    }

    public static Map<String, List<OrderModal.Orders.Payment>> groupPayments(ArrayList<OrderModal.Orders.Payment> list){
        Map<String, List<OrderModal.Orders.Payment>> map = new TreeMap<String, List<OrderModal.Orders.Payment>>();
        for(int i=0;i<list.size();i++){
            OrderModal.Orders.Payment p = (OrderModal.Orders.Payment) list.get(i);
            String key = getDateByFormat(p.createdTime, "yyyy-MM-dd");
            if(map.containsKey(key)){
                ArrayList<OrderModal.Orders.Payment> old = (ArrayList<OrderModal.Orders.Payment>)map.get(key);
                old.add(p);
            }else{
                ArrayList<OrderModal.Orders.Payment> n = new ArrayList<OrderModal.Orders.Payment>();
                n.add(p);
                map.put(key, n);
            }
        }
        return map;
    }
    }
