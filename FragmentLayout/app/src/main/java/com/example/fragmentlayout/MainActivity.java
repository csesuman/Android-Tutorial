package com.example.fragmentlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runFragTxn(R.id.frameContainer, ImageFragment.getInstance(R.drawable.swift)) ;
        runFragTxn(R.id.frameBottom, BottomFragment.getInstance());
    }

//    private void runFragTxn() {
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = manager.beginTransaction();
////        fragmentTransaction.add(R.id.frameContainer, MyFragment.getInstance("My Data"));
//        fragmentTransaction.replace(R.id.frameContainer, MyFragment.getInstance("My is My Code"));
//        fragmentTransaction.commit();
//    }

    public void runFragTxn(int container, Fragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace( container, fragment);
        fragmentTransaction.commit();
    }
}