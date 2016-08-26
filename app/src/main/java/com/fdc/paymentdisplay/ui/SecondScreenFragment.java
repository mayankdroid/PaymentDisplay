package com.fdc.paymentdisplay.ui;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.adaptor.PaymentCustomAdaptor;
import com.fdc.paymentdisplay.modal.OrderModal;
import com.fdc.paymentdisplay.modal.PaymentDate;
import com.fdc.paymentdisplay.modal.PaymentRowDetails;
import com.fdc.paymentdisplay.modal.UserInfo;
import com.fdc.paymentdisplay.util.Utility;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class SecondScreenFragment extends Fragment {
    private View view;
    private ListView listview;
    private PaymentCustomAdaptor mPaymentCustomAdaptor;
    private OrderModal orderModal;
    private List<PaymentDetailsInterface> paymentDetailsInterfaceList = new ArrayList<PaymentDetailsInterface>();
    private ProgressDialog progress;
    private UserInfo userInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.screensecond, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userInfo = (UserInfo) bundle.getSerializable("formInfo");
            orderModal = (OrderModal) bundle.getSerializable("orders");
        }

        initializeViews();
        if (orderModal != null) {
            populatePaymentDetails();
            initializeAdapters();
        }
        return view;
    }

    private void initializeAdapters() {
        mPaymentCustomAdaptor = new PaymentCustomAdaptor(getActivity(), paymentDetailsInterfaceList);
        listview.setAdapter(mPaymentCustomAdaptor);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PaymentDetailsInterface paymentDetailsInterface = paymentDetailsInterfaceList.get(position);
                if (paymentDetailsInterface instanceof PaymentRowDetails) {
                    Bundle userInfoBundle = new Bundle();
                    userInfoBundle.putSerializable("formInfo", userInfo);
                    userInfoBundle.putSerializable("transactionInfo", (PaymentRowDetails) paymentDetailsInterface);
                    ThirdScreenFragment thirdScreenFragment = ThirdScreenFragment.newInstance(userInfoBundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, thirdScreenFragment).addToBackStack(ThirdScreenFragment.class.getSimpleName()).commit();
                }
            }
        });
    }


    private void initializeViews() {
        listview = (ListView) view.findViewById(R.id.payments_listview);
    }

    public static SecondScreenFragment newInstance(Bundle bundle) {

        SecondScreenFragment fragment = new SecondScreenFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    private void populatePaymentDetails() {
        Map<String, List<OrderModal.Orders.Payment>> datePaymentMap;
        List<OrderModal.Orders.Payment> payments = new ArrayList<>();
        try {
            if (orderModal != null) {

                for (OrderModal.Orders orderObj : ((OrderModal) orderModal).orders) {
                    payments.addAll(orderObj.payments);
                }
                if (payments != null) {
                    datePaymentMap = Utility.groupPayments((ArrayList<OrderModal.Orders.Payment>) payments);
                    paymentDetailsInterfaceList.clear();
                    for (String headerDate : datePaymentMap.keySet()) {
                        //creating the header in the adapter list
                        paymentDetailsInterfaceList.add(new PaymentDate(headerDate));
                        List<OrderModal.Orders.Payment> subPayments = datePaymentMap.get(headerDate);
                        for (OrderModal.Orders.Payment payment : subPayments) {
                            String createdDateforDetails = Utility.getDateByFormat(payment.createdTime, "MMMM dd,yyyy");
                            if (payment.cardTransaction != null) {
                                if (payment.order != null && payment.tender != null) {
                                    paymentDetailsInterfaceList.add(new PaymentRowDetails(payment.cardTransaction.cardType, String.valueOf(payment.amount), payment.id, payment.employeeId, payment.order.currency, String.valueOf(payment.order.total), createdDateforDetails, payment.tender.label, payment.cardTransaction.last4));
                                } else {
                                    paymentDetailsInterfaceList.add(new PaymentRowDetails(payment.cardTransaction.cardType, String.valueOf(payment.amount), payment.id, payment.employeeId, "Info NA", "Info NA", createdDateforDetails, "Info NA", payment.cardTransaction.last4));
                                }
                            } else {
                                paymentDetailsInterfaceList.add(new PaymentRowDetails(payment.tender.label, String.valueOf(payment.amount), payment.id, payment.employeeId, payment.order.currency, String.valueOf(payment.order.total), createdDateforDetails, payment.tender.label, "Info NA"));
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
