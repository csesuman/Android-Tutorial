package com.example.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.databindingjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
      ActivityMainBinding activityMainBinding =  DataBindingUtil.setContentView(this,R.layout.activity_main);

//        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(activityMainBinding.getRoot());
        activityMainBinding.setMyVariable("Hello DataBinding......");

        User user = new User("Youts Truly", 26, true, "https:vull");


        activityMainBinding.setUser(user);

//        activityMainBinding.button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


}