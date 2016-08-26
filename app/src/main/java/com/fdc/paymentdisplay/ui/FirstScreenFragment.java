package com.fdc.paymentdisplay.ui;

import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.constant.Constants;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class FirstScreenFragment extends Fragment implements View.OnClickListener {
    private final String HTTP = "https://api-int.payeezy.com/v1/clovergo/refdata";
    private final String JSONFILE = "paymentdetails.json";
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
                fetchLocalJson.execute(JSONFILE);
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
            PostLocalJson postLocalJson = new PostLocalJson();
            postLocalJson.execute(HTTP);

        }else{
            Toast.makeText(getActivity(), "Please fill details correctly" , Toast.LENGTH_LONG).show();
        }

    }

    private void navigateFurther() {
        FragmentManager fragManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
        Bundle userInfoBundle = new Bundle();
        userInfoBundle.putSerializable(Constants.USERINFO, userInfo);
        userInfoBundle.putSerializable(Constants.ORDERS , orderModal);
        SecondScreenFragment secondScreenFragment = SecondScreenFragment.newInstance(userInfoBundle);
        fragmentTransaction.replace(R.id.fragment_container , secondScreenFragment);
        fragmentTransaction.addToBackStack(SecondScreenFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }


    private class FetchLocalJson extends AsyncTask<String, Void, Serializable> {

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity());
            progress.setMessage("Loading...");
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
            validateForm();
            super.onPostExecute(orderModal);
        }
    }


    private class PostLocalJson extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            if (progress != null && progress.isShowing()) {
                progress.setMessage("Posting Details...");
            }
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();

            HttpURLConnection urlConnection=null;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.connect();

                //Create JSONObject here
                JSONObject jsonParam = new JSONObject();
                jsonParam.put(Constants.ORDERS, orderModal);
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(jsonParam.toString());
                out.close();

                int HttpResult =urlConnection.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_OK){
                    sb.append("Success");
                    return sb.toString();
                }else{
                    System.out.println(urlConnection.getResponseMessage());
                    sb.append( urlConnection.getResponseMessage());
                    return sb.toString();
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            catch (IOException e) {

                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally{
                if(urlConnection!=null)
                    urlConnection.disconnect();
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String message) {
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
            navigateFurther();
            super.onPostExecute(message);
        }
    }


}
