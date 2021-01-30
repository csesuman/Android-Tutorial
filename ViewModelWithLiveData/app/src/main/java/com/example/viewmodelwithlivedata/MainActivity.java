package com.example.viewmodelwithlivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private Button addButton;
    private Button resetButton;
//    private  int score;

    ScoreViewModel scoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.scoreTextView);
        addButton = findViewById(R.id.addButton);
        resetButton = findViewById(R.id.resetButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addScore();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScore();
            }
        });

        scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        scoreViewModel.getScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer){
                scoreTextView.setText( String.valueOf( integer ) );
            }
        });
    }

    private void resetScore() {
        scoreViewModel.resetScore();
    }

    private void addScore() {
        scoreViewModel.addScore();
    }
}