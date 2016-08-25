package com.fdc.paymentdisplay.ui;

import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.modal.PaymentRowDetails;
import com.fdc.paymentdisplay.modal.UserInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class ThirdScreenFragment extends Fragment{

    private View view ;
    private TextView userName ;
    private TextView useraddress;
    private TextView favBook ;
    private TextView transactionId;
    private TextView employeeId;
    private TextView totalAmount;
    private TextView transactionTime;
    private UserInfo userInfo ;
    private PaymentRowDetails paymentRowDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.screenthird, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null){
            userInfo = (UserInfo) bundle.get("formInfo");
            paymentRowDetails = (PaymentRowDetails)bundle.get("transactionInfo");
        }
        initialize();
        bindData();
        return view ;
    }

    private void bindData() {
        userName.setText("Hello " + userInfo.getFirstName() + " " + userInfo.getLastName());
        useraddress.setText(userInfo.getAddress() + " | M : " + userInfo.getPhoneNumber());
        favBook.setText("favorate book : " +  userInfo.getFavBook());
        transactionId.setText(paymentRowDetails.getTransactionId());
        employeeId.setText(paymentRowDetails.getEmployeeId());
        totalAmount.setText(paymentRowDetails.getCurrency() + " " + paymentRowDetails.getTotalAmount());
        transactionTime.setText(paymentRowDetails.getTimeOfTransaction());
    }

    private void initialize() {
        userName = (TextView)view.findViewById(R.id.greetuserlbl);
        useraddress = (TextView)view.findViewById(R.id.addressphonenumberlbl);
        favBook = (TextView)view.findViewById(R.id.favbook);
        transactionId = (TextView)view.findViewById(R.id.transactionidvalue);
        employeeId = (TextView)view.findViewById(R.id.employeeidvalue);
        totalAmount = (TextView)view.findViewById(R.id.totalamountvalue);
        transactionTime = (TextView)view.findViewById(R.id.createdtimeidvalue);
    }

    public static ThirdScreenFragment newInstance(Bundle bundle) {

        ThirdScreenFragment fragment = new ThirdScreenFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }
}
