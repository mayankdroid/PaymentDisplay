package com.fdc.paymentdisplay.ui;

import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.modal.UserInfo;
import com.fdc.paymentdisplay.util.Utility;

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
                validateForm();
                FragmentManager fragManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
                Bundle userInfoBundle = new Bundle();
                //UserInfo userInfo = new UserInfo("first" ,"first" , "first" , "first" ,"first");
                userInfoBundle.putSerializable("formInfo" , userInfo);
                SecondScreenFragment secondScreenFragment = SecondScreenFragment.newInstance(userInfoBundle);
                fragmentTransaction.replace(R.id.fragment_container , secondScreenFragment);
                fragmentTransaction.addToBackStack(SecondScreenFragment.class.getSimpleName());
                fragmentTransaction.commit();
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
        }else{
            Toast.makeText(getActivity(), "Please fill details correctly" , Toast.LENGTH_LONG).show();
        }

    }



}
