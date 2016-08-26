package com.fdc.paymentdisplay.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.adaptor.PaymentCustomAdaptor;
import com.fdc.paymentdisplay.constant.Constants;
import com.fdc.paymentdisplay.modal.OrderModal;
import com.fdc.paymentdisplay.modal.PaymentDate;
import com.fdc.paymentdisplay.modal.UserInfo;
import com.fdc.paymentdisplay.util.Utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        view = inflater.inflate(R.layout.screensecond, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userInfo = (UserInfo) bundle.getSerializable(Constants.USERINFO);
            orderModal = (OrderModal) bundle.getSerializable(Constants.ORDERS);
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
                if (paymentDetailsInterface instanceof OrderModal.Orders.Payment) {
                    Bundle userInfoBundle = new Bundle();
                    userInfoBundle.putSerializable(Constants.USERINFO, userInfo);
                    userInfoBundle.putSerializable(Constants.TRANSACTIONINFO, ((Serializable) paymentDetailsInterface));
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
                        paymentDetailsInterfaceList.addAll(datePaymentMap.get(headerDate));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
