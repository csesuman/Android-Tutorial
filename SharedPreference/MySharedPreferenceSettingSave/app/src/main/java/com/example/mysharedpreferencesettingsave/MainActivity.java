package com.example.mysharedpreferencesettingsave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearLayoutId);
        if(loadColor()!=getResources().getColor(R.color.design_default_color_primary)) {
            linearLayout.setBackgroundColor(loadColor());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.redColorMenuItemId) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.red));
            storeColor(getResources().getColor(R.color.red));
        }

        if(item.getItemId()==R.id.greendColorMenuItemId) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.green));
            storeColor(getResources().getColor(R.color.green));
        }

        if(item.getItemId()==R.id.pinkColorMenuItemId) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.orange));
            storeColor(getResources().getColor(R.color.orange));
        }

        if(item.getItemId()==R.id.yellowColorMenuItemId) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
            storeColor(getResources().getColor(R.color.design_default_color_primary));
        }


        return super.onOptionsItemSelected(item);
    }

    private void storeColor(int color) {
        SharedPreferences sharedPreferences = getSharedPreferences("SelecedColor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("myColor",color);
        editor.commit();
    }

    private int loadColor() {
        SharedPreferences sharedPreferences = getSharedPreferences("SelecedColor", Context.MODE_PRIVATE);
        int seletedColor = sharedPreferences.getInt("myColor",getResources().getColor(R.color.blue));
        return  seletedColor;
    }
}