package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Video Link : https://www.youtube.com/watch?v=GlzbOjzEhc0
    // initialize variable

    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign Variables
        editText = findViewById(R.id.edit_text);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        // Initialize database
        database = RoomDB.getDatabase(this);

        // Store values in the data list
        dataList = (List<MainData>) database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);

        // set Layout Manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter = new MainAdapter(MainActivity.this, dataList);

        // Set Adapter
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get String form edit text
                String sText = editText.getText().toString();

                // Check condition
                if(!sText.equals("")) {
                    // When text is not empty
                    // Initialize main data
                    MainData data = new MainData();

                    // Set text on main data
                    data.setText(sText);

                    database.mainDao().insert(data);

                    editText.setText("");
                    // Notify when data is inserted

                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete all data from databse
                database.mainDao().reset(dataList);
                // Notify when all data deleted
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}