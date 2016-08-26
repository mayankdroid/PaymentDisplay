package com.fdc.paymentdisplay;

import com.fdc.paymentdisplay.ui.FirstScreenFragment;
import com.fdc.paymentdisplay.ui.SecondScreenFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentHoldingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holding);
        addFragment();
    }

    private void addFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FirstScreenFragment firstScreenFragment = FirstScreenFragment.newInstance(null);
        fragmentTransaction.replace(R.id.fragment_container, firstScreenFragment);
        fragmentTransaction.commit();
    }


}
