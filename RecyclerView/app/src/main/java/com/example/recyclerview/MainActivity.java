package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String nameList[], descriptions[];
    int images[] = {R.drawable.cplusplus, R.drawable.java,R.drawable.javascript,R.drawable.python, R.drawable.ruby, R.drawable.swift, R.drawable.typescript, R.drawable.visualstudio};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        nameList = getResources().getStringArray(R.array.programming_languages);
        descriptions = getResources().getStringArray(R.array.descriptions);

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter( this, nameList, descriptions, images);

        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}