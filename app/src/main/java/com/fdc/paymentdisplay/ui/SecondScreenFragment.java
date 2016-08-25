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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class SecondScreenFragment extends Fragment {
    private View view ;
    private ListView listview;
    private PaymentCustomAdaptor mPaymentCustomAdaptor;
    private OrderModal order;
    private List<PaymentDetailsInterface> paymentDetailsInterfaceList = new ArrayList<PaymentDetailsInterface>();
    private ProgressDialog progress ;
    private UserInfo userInfo ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.screensecond, container, false);
        Bundle bundle = getArguments();
        if(bundle !=null){
            userInfo = (UserInfo) bundle.getSerializable("formInfo");
        }

        initializeViews();
        FetchLocalJson fetchLocalJson = new FetchLocalJson();
        fetchLocalJson.execute("paymentdetails.json");
        return view ;
    }

    private void initializeAdapters() {
        mPaymentCustomAdaptor = new PaymentCustomAdaptor(getActivity(), paymentDetailsInterfaceList);
        listview.setAdapter(mPaymentCustomAdaptor);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PaymentDetailsInterface paymentDetailsInterface = paymentDetailsInterfaceList.get(position);
                if(paymentDetailsInterface instanceof PaymentRowDetails){
                    Bundle userInfoBundle = new Bundle();
                    userInfoBundle.putSerializable("formInfo" , userInfo);
                    userInfoBundle.putSerializable("transactionInfo" , (PaymentRowDetails)paymentDetailsInterface);
                    ThirdScreenFragment thirdScreenFragment = ThirdScreenFragment.newInstance(userInfoBundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , thirdScreenFragment ).addToBackStack(ThirdScreenFragment.class.getSimpleName()).commit();
                }
            }
        });
    }



    private void initializeViews() {
        listview = (ListView)view.findViewById(R.id.payments_listview);
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


    private class FetchLocalJson extends AsyncTask<String, Void, Serializable> {

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity());
            progress.setMessage("Loading");
            progress.show();
            super.onPreExecute();
        }

        @Override
        protected Serializable doInBackground(String... params) {

            String response = Utility.loadJSONFromAsset(getActivity(), params[0]);
            OrderModal order = (OrderModal) Utility.getObjectFromJSONString(response, OrderModal.class);

            return order;
        }

        @Override
        protected void onPostExecute(Serializable orderModal) {
            populatePaymentDetails(orderModal);
            initializeAdapters();
            if(progress!=null && progress.isShowing()){
                progress.dismiss();
            }
            super.onPostExecute(order);
        }
    }

    private void populatePaymentDetails(Serializable orderModal) {
        Set<Long> key = new HashSet<Long>();

        try {
            if(orderModal!=null){

                OrderModal ordermodals = (OrderModal) orderModal ;
                List<OrderModal.Orders.Payment> payments = new ArrayList<OrderModal.Orders.Payment>() ;
                for(OrderModal.Orders orderObj : ordermodals.orders){
                    payments.addAll(orderObj.payments) ;
                }

                if(payments!=null){
                    for(OrderModal.Orders.Payment payment :payments){
                        key.add(payment.createdTime);
                    }
                    List<Long> keyList = new ArrayList<Long>(key);
                    Collections.sort(keyList);
                    paymentDetailsInterfaceList.clear();
                    for(Long date :keyList){
                        String paymentcreatedDate = Utility.getDateByFormat(date,"yyyy-MM-dd");
                        paymentDetailsInterfaceList.add(new PaymentDate(paymentcreatedDate));
                        for (OrderModal.Orders.Payment payment : payments) {
                            if (date==payment.createdTime) {
                                String createdDateforDetails = Utility.getDateByFormat(date,"MMMM dd,yyyy");
                                if(payment.cardTransaction!=null){
                                    paymentDetailsInterfaceList.add(new PaymentRowDetails(payment.cardTransaction.cardType , String.valueOf(payment.amount) , payment.id ,payment.employeeId,payment.order.currency,String.valueOf(payment.order.total),createdDateforDetails));
                                }else{
                                    paymentDetailsInterfaceList.add(new PaymentRowDetails("Info NA" , String.valueOf(payment.amount) , payment.id ,payment.employeeId,payment.order.currency,String.valueOf(payment.order.total),createdDateforDetails));
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
