package com.example.dialogapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class LoginDialog extends AppCompatDialogFragment  {

    private EditText editTextUsername;
    private EditText editTextPssword;

    private LoginDialogListener lister;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = editTextUsername.getText().toString();
                        String password = editTextPssword.getText().toString();

                        lister.applyTexts(username, password);
                    }
                });

        editTextUsername = view.findViewById(R.id.edit_username);
        editTextPssword = view.findViewById(R.id.edit_password);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            lister = (LoginDialogListener) context;
        } catch (Exception e) {
           throw  new ClassCastException( context.toString() + " must implement LoginDialogListener" );
        }
    }

    public interface LoginDialogListener {
        void applyTexts( String username, String password );
    }
}
