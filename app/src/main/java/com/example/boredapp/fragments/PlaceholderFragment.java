package com.example.boredapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.boredapp.R;

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ImageView imageView;

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int section_number){
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, section_number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_placeholder, container, false);

         imageView = view.findViewById(R.id.slider_image);

         int sectNumb = getArguments().getInt(ARG_SECTION_NUMBER);
         int[] images = {R.drawable.ic_camera_black_24dp,
                 R.drawable.ic_filter_vintage_black_24dp,
                 R.drawable.ic_monochrome_photos_black_24dp};
         imageView.setImageResource(images[sectNumb]);
        return view;
    }

}
