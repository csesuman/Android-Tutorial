package com.example.mytextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView ageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTextView = findViewById(R.id.nameTextViewId); // Type cass not needed now

        ageTextView = findViewById(R.id.ageTextViewId);

        nameTextView.setText("Another suman vaiya");
        ageTextView.setText("25 years old");

    }
}