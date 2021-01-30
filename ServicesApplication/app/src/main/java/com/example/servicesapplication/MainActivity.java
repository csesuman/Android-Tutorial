 package com.example.servicesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button buttonStart, buttonStop;
    private TextView textViewThreadCount;
    int count = 0;

    private Intent serviceIntent;
    private boolean mStopLoop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(getString(R.string.service_demo_tag) , "Main.Activity thread id: " +Thread.currentThread().getId());

        buttonStart = findViewById(R.id.buttonStarterThread);
        buttonStop = findViewById(R.id.buttonStopThread);

        textViewThreadCount = findViewById(R.id.textViewThreadCount);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        serviceIntent = new Intent(getApplicationContext(), MyService.class);
    }

     @Override
     public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonStarterThread:
                mStopLoop = true;
                startService(serviceIntent);
                break;
            case R.id.buttonStopThread:
                break;
        }
     }
 }