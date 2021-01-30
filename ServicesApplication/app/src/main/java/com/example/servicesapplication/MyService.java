package com.example.servicesapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.Range;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyService extends Service {

    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    class MyServiceBinder extends Binder {
        public MyService getMyService() {
            return MyService.this;
        }
    }

    private IBinder mBinder = new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getString(R.string.service_demo_tag), "In onBind");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
        Log.i(getString(R.string.service_demo_tag), " Service Destroyed .");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getString(R.string.service_demo_tag),"In onStartCommand, thread id: " + Thread.currentThread().getId());
        if(mIsRandomGeneratorOn==false) {
            mIsRandomGeneratorOn = true;
            new Thread((Runnable) () -> {
                startRandomNumberGenerator();
            }).start();
        }
        return START_STICKY;
    }

    private void startRandomNumberGenerator(){
        while(mIsRandomGeneratorOn) {
            try {
                mRandomNumber = new Random().nextInt(MAX) + MIN;
                Log.i(getString(R.string.service_demo_tag),"thread id: " + Thread.currentThread().getId() + ", Random Number: " + mRandomNumber);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.i(getString(R.string.service_demo_tag),"Thread interrupted");
            }
        }
    }
    private void stopRandomNumberGenerator(){
        mIsRandomGeneratorOn = false;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(getString(R.string.service_demo_tag), "In onUnbind");
        return super.onUnbind(intent);
    }

    public int getRandomNumber(){
        return mRandomNumber;
    }
}
