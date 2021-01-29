package com.example.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast();
            }
        });
    }


    public void sendBroadcast() {
        Intent intent = new Intent("com.example.EXAMPLE_ACTION");
//        intent.setClass(this, ExampleBroadcastReceiver2.class);
//        ComponentName cn = new ComponentName("com.example.broadcastreceiver", "com.example.broadcastreceiver.ExampleBroadcastReceiver" );
////        ComponentName cn = new ComponentName(this, ExampleBroadcastReceiver2.class);
//        intent.setComponent(cn);

//        intent.setClassName("com.example.broadcastreceiver", "com.example.broadcastreceiver.ExampleBroadcastReceiver");

        intent.setPackage( "com.example.broadcastreceiver");
//
//        PackageManager packageManager = getPackageManager();
//        List<ResolveInfo>  infos = packageManager.queryBroadcastReceivers(intent,0);
//        Log.i("Suman", "Hello " + infos.size());
////        sendBroadcast(intent);
//        for(ResolveInfo info: infos)  {
//            ComponentName cn = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
//            Log.i("Suman", info.activityInfo.packageName + " + " + info.activityInfo.name);
//            intent.setComponent(cn);
            sendBroadcast(intent);
//        }
    }
}