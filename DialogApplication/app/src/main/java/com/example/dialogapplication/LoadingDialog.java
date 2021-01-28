package com.example.dialogapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog dialog;

    LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    void startLoadingDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater= activity.getLayoutInflater();
        builder.setView(inflater.inflate((R.layout.custom_dialog_animation), null ));
        builder.setCancelable(false);


        dialog = builder.create();
        dialog.show();
    }

    void dismissDialog() {
        dialog.dismiss();
    }
}
