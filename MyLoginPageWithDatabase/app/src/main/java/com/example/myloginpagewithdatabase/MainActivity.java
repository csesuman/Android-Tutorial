package com.example.myloginpagewithdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DataBaseHelper dataBaseHelper;

    private Button singInButton, singUpButton;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singInButton = findViewById(R.id.signInButtonId);
        singUpButton = findViewById(R.id.signUpHereButtonId);

        usernameEditText = findViewById(R.id.signInUserNameEditText);
        passwordEditText = findViewById(R.id.signInPasswordEditText);

        dataBaseHelper =  new DataBaseHelper(this);
        SQLiteDatabase sqLiteDatabase =  dataBaseHelper.getWritableDatabase();

        singUpButton.setOnClickListener(this);
        singInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if( v.getId()  == R.id.signInButtonId ) {

            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            Boolean result = dataBaseHelper.findPassword(username, password);

            if(result) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class );
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Username and password didn't match" , Toast.LENGTH_LONG).show();
            }
        }

        if( v.getId() == R.id.signUpHereButtonId ) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}