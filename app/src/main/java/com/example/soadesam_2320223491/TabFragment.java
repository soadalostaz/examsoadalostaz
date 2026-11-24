package com.example.soadesam_2320223491;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    public static TabFragment newInstance(int position) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(requireContext());
        tv.setTextSize(24);
        tv.setGravity(android.view.Gravity.CENTER);

        int position = getArguments() != null ? getArguments().getInt(ARG_POSITION) : 0;
        switch (position) {
            case 0: tv.setText("First Screen"); break;
            case 1: tv.setText("Second Screen"); break;
            case 2: tv.setText("Third Screen"); break;
        }

        return tv;
    }
}