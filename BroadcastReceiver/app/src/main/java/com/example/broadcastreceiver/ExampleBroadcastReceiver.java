package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
         
        if("com.broadcastreceiver.EXAMPLE_ACTION".equals(intent.getAction())) {

//            String receiveText = intent.getStringExtra("com.broadcastreceiver.EXTRA_TEXT");
            String receviedText = intent.getStringExtra("com.broadcastreceiver.EXTRA_TEXT");
            Toast.makeText(context, receviedText, Toast.LENGTH_SHORT).show();

//            Log.i("Suman", " comes for connectivity options");
//            Boolean noConnectivity = intent.getBooleanExtra(
//                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,
//                    false
//            );
//
//            if(noConnectivity) {
//                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
