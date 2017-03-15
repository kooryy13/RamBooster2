package com.example.kooryy2.cleanmaster.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kooryy2.cleanmaster.Fragment.FragmentAddWhiteList;
import com.example.kooryy2.cleanmaster.Fragment.FragmentDiscover;
import com.example.kooryy2.cleanmaster.Fragment.FragmentHome;
import com.example.kooryy2.cleanmaster.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentHome.newInstance()).commit();
    }
}

