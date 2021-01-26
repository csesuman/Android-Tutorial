package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    ImageView mainImageView ;
    TextView titleView, descriptionView;

    String name,description;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainImageView = findViewById(R.id.mainImageView);
        titleView = findViewById(R.id.title);
        descriptionView = findViewById(R.id.description);

        getData();
        setData();
    }
    
    private void getData() {
        if(getIntent().hasExtra("myImage")  && getIntent().hasExtra("name") && getIntent().hasExtra("description")) {

            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            myImage = getIntent().getIntExtra("myImage",1);
        }
        else {
            Toast.makeText(this, "No data,", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void setData() {
        titleView.setText(name);
        descriptionView.setText(description);
        mainImageView.setImageResource(myImage);
    }
}