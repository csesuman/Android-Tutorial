package com.example.fragmentlayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomFragment extends Fragment {

    public static Fragment getInstance() {
        BottomFragment fragment = new BottomFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bottom, container, false);

        initButtons(rootView);

        return rootView;
    }

    private void initButtons(View rootView) {

        rootView.findViewById(R.id.cplusplus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage(R.drawable.cplusplus);
            }
        });

        rootView.findViewById(R.id.java).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage(R.drawable.java);
            }
        });
    }

    private void changeLanguage(int language) {

        ( (MainActivity) getActivity() )
                .runFragTxn(R.id.frameContainer, ImageFragment.getInstance( language ) );
    }
}