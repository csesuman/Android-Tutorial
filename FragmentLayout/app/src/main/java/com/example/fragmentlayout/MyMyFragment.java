package com.example.fragmentlayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMyFragment extends Fragment {

    private static final String KEY_MY_DATA = "myData";

    public static Fragment getInstance(String my_data) {
        MyMyFragment fragment = new MyMyFragment();
        Bundle args = new Bundle();
        args.putString(KEY_MY_DATA,my_data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);

        setText(rootView);

        rootView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

    private void setText(View rootView) {

        Bundle args = getArguments();
        if(args != null) {
            ((TextView)rootView.findViewById(R.id.textData))
                    .setText(args.getString(KEY_MY_DATA));
        }
    }
}