package com.fdc.paymentdisplay.ui;

import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.modal.OrderModal;
import com.fdc.paymentdisplay.modal.UserInfo;
import com.fdc.paymentdisplay.util.Utility;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class FirstScreenFragment extends Fragment implements View.OnClickListener {
    private View view ;
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText address;
    private EditText favBook;
    private Button nextButton;
    private UserInfo userInfo;
    private ProgressDialog progress;
    private OrderModal orderModal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.screenfirst, container, false);
        return view;
    }

    public static FirstScreenFragment newInstance(Bundle bundle) {

        FirstScreenFragment fragment = new FirstScreenFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        initializeView();
        super.onResume();
    }

    private void initializeView() {
        firstName = (EditText) view.findViewById(R.id.firstname_et);
        lastName = (EditText) view.findViewById(R.id.lastname_et);
        phoneNumber = (EditText) view.findViewById(R.id.phonenumber_et);
        address = (EditText) view.findViewById(R.id.address_et);
        favBook = (EditText) view.findViewById(R.id.favoritebook_et);
        nextButton = (Button)view.findViewById(R.id.nextbutton);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.nextbutton:
                FetchLocalJson fetchLocalJson = new FetchLocalJson();
                fetchLocalJson.execute("paymentdetails.json");
        }
    }

    private void validateForm() {

        if(Utility.validateField(firstName) && Utility.validateField(lastName) && Utility.validateField(address) && Utility.validateField(favBook) && Utility.validPhoneNumber(phoneNumber)){
            String firstNameValue =  firstName.getText().toString();
            String lastNameValue=  lastName.getText().toString();
            String phoneNumberValue =  phoneNumber.getText().toString();
            String addressValue =  address.getText().toString();
            String favBookValue =  favBook.getText().toString();
            userInfo = new UserInfo(firstNameValue , lastNameValue , phoneNumberValue , addressValue , favBookValue);
            navigateFurther();
        }else{
            Toast.makeText(getActivity(), "Please fill details correctly" , Toast.LENGTH_LONG).show();
        }

    }

    private void navigateFurther() {
        FragmentManager fragManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
        Bundle userInfoBundle = new Bundle();
        userInfoBundle.putSerializable("formInfo" , userInfo);
        userInfoBundle.putSerializable("orders" , orderModal);
        SecondScreenFragment secondScreenFragment = SecondScreenFragment.newInstance(userInfoBundle);
        fragmentTransaction.replace(R.id.fragment_container , secondScreenFragment);
        fragmentTransaction.addToBackStack(SecondScreenFragment.class.getSimpleName());
        fragmentTransaction.commit();
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
            orderModal = (OrderModal) Utility.getObjectFromJSONString(response, OrderModal.class);

            return orderModal;
        }

        @Override
        protected void onPostExecute(Serializable orderModal) {
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
            validateForm();
            super.onPostExecute(orderModal);
        }
    }


    private class PostLocalJson extends AsyncTask<String, Void, Serializable> {

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
            orderModal = (OrderModal) Utility.getObjectFromJSONString(response, OrderModal.class);

            return orderModal;
        }

        @Override
        protected void onPostExecute(Serializable orderModal) {
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
            validateForm();
            super.onPostExecute(orderModal);
        }
    }


}
