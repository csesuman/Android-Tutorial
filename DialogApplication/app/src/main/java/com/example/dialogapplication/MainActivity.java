package com.example.dialogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginDialog.LoginDialogListener {

    private Button simpleButton;

    private Button twoSimpleButton;
    Dialog dialog;

    private TextView textUserName;
    private TextView textPassword;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleButton = findViewById(R.id.buttonSimple);
        simpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSimpleDialog();
            }
        });

        // Second Button
        // Todo: fade in and fade out not working
        // Todo: Custom dialog is large !!!

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.two_button_custom_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button okay = dialog.findViewById(R.id.ok_button);
        Button cancel = dialog.findViewById(R.id.cancel_button);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Okay", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        // 3rd Done Okay

        twoSimpleButton = findViewById(R.id.twoButtonDialog);
        twoSimpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        // 3rd Button
        textUserName = (TextView) findViewById(R.id.text_username);
        textPassword = (TextView) findViewById(R.id.text_password);
        Button buttonSendData = findViewById(R.id.buttonSendDataToActivity);
        buttonSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForLogin();
            }
        });


        // 4th

        Button buttonAnimation = findViewById(R.id.buttonAnimation);
        final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);


        buttonAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 5000);
            }
        });
    }

    private void openDialogForLogin() {
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.setCancelable(false);

        loginDialog.show( getSupportFragmentManager(), "Login Dialog-- ;)" );
    }

    private void openSimpleDialog() {
        SimpleDialog simpleDialog = new SimpleDialog();
        simpleDialog.setCancelable(false);
        simpleDialog.show( getSupportFragmentManager(), " Example Dialog" );
    }

    @Override
    public void applyTexts(String username, String password) {
        textUserName.setText(username);
        textPassword.setText(password);
    }
}