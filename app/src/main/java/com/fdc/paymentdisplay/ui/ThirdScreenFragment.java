package com.fdc.paymentdisplay.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.constant.Constants;
import com.fdc.paymentdisplay.modal.Payment;
import com.fdc.paymentdisplay.modal.UserInfo;
import com.fdc.paymentdisplay.util.Utility;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class ThirdScreenFragment extends Fragment {

    private View view;
    private TextView userName;
    private TextView useraddress;
    private TextView favBook;
    private TextView transactionId;
    private TextView employeeId;
    private TextView totalAmount;
    private TextView transactionTime;
    private TextView paymentType;
    private UserInfo userInfo;
    private Payment payment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.screenthird, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userInfo = (UserInfo) bundle.get(Constants.USERINFO);
            payment = (Payment) bundle.get(Constants.TRANSACTIONINFO);
        }
        initialize();
        bindData();
        return view;
    }

    private void bindData() {
        userName.setText("Hello " + userInfo.getFirstName() + " " + userInfo.getLastName());
        useraddress.setText(userInfo.getAddress() + " | M : " + userInfo.getPhoneNumber());
        favBook.setText("Favorite  Book : " + userInfo.getFavBook());
        transactionId.setText(payment.id);
        employeeId.setText(payment.employeeId);
        if(payment.order!=null){
            totalAmount.setText(payment.order.currency + " " + payment.order.total);
        }
        String createdDateforDetails = Utility.getDateByFormat(payment.createdTime, Constants.DETAIL_DATEFORMATTER);
        transactionTime.setText(createdDateforDetails);
        if(payment.tender!=null){
            if (payment.tender.label.contains("Card") && payment.cardTransaction!=null)
                paymentType.setText(payment.cardTransaction.cardType + " " + payment.cardTransaction.last4);
            else {
                paymentType.setText(payment.tender.label);
            }
        }

    }

    private void initialize() {
        userName = (TextView) view.findViewById(R.id.greetuserlbl);
        useraddress = (TextView) view.findViewById(R.id.addressphonenumberlbl);
        favBook = (TextView) view.findViewById(R.id.favbook);
        transactionId = (TextView) view.findViewById(R.id.transactionidvalue);
        employeeId = (TextView) view.findViewById(R.id.employeeidvalue);
        totalAmount = (TextView) view.findViewById(R.id.totalamountvalue);
        transactionTime = (TextView) view.findViewById(R.id.createdtimeidvalue);
        paymentType = (TextView) view.findViewById(R.id.paymenttypevalue);
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
