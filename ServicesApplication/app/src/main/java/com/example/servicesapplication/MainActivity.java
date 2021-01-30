 package com.example.servicesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button buttonStart, buttonStop, buttonBinService, buttonUnbindService, buttonGetRandomNumber;
    private TextView textViewThreadCount;

    private MyService myService;
    private Boolean isServiceBound;
    private ServiceConnection serviceConnection;

    private Intent serviceIntent;
    private boolean mStopLoop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(getString(R.string.service_demo_tag) , "Main.Activity thread id: " +Thread.currentThread().getId());

        buttonStart = findViewById(R.id.buttonStartThread);
        buttonStop = findViewById(R.id.buttonStopThread);
        buttonBinService = findViewById(R.id.buttonBindService);
        buttonUnbindService = findViewById(R.id.buttonUnbindService);
        buttonGetRandomNumber = findViewById(R.id.buttonGetRandomNumber);

        textViewThreadCount = findViewById(R.id.textViewThreadCount);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonBinService.setOnClickListener(this);
        buttonUnbindService.setOnClickListener(this);
        buttonGetRandomNumber.setOnClickListener(this);

        isServiceBound = false;
        serviceIntent = new Intent(getApplicationContext(), MyService.class);
    }

     @Override
     public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonStartThread:
                mStopLoop = true;
                startService(serviceIntent);
                break;
            case R.id.buttonStopThread:
                stopService(serviceIntent);
                break;
            case R.id.buttonBindService:
                bindService();
                break;
            case R.id.buttonUnbindService:
                unbindService();
                break;
            case R.id.buttonGetRandomNumber:
                setRandomNumber();
                break;

        }
     }

     private void setRandomNumber() {
        if(isServiceBound){
            textViewThreadCount.setText("Random Number: " + myService.getRandomNumber());
        }
        else {
            textViewThreadCount.setText("Service not bound");
        }
     }

     private void unbindService() {
        if(isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
     }

     private void bindService() {
        if(serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder)service;
                    myService = myServiceBinder.getMyService();
                    isServiceBound = true;
                }
       
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceBound = false;
                }
            };
        }

        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
     }
 }