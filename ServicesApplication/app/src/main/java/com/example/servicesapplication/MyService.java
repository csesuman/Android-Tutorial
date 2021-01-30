package com.example.servicesapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getString(R.string.service_demo_tag), " Service Destroyed . Current Thread id " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getString(R.string.service_demo_tag),"In onStartCommand, thread id: " + Thread.currentThread().getId());
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
