package com.example.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.databindingjava.databinding.ActivityMainBinding;


// https://www.youtube.com/watch?v=tDYZBSSgp1c&t=649s

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
      ActivityMainBinding activityMainBinding =  DataBindingUtil.setContentView(this,R.layout.activity_main);

//        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(activityMainBinding.getRoot());
        activityMainBinding.setMyVariable("Hello DataBinding......");

        String url = "https://scontent.fcla1-1.fna.fbcdn.net/v/t1.0-1/p480x480/74224154_2698649110199368_7930597270284664832_o.jpg?_nc_cat=107&ccb=3&_nc_sid=dbb9e7&_nc_ohc=k6hjX9NCrcQAX8Dwc5-&_nc_ht=scontent.fcla1-1.fna&tp=6&oh=0bc844d953f0cac0490ecfcaec4d4467&oe=60657E3F";

        User user = new User("Youts Truly", 26, true, url);


        activityMainBinding.setUser(user);
//
//        activityMainBinding.button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


}