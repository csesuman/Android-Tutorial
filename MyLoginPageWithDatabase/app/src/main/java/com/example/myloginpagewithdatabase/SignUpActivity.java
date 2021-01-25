package com.example.myloginpagewithdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, emailEditText, usernameEditText, passwordEditText;
    private Button signUpButton;
    UserDetails userDetails;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText =  findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButtonId);
        dataBaseHelper = new DataBaseHelper(this);

        userDetails = new UserDetails();
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setPassword(password);
        userDetails.setUsername(username);

        long rowId = dataBaseHelper.insertData(userDetails);

        if(rowId > 0)  {
            Toast.makeText(getApplicationContext(), "Row " + rowId + " is successfully inserted" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Row insertion failed" , Toast.LENGTH_LONG).show();
        }
    }

}