package com.fdc.paymentdisplay.modal;

import java.io.Serializable;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class UserInfo implements Serializable {

    private  String firstName ;
    private  String lastName ;
    private  String phoneNumber ;
    private  String address ;
    private  String favBook ;

    public UserInfo(String firstName , String lastName , String phoneNumber , String address , String favBook){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.favBook = favBook;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getFavBook() {
        return favBook;
    }


}
