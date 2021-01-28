package com.example.fragmentlayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment {

    private static final String KEY_MY_DATA = "myImage";

    public static Fragment getInstance(int image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_MY_DATA,image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        setImage(rootView);
        return rootView;
    }

    private void setImage(View rootView) {
        Bundle args = getArguments();
        if(args != null) {
            ((ImageView)rootView.findViewById(R.id.imageView))
                    .setImageResource(args.getInt(KEY_MY_DATA));
        }
    }
}